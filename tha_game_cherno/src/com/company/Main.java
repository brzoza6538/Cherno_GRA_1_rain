package com.company;


import com.company.entity.mob.Player;
import com.company.graphics.Screen;
import com.company.input.Keyboard;
import com.company.input.Mouse;
import com.company.level.Level;
import com.company.level.RandomLevel;
import com.company.level.SpawnLevel;
import com.company.level.TileCoordinate;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

//implement - interface
// extends - klasy
public class Main extends Canvas implements Runnable
{

//////////////obiekty//////////////////////////////

    private static final long serialVersionUID = 1L;
    public static String title = "Rain";
    private static int width = 500;
    private static int height = width / 16 * 9; //  168
    private static int scale = 2;

    // wielkosc zrobimy na scale * width pixeli ale poniewaz nie uzywamy karty graficznej pracujemy na width pixeli

    private Thread thread;
    public JFrame frame;
    private Keyboard key;
    // thread that runs game
    private boolean running = false;

    private Screen screen;


    private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    //creating image with the buffer
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()) .getData();

    private Level level;

    private Player player;
    public int FPS = 0;

///////////////////////metody//////////////////////

    public Main() // constructor
    {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        frame = new JFrame();
        screen = new Screen(width, height);

        key = new Keyboard();
        addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        level = Level.spawn; // mozna zmienic "random" || "spawn"
        TileCoordinate playerspawn = new TileCoordinate( 5,5); //30,28); - wartosci dla spawn // 0,1); - dla random
        player = new Player(playerspawn.X(),playerspawn.Y(), key);
        player.init(level);
    }

    public static void main(String[] args)
    {
        Main game = new Main();
        game.frame.setResizable(false); // not resizable - WAZNE
        game.frame.setTitle(title + " | " + "updates : - | fps : -");
        game.frame.add(game); // fillls window with object
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // koniec procesu na zamknieciu okna
        game.frame.setLocationRelativeTo(null); // center window on screen

        game.frame.setVisible(true);

        game.start();

    }


    public synchronized void start()
    {
        running = true;

        //synchronised - nie ma overlapów threadów

        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop()
    {
        running = false;

        try
        {
            thread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    public void run() //contains game loop
    {
        long  lastTime = System.nanoTime(); // nanosekunda
        long timer = System.currentTimeMillis();
        final double nano_s = 1000000000.0 / 60.0; //  60 na sekunde wyjdzie
        double delta = 0;
        long now;
        int updates = 0;

        requestFocus(); // okienko jest in focus od razu


        while(running)
        {
             now = System.nanoTime(); // nanosekunda

            delta += (now - lastTime) / nano_s;
            lastTime = now;

            while(delta >= 1)
            {
                update(); // - update gry/logiki lub tick(); - restrykcje czasowe wazne zeby kady gral z taka sama szybkoscia
                delta--;
                updates ++;
            }
            //System.out.println("Running...");
            render(); // - upudate grafiki  - moze sie wykonywac ile razy potrzebuje
            FPS ++;
            if(System.currentTimeMillis() - timer  > 1000)
            {
                timer += 1000;
                //System.out.println(updates+"  " + FPS);
                frame.setTitle(title + " | " + "updates : " + updates + " | " + "fps : " + FPS);

                FPS = 0;
                updates  = 0;

            }
        }//petla - grafic part + logic part
        // kadzy ma inny fPS - trzeba to zakotwiczyc o prawdziwy czas
        stop();
    }



    public void update()
    {
        key.update();
        player.update();
        level.update();
    }
    public void render()
    {
        BufferStrategy bs = getBufferStrategy();

        //create buffer strategy - zeby zachowaly sie informacje z nastepnej klatki
        // - nie zmieniasz pixel po pixelu tylko klakta po klatce
        //  nie chcemy tworzy bufferStategy co ulamek sekundy
        if(bs == null) // jesli wczesniej nie byl stworzony to zostaje stworzony
        {
            createBufferStrategy(3);
            return;
            // 3 - prawie zawsze na 3 bufferowanie  - jesli ma czas na stworzenie nastepnej klatki to to zacznie robic
        }

        screen.clear();
        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render(xScroll, yScroll,screen);
        player.render(screen);

        screen.renderFrame();

        for (int i =0; i < pixels.length;i++)
        {
            pixels[i] = screen.pixels[i];
        }
        //pusty triple buffer

        Graphics g = bs.getDrawGraphics();
/* - znacząco zrzuca fps */
        g.drawImage (image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(new Color(0xFFFF0000, true));
        g.setFont(new Font("Verdana",1,12));
        g.drawString("X: " + (player.x - 4) + " Y: " + (player.y + 6) ,(width - 50)*scale - 20,10*scale - 4); // position shower
        //g.drawString("FPS: " + FPS,272*scale,9*scale);

        //g.fillRect(Mouse.getX() - 8,Mouse.getY() - 8, 16,16);

        g.dispose();

        bs.show();

        //{ zmiany grafiki

        //g.setColor(Color.blue);
        /* -- tlo
        g.setColor(new Color(20,50,180));
        g.setColor(Color.BLACK);
        //tło
        g.fillRect(0,0,width * scale, getHeight() ); // getWidth, getHeight
        */

        //przod
        g.drawImage(image, 0,0,getWidth(), getHeight(), null);

        //0,0 - top left corner wypelnia lecac w prawo i w dol

        //}
        g.dispose(); // dispose of curent graphics
        //bufferswapping / blitting
        // buffer - temporary storage in RAM
        bs.show(); // pokaze kolejny przygotowywany buffer
    }

    public static int getWindowWidth()
    {
        return width * scale;
    }
    public static int getScale()
    {
        return scale;
    }

}
//przy debugowaniu problem - nie moze debugowac przy kilku watkach?

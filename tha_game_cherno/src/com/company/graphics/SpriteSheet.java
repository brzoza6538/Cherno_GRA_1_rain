package com.company.graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet
{
    private  String path;
    public final int SIZE; //final = cap
    public final int WIDTH;
    public final int HEIGHT;

    public int[] pixels;
    private Sprite[] sprites;

    public static SpriteSheet spawn_level = new SpriteSheet("tha_game_cherno/resources/textures/sheets/spawn_level_4.0.png", 48);

    public static SpriteSheet spell_projetiles = new SpriteSheet("tha_game_cherno/resources/textures/sheets/projectiles/spell_projectiles.png", 48);



    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize)
    {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;

        WIDTH = w;
        HEIGHT = h;
        SIZE = -1;

        pixels = new int[w*h];

        for(int y0 = 0; y0 < h; y0++)
        {
            int yp = yy + y0;
            for(int x0 = 0; x0 < w; x0++)
            {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }


        // dzielenie na klatki -> zapisywanie
        int frame = 0;
        sprites = new Sprite[width*height];
        for(int ya = 0; ya  <  height; ya ++)
        {
            for(int xa = 0; xa  <  width; xa ++)
            {
                int[] spritePixels = new int[spriteSize*spriteSize];
                /////////////////////////////////////
                for(int y0 = 0; y0 < spriteSize; y0++)
                {
                    for (int x0 = 0; x0 < spriteSize; x0++)
                    {
                        spritePixels[x0 + y0*spriteSize] = pixels[(x0 + xa*spriteSize)  + (y0 + ya*spriteSize) * WIDTH];
                    }
                }
                /////////////////////////////////////
                Sprite sprite = new Sprite(spritePixels, spriteSize,spriteSize);
                sprites[frame++] = sprite;
            }

        }

    }


    public SpriteSheet(String path, int width, int height)
    {
        this.path = path;
        SIZE = -1;

        WIDTH = width;
        HEIGHT = height;

        pixels = new int [HEIGHT * WIDTH];

        load();
    }


    public SpriteSheet(String path, int size)
    {
        this.path = path;
        this.SIZE = size;
        WIDTH = size;
        HEIGHT = size;

        pixels = new int[SIZE*SIZE];
        load();
    }

    public Sprite[] getSprites()
    {
        return this.sprites;
    }


    private void load()
    {
        try
        {
            //BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            BufferedImage image = ImageIO.read(new File(this.path));

            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0,w,h,pixels, 0,w);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


}
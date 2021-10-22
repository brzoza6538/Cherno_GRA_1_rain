package com.company.graphics;

import com.company.entity.mob.Player;
import com.company.entity.projectiles.Projectile;
import com.company.level.tile.Tile;

import java.awt.*;
import java.util.Random;
//tiles must be squares

public class Screen
{
    public int width;
    public int height;
    public int[] pixels;
    public int xOffset,yOffset;
    public static int FRAME = 3;

    private Random random = new Random();

    public Screen(int width, int height) // constructor
    {
        this.width = width;
        this.height = height;

        pixels = new int[height * width]; // WHY NOT INT[width][height]??!?!?!?
        /*
        for (int i = 0; i < MAP_SIZE*MAP_SIZE; i ++)
        {
            tiles[i] = random.nextInt(0xffffff); // od zera do ()
        }
        */
    }
    //0x liczba zamiana na hexadecimal
    //0b do binary

    // ruch przesuwając tile-y


    public void clear()
    {
        for (int i = 0; i < pixels.length; i++)
        {
            pixels[i] = 0;
        }
    }

    public void renderFrame()
    {
        for(int y = 0; y < height; y++)
        {

            for (int x = 0; x < width; x++)
            {

                //if (!((y < height - FRAME && y > FRAME-1) && (x < width - FRAME && x > FRAME-1)))
                if (((y > height - FRAME || y < FRAME-1) || (x > width - FRAME || x < FRAME-1)))
                {
                    pixels[x+(y*width)] = 0xff6e29;
                    //pixels[x+(y*width)] = 0x1230b1;
                }
            }
        }
    }

    public void renderTile(int xp, int yp, Tile tile)
    {
        boolean x_f = false, y_f = false; //flip

        // y - pozycja do renderowania
        //ya - pozycja absolutna
        //yp - lokacja na mapie
        xp -= xOffset;
        yp -= yOffset;

        for(int y = 0; y < tile.sprite.SIZE; y++)
        {
            int ya = yp + y;
            int ys = y;
            if(y_f){ys = tile.sprite.SIZE - 1 - y;}

            for(int x = 0; x < tile.sprite.SIZE; x++)
            {
                int xa = xp + x;
                int xs = x;
                if(x_f){xs = tile.sprite.SIZE - 1 - x;}

                if(xa <  -tile.sprite.SIZE || xa >= width   || ya < -tile.sprite.SIZE || ya >= height )  break; // render what is visible
                if(xa < 0) xa = 0;
                if(ya < 0) ya = 0;

                int col = tile.sprite.pixels[xs + ys * tile.sprite.SIZE];
                if(col != 0xffff00ff)
                {
                    pixels[xa + ya * width] = tile.sprite.pixels[xs + ys * tile.sprite.SIZE];
                }

            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p)
    {
        boolean x_f = false, y_f = false; //flip

        // y - pozycja do renderowania
        //ya - pozycja absolutna
        //yp - lokacja na mapie
        xp -= xOffset;
        yp -= yOffset;

        for(int y = 0; y < p.getSpriteSize(); y++)
        {
            int ya = yp + y;
            int ys = y;
            if(y_f){ys = p.getSpriteSize() - 1 - y;}

            for(int x = 0; x < p.getSpriteSize(); x++)
            {
                int xa = xp + x;
                int xs = x;
                if(x_f){xs = p.getSpriteSize() - 1 - x;}

                if(xa <  -p.getSpriteSize() || xa >= width   || ya < -p.getSpriteSize() || ya >= height )  break; // render what is visible
                if(xa < 0) xa = 0;
                if(ya < 0) ya = 0;

                int col = p.getSprite().pixels[xs + ys * p.getSpriteSize()];
                if(col != 0xffff00ff)
                {
                    pixels[xa + ya * width] = p.getSprite().pixels[xs + ys * p.getSpriteSize()];
                }

            }
        }
    }
    public void renderPlayer(int xp,int yp, Sprite sprite, boolean x_f,boolean y_f)
    {
        xp -= xOffset;
        yp -= yOffset;

        for(int y = 0; y < 32 ; y++)
        {
            int ya = yp + y;
            int ys = y;
            if(y_f){ys = 31 - y;}

            for(int x = 0; x < 32 ; x++)
            {
                int xs = x;
                if(x_f){xs = 31 - x;}
                int xa = xp + x;

                if(xa <  -32|| xa >= width   || ya < -32 || ya >= height )  break; // render what is visible
                if(xa < 0) xa = 0;
                if(ya < 0) ya = 0;

                int col = sprite.pixels[xs + ys * 32 ];
                //System.out.println(x + " - " + y + " - "  + Integer.toHexString(col));
                if(col != 0xffff00ff)
                    pixels[xa + ya * width] = col; // - 0xFF000000 - nie moge nie renderuje nic pod spodem

            }
        }
    }

    public void setOffset(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;

    }
}
//For loops are slightly faster for copying arrays than the System.arraycopy() method.

/* -- ramka
                if((x) % (width) == 0 )// do zmiany
                {
                    pixels[(int) (Math.random() * (grubosc_ramki)) + (((int) (Math.random() * (height-grubosc_ramki))) * width)] = 0xff00ff;
                    pixels[(int) (Math.random() * (grubosc_ramki)) + width - grubosc_ramki + (( grubosc_ramki + (int) (Math.random() * (height - grubosc_ramki))) * width)] = 0xff00ff;

                    pixels[(int) (Math.random() * (grubosc_ramki)) + (((int) (Math.random() * (height-grubosc_ramki))) * width)] = 0x00ffff;
                    pixels[(int) (Math.random() * (grubosc_ramki)) + width - grubosc_ramki + (( grubosc_ramki + (int) (Math.random() * (height - grubosc_ramki))) * width)] = 0x00ffff;

                    pixels[(int) (Math.random() * (grubosc_ramki)) + (((int) (Math.random() * (height-grubosc_ramki))) * width)] = 0xffff00;
                    pixels[(int) (Math.random() * (grubosc_ramki)) + width - grubosc_ramki + (( grubosc_ramki + (int) (Math.random() * (height - grubosc_ramki))) * width)] = 0xffff00;


                }
                if((y) % (height) == 0)
                {
                    pixels[grubosc_ramki + (int) (Math.random() * (width - grubosc_ramki)) + (((int) (Math.random() * (grubosc_ramki))) * width)] = 0xff00ff;
                    pixels[(int) (Math.random() * (width-grubosc_ramki)) + ((height - grubosc_ramki + ((int) (Math.random() * (grubosc_ramki))) )* width)] = 0xff00ff;

                    pixels[grubosc_ramki + (int) (Math.random() * (width - grubosc_ramki)) + (((int) (Math.random() * (grubosc_ramki))) * width)] = 0x00ffff;
                    pixels[(int) (Math.random() * (width-grubosc_ramki)) + ((height - grubosc_ramki + ((int) (Math.random() * (grubosc_ramki))) )* width)] = 0x00ffff;

                    pixels[grubosc_ramki + (int) (Math.random() * (width - grubosc_ramki)) + (((int) (Math.random() * (grubosc_ramki))) * width)] = 0xffff00;
                    pixels[(int) (Math.random() * (width-grubosc_ramki)) + ((height - grubosc_ramki + ((int) (Math.random() * (grubosc_ramki))) )* width)] = 0xffff00;
                }

                if((y < height - grubosc_ramki && y > grubosc_ramki-1) && (x < width - grubosc_ramki && x > grubosc_ramki-1))
                {
                    pixels[x+(y*width)] = 0x1230b1;
                }
 */
// - ladny pomarancz - 0xf0b00f

/* -- ramka
                if((y < height - grubosc_ramki && y > grubosc_ramki-1) && (x < width - grubosc_ramki && x > grubosc_ramki-1))
                {


                }
                else
                {
                    pixels[x+(y*width)] = 0xf0b00f;
                    //pixels[x+(y*width)] = 0x1230b1;
                }
 */
package com.company.graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet
{
    private  String path;
    public final int SIZE; //final = cap
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("resources/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_level = new SpriteSheet("resources/textures/sheets/spawn_level_2.0.png", 48);
    public static SpriteSheet spell_projetiles = new SpriteSheet("resources/textures/sheets/projectiles/spell_projectiles.png", 48);

    public SpriteSheet(String path, int size)
    {
        this.path = path;
        this.SIZE = size;

        pixels = new int[SIZE*SIZE];
        load(path);
    }
    private void load(String path)
    {
        try
        {
            //BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            BufferedImage image = ImageIO.read(new File(path));

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

package com.company.level;

import com.company.graphics.Screen;
import com.company.level.tile.Tile;
import com.company.level.tile.VoidTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestingLevel extends Level
{


    public TestingLevel(String path)
    {
        super(path);
        spawnX = 5;
        spawnY = 5;
    }

    protected void loadLevel(String path)
    {

        try
        {
            // BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            BufferedImage image = ImageIO.read(new File(path));

            width = image.getWidth();
            height = image.getHeight();

            tiles =  new int [width*height];

            image.getRGB(0,0,width,height,tiles,0,width);
        }
        catch(IOException e)
        {
            System.out.println("Level file not loaded");
        }
    }

    protected void generateLevel()
    {

    }
}
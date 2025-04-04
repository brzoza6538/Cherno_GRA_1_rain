package com.company.level;

import com.company.graphics.Screen;
import com.company.level.tile.Tile;
import com.company.level.tile.VoidTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpawnLevel extends Level
{


    public SpawnLevel(String path)
    {
        super(path);
        spawnX = 30;
        spawnY = 28;
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



        /*
        for(int i = 0; i < levelPixels.length; i++)
        {
            if(levelPixels[i] == 0xffff0000) {tiles[i] = Tile.voidTile;}
            else if(levelPixels[i] == 0xff9c9c9c) {tiles[i] = Tile.rock_2;}
            else if(levelPixels[i] == 0xff404040) {tiles[i] = Tile.rock_1;}
            else if(levelPixels[i] == 0xff00ff21) {tiles[i] = Tile.grass_1;}
            else if(levelPixels[i] == 0xffb6ff00) {tiles[i] = Tile.grass_2;}
            else if(levelPixels[i] == 0xffff00dc) {tiles[i] = Tile.flowers;}
            else if  (levelPixels[i] == 0xff00ffff) {tiles[i] = Tile.puddle;}
        }
    }

- old generateLevel

         */



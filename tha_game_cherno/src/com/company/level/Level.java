package com.company.level;

import com.company.graphics.Screen;
import com.company.level.tile.Tile;
import com.company.level.tile.VoidTile;

public class Level
{
    public int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    public static Level spawn = new SpawnLevel("resources/levels/spawn.png");
    public static Level random = new RandomLevel(256,256);

    public Level(int width, int height)
    {
        this.width = width;
        this.height = height;

        tilesInt = new int[width* height];
        prepare();
        generateLevel(true ,0,0,width-1,height-1);



    }

    public Level(String path)
    {
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel()
    {

    }

    protected void generateLevel(boolean xy, int x0, int y0 , int x1, int y1)
    {

    }

    protected void loadLevel(String path)
    {

    }


    public void update()
    {

    }
    private void time()
    {

    }

    public void render(int xScroll, int yScroll, Screen screen)
    {
        screen.setOffset(xScroll,yScroll);
        int x0 = (xScroll >> 4);
        int x1 = ((xScroll + screen.width) >> 4) + 1;

        int y0 = (yScroll >> 4);
        int y1 = ((yScroll + screen.height) >> 4) + 1;
        //defined render region

        for(int y = y0 ; y < y1; y++)
        {
            for(int x = x0 ; x < x1; x++)
            {
                getTile(x,y).render(x,y,screen);
            }
        }
    }


    public Tile getTile(int x, int y)
    {
        if (x < 0 || y < 0 || x >= width || y >= height) {return Tile.voidTile;}

            if(tiles[x+y * width] == Tile.col_void)      {return Tile.voidTile;}
            else if(tiles[x+y * width] == Tile.col_spawn_rock_2) {return Tile.spawn_rock_2;}
            else if(tiles[x+y * width] == Tile.col_spawn_rock_1) {return Tile.spawn_rock_1;}
            else if(tiles[x+y * width] == Tile.col_spawn_grass_1) {return Tile.spawn_grass_1;}
            else if(tiles[x+y * width] == Tile.col_spawn_grass_2) {return Tile.spawn_grass_2;}
            else if(tiles[x+y * width] == Tile.col_spawn_flowers) {return Tile.spawn_flowers;}
            else if(tiles[x+y * width] == Tile.col_spawn_puddle) {return Tile.spawn_puddle;}
            else  {return Tile.voidTile;}

        //return Tile.voidTile; // render tile void pozniej - trzeba zrobic udawanego nulla;

    }

    protected void prepare()
    {

    }
}

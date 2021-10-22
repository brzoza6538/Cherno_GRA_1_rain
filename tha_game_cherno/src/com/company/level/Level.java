package com.company.level;

import com.company.entity.Entity;
import com.company.entity.projectiles.Projectile;
import com.company.graphics.Screen;
import com.company.level.tile.Tile;
import com.company.level.tile.VoidTile;

import java.util.*;

public class Level
{
    public int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();

    public static Level spawn = new SpawnLevel("resources/levels/Level_4.png");     //spawn.png");
    //public static Level random = new RandomLevel(256,256);

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

    public List<Projectile> getProjectiles()
    {
        return projectiles;
    }

    public void update()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update();
        }

        for(int i = 0; i < projectiles.size(); i++)
        {
            projectiles.get(i).update();
        }
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

        for(int i = 0; i < entities.size(); i++)
        {
            entities.get(i).render(screen);
        }

        for(int i = 0; i < projectiles.size(); i++)
        {
            projectiles.get(i).render(screen);
        }
    }

    public void add(Entity e)
    {
        entities.add(e);
    }

    public void addProjectile (Projectile p)
    {
        projectiles.add(p);
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

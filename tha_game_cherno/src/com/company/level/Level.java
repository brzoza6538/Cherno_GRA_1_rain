package com.company.level;

import com.company.entity.Entity;
import com.company.entity.particle.Particle;
import com.company.entity.projectiles.Projectile;
import com.company.graphics.Screen;
import com.company.level.tile.Tile;

import java.util.*;

public class Level
{
    public int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();

    public static Level spawn = new SpawnLevel("resources/levels/level_2.png");     //spawn.png");
    public static Level random = new RandomLevel(1024,1024);

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

        for(int i = 0; i < particles.size(); i++)
        {
            particles.get(i).update();
        }
        remove();
    }
    private void remove()
    {
        for(int i = 0; i < entities.size(); i++)
        {
            if(entities.get(i).isRemoved())
            {
                entities.remove(i);
            }
        }

        for(int i = 0; i < projectiles.size(); i++)
        {
            if(projectiles.get(i).isRemoved())
            {
                projectiles.remove(i);
            }
        }

        for(int i = 0; i < particles.size(); i++)
        {
            if(particles.get(i).isRemoved())
            {
                particles.remove(i);
            }
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
        for(int i = 0; i < particles.size(); i++)
        {
            particles.get(i).render(screen);
        }
    }

    public void add(Entity e)
    {
        e.init(this);

        if(e instanceof Particle)
        {
            particles.add((Particle)e);
        }
        else if(e instanceof Particle)
        {
            projectiles.add((Projectile) e);
        }
        else
        {
            entities.add(e);
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

    public boolean Up_TileCollision(int x, int y, int size, int xOffset, int yOffset)
    {
        //System.out.println(level.getTile(((x+xa)/16),((y+ya+10)/16)).Name() + " == " +  ((x+xa)/16) + " == " + (y+ya+10)/16);

        boolean solid = false;

        for(int c = 0; c< 4; c++)
        {
            int xt = (x + (c % 2 * size) + xOffset) >> 4;
            int yt = (y + (c / 2 * size) + yOffset) >> 4;

            if(!getTile(xt,yt).shootable_through())
            {
                solid = true;
            }

        }
        return solid;
    }

    public boolean Down_TileCollision(int x, int y, int size, int xOffset, int yOffset)
    {
        boolean solid = false;

        for(int c = 0; c < 4; c++)
        {
            int xt = (x - (c % 2 * size) + xOffset) >> 4;
            int yt = (y - (c / 2 * size) + yOffset) >> 4;

            if(getTile(xt,yt).solid())
            {
                solid = true;
            }
        }
        return solid;
    }
}


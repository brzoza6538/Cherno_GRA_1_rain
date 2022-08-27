package com.company.entity.mob;

import com.company.entity.Entity;
import com.company.entity.spawner.ParticleSpawner;
import com.company.entity.projectiles.Projectile;
import com.company.entity.projectiles.SpellProjectile_1;
import com.company.entity.projectiles.SpellProjectile_2;
import com.company.graphics.AnimatedSprite;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.level.Level;
import com.company.objects.costumes.Basic;
import com.company.objects.costumes.Costume;


public abstract class Mob extends Entity
{
    protected int SPEED = 1;

    protected enum Direction
    {
        UP,DOWN,LEFT,RIGHT
    }
    protected Direction dir;
    protected Costume costume = new Basic();

    protected AnimatedSprite currentAnim = null;
    //protected Costume costume;
    /*
     0 - N
     1 - E
     2 - S
     3 - W
    */
    protected boolean walking = false;

    public Mob()
    {
        //this.costume = new Scafander("clean");
    }

    public void move(int xa, int ya)
    {

        if(xa > 0) dir = Direction.RIGHT;
        if(xa < 0) dir = Direction.LEFT;
        if(ya > 0) dir = Direction.DOWN;
        if(ya < 0) dir = Direction.UP;

        if(! collision(xa,0))
        {
            x += xa;
        }

        if(! collision(0,ya))
        {
            y += ya;
        }

        if(waterCheck(xa,ya))
        {
            level.add(new ParticleSpawner((x + xa),(y + ya) + 16 ,7, Sprite.particle_water ,7,level));
        }


    }

    public abstract void update();

    public abstract void render(Screen screen);

    protected boolean waterCheck(int xa, int ya)
    {
        boolean water = false;
        int wallCount = 4;

        for(int c = 0; c< 4; c++)
        {

            int xt = ((x + xa) + c % 2 * 3 - 1) / 16 ;
            int yt = ((y + ya) + c / 2 * 3 + 16) / 16 ;

            if(level.getTile(xt,yt).Water() == true   && (((x + xa) % 16 >= 3 && (x + xa) % 16 <= 11) && ((y + ya) % 16 >= 3 && (y + ya) % 16 <= 11)))
            {
                //level.add(new Spawner((xt)*16 + 8,(yt)*16 - 8,Spawner.Type.PARTICLE, 10,level));
                water = true;
            }
        }
        return water;
    }
    public boolean collision(int xa, int ya)
    {

            boolean solid = false;


        for(int c = 0; c< 4; c++)
        {
            int xt = ((x + xa) + (c % 2 * costume.offX1) + (costume.offX2)) / 16 ;
            int yt = ((y + ya) + (c / 2 * costume.offY1) + costume.offY2) / 16 ;


            if(level.getTile(xt,yt).solid())
            {
                solid = true;

            }

        }


        if(((x+xa - costume.OOBX)/16) < 0   || ((y+ya - costume.OOBY) /16) < 0  )
        {
           solid = true;
        }



        return solid;
    }

    protected void shoot_1(int x, int y, double dir)
    {
        Projectile p = new SpellProjectile_1(x,y,dir);

        level.add(p);
    }
    protected void shoot_2(int x, int y, double dir)
    {
        Projectile p = new SpellProjectile_2(x,y,dir);

        level.add(p);
    }

    public void init(Level level)
    {
        this.level = level;

        if(waterCheck(0,0))
        {
            level.add(new ParticleSpawner((x),(y) + 16 ,25, Sprite.particle_water ,25,level));
        }
    }
    public String getType()
    {
        return "Mob";
    }
}



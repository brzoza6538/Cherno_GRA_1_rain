package com.company.entity.mob;

import com.company.entity.Entity;
import com.company.entity.spawner.ParticleSpawner;
import com.company.entity.spawner.Spawner;
import com.company.entity.particle.Particle;
import com.company.entity.projectiles.Projectile;
import com.company.entity.projectiles.SpellProjectile_1;
import com.company.entity.projectiles.SpellProjectile_2;
import com.company.graphics.Sprite;

public abstract class Mob extends Entity
{
    protected Sprite sprite;
    protected int dir = -1;
    /*
     0 - N
     1 - E
     2 - S
     3 - W
    */
    protected boolean moving = false;

    public Mob()
    {
    }

    public void move(int xa, int ya)
    {

        if(xa > 0) dir = 1;
        if(xa < 0) dir = 3;
        if(ya > 0) dir = 2;
        if(ya < 0) dir = 0;

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
            level.add(new ParticleSpawner((x + xa),(y + ya) + 16 ,3, Sprite.particle_water , Particle.Dir.ZERO,15,level));
        }


    }

    public void update()
    {

    }

    public void render()
    {

    }

    private boolean waterCheck(int xa, int ya)
    {
        boolean water = false;

        for(int c = 0; c< 4; c++)
        {

            int xt = ((x + xa) + c % 2 * 3 - 1) / 16 ;
            int yt = ((y + ya) + c / 2 * 3 + 16 ) / 16 ;

            if(level.getTile(xt,yt).Name() == "PuddleTile"  && (((x + xa) % 16 >= 3 && (x + xa) % 16 <= 11) && ((y + ya) % 16 >= 3 && (y + ya) % 16 <= 11)))
            {
                //level.add(new Spawner((xt)*16 + 8,(yt)*16 - 8,Spawner.Type.PARTICLE, 10,level));
                water = true;
            }
        }
        return water;
    }
    private boolean collision(int xa, int ya)
    {
        //System.out.println(level.getTile(((x+xa)/16),((y+ya+10)/16)).Name() + " == " +  ((x+xa)/16) + " == " + (y+ya+10)/16);

            boolean solid = false;


        for(int c = 0; c< 4; c++)
        {
            int xt = ((x + xa) + c % 2 * 15 - 8) / 16 ;
            int yt = ((y + ya) + c / 2 * 15 -0) / 16 ;

            /*
                  int xt = ((x + xa) + c % 2 * 15 - 8) / 16 ;
            int yt = ((y + ya) + c / 2 * 18 - 3) / 16 ;
             */
            if(level.getTile(xt,yt).solid())
            {
                solid = true;

            }

        }


        if(((x+xa - 21)/16) < 0   || ((y+ya - 14) /16) < 0  )
        {
           solid = true;
        }



        return solid;
    }

    protected void shoot_1(int x, int y, double dir)
    {
        //dir = dir * 180 / Math.PI;
        Projectile p = new SpellProjectile_1(x,y,dir);

        level.add(p);
    }
    protected void shoot_2(int x, int y, double dir)
    {
        //dir = dir * 180 / Math.PI;
        Projectile p = new SpellProjectile_2(x,y,dir);

        level.add(p);
    }


}



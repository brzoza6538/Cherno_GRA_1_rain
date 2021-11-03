package com.company.entity.mob;

import com.company.entity.Entity;
import com.company.entity.projectiles.Projectile;
import com.company.entity.projectiles.SpellProjectile_1;
import com.company.entity.projectiles.SpellProjectile_2;
import com.company.graphics.Sprite;
import com.company.level.Level;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

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
    }

    public void update()
    {

    }

    public void render()
    {

    }

    private boolean collision(int xa, int ya)
    {
        //System.out.println(level.getTile(((x+xa)/16),((y+ya+10)/16)).Name() + " == " +  ((x+xa)/16) + " == " + (y+ya+10)/16);

        boolean solid = false;

        for(int c = 0; c< 4; c++)
        {
            int xt = ((x + xa) + c % 2 * 11 - 6) / 16 ;
            int yt = ((y + ya) + c / 2 * 14 - 0) / 16 ;

            if(level.getTile(xt,yt).solid())
            {
                solid = true;
            }

        }

        if(((x+xa - 18)/16 ) < 0  || ((x+xa+18)/16) > level.width || ((y+ya - 16) /16) < 0  || (((y+ya+32)/16) )> level.height)
        {
            solid = true;
        }


        return solid;
    }

    protected void shoot_1(int x, int y, double dir)
    {
        //dir = dir * 180 / Math.PI;
        Projectile p = new SpellProjectile_1(x,y,dir);

        level.addProjectile(p);
    }
    protected void shoot_2(int x, int y, double dir)
    {
        //dir = dir * 180 / Math.PI;
        Projectile p = new SpellProjectile_2(x,y,dir);

        level.addProjectile(p);
    }


}



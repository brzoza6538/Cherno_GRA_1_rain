package com.company.entity.particle;

import com.company.entity.Entity;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;

public class Particle extends Entity
{
    private Sprite sprite;
    private int TTL;
    protected double xx, yy, zz;
    protected double xa, ya, za;

    public enum ParticleType
    {
        WATER,STONE;
    }

    private ParticleType type;

    public Particle(int x, int y, int TTL, Sprite particletype)
    {
        this.x = x ;
        this.y = y ;

        this.xx = x ;
        this.yy = y ;

        this.TTL = TTL + (random.nextInt(20)-10);

        this.sprite = particletype;
/*
        if(dir == Dir.LEFT)
        {
            this.xa = Math.abs(random.nextGaussian()) * -1;
            this.ya = random.nextGaussian();
        }
        else if(dir == Dir.RIGHT)
        {
            this.xa = Math.abs(random.nextGaussian());
            this.ya = random.nextGaussian();
        }
        else if(dir == Dir.UP)
        {
            this.xa = random.nextGaussian();
            this.ya = Math.abs(random.nextGaussian()) * -1;
        }
        else if(dir == Dir.DOWN)
        {
            this.xa = random.nextGaussian();
            this.ya = Math.abs(random.nextGaussian());
        }
        else if(dir == Dir.ZERO)
        {
           this.xa = random.nextGaussian();
            this.ya = random.nextGaussian();
        }
 */
        this.xa = random.nextGaussian() ;
        this.ya = random.nextGaussian() ;


        //this.zz = random.nextFloat() + 2.0;
    }


    public void update()
    {
        TTL -= 1;

        if(TTL <= 0)
        {
            remove();
        }

        xa *= 0.95;
        ya *= 0.95;
/*
        za -= 0.1;
        if(zz < 0)
        {
            zz = 0;
            za *= -0.55;
            xa *= 0.4;
            ya *= 0.4;
        }

 */

        move(xx+ xa, (yy+ ya)  );// + (zz + za));

    }

    private void move(double x, double y)
    {
        if(collision(x,y))
        {
            this.xa *= -0.5;
            this.ya *= -0.5;
            //this.za *= -0.5;
        }
        this.xx += xa;
        this.yy += ya;
        //this.zz += za;
    }

    public boolean collision(double x, double y)
    {
        //System.out.println(level.getTile(((x+xa)/16),((y+ya+10)/16)).Name() + " == " +  ((x+xa)/16) + " == " + (y+ya+10)/16);

        boolean solid = false;

        for(int c = 0; c< 4; c++)
        {
            double xt = (x + (c % 2 * sprite.SIZE)) / 16;
            double yt = (y + (c / 2 * sprite.SIZE)) / 16;


            if(!level.getTile((int)xt,(int) yt).shootable_through())
            {
                solid = true;
            }

        }
        return solid;
    }

    public void render(Screen screen)
    {
        //screen.renderSprite((int)xx, (int)yy - (int) zz, sprite, true);
        screen.renderSprite((int)xx, (int)yy, sprite, true);

    }
}
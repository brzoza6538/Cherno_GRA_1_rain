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

    public enum Dir
    {
        RIGHT,LEFT,UP,DOWN,ZERO;
    }

    private ParticleType type;
    private Dir dir;

    public Particle(int x, int y, int TTL, Sprite particletype, Dir dir)
    {
        this.x = x;
        this.y = y;

        this.xx = x;
        this.yy = y;
        this.dir = dir;

        this.TTL = TTL + (random.nextInt(20)-10);

        this.sprite = particletype;

        if(dir == Dir.LEFT)
        {
            this.xa = Math.abs(random.nextGaussian()) * -1 - 0.5;
            this.ya = random.nextGaussian();
        }
        else if(dir == Dir.RIGHT)
        {
            this.xa = Math.abs(random.nextGaussian()) + 0.5;
            this.ya = random.nextGaussian();
        }
        else if(dir == Dir.UP)
        {
            this.xa = random.nextGaussian();
            this.ya = Math.abs(random.nextGaussian()) * -1 - 0.5;
        }
        else if(dir == Dir.DOWN)
        {
            this.xa = random.nextGaussian();
            this.ya = Math.abs(random.nextGaussian()) + 0.5;
        }
        else if(dir == Dir.ZERO)
        {
            this.xa = random.nextGaussian();
            this.ya = random.nextGaussian();
        }
        this.zz = 2.0;
    }


    public void update()
    {
        za -= 0.1;
        this.xx += xa;
        this.yy += ya;
        this.zz += za;

        TTL -= 1;

        if(TTL == 0)
        {
            remove();
        }
    }


    public void render(Screen screen)
    {
        screen.renderSprite((int)xx, (int)yy - (int)zz, sprite, true);
    }
}
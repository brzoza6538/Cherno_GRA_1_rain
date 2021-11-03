package com.company.entity.projectiles;

import com.company.entity.Entity;
import com.company.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity
{
    protected final int Xorigin, Yorigin;
    protected double angle;
    protected Sprite sprite;
    protected double nx,ny;
    protected double speed;
    protected double damage;
    protected double TTL;

    protected double x,y;
    protected int inverted = 1;

    protected final Random random = new Random();

    public Projectile(int x, int y, double dir)
    {
        Xorigin = x ;
        Yorigin = y ;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    protected void move()
    {

    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public int getSpriteSize()
    {
        return sprite.SIZE;
    }
}

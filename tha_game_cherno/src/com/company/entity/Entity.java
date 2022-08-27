package com.company.entity;

import com.company.entity.spawner.ParticleSpawner;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.level.Level;
import java.util.Random;

public class Entity
{
    protected Sprite sprite;

    protected int x,y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public  void update()
    {

    }

    public void render(Screen screen)
    {
        //
    }

    public void remove()
    {
        //pozbywanie sie
        removed = true;
    }

    public boolean isRemoved()
    {
        return removed;
    }

    public void init(Level level)
    {
        this.level = level;
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}

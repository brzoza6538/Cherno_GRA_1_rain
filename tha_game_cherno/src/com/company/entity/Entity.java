package com.company.entity;

import com.company.graphics.Screen;
import com.company.level.Level;
import java.util.Random;

public abstract class Entity
{
    public int x,y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update()
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
}

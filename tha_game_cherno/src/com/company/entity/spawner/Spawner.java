package com.company.entity.spawner;

import com.company.entity.Entity;
import com.company.entity.particle.Particle;
import com.company.graphics.Sprite;
import com.company.level.Level;

public abstract class Spawner extends Entity
{

    public Spawner (int x, int y, Sprite particletype, int amount, Level level)
    {
        init(level);
        this.x = x;
        this.y = y;


    }
}
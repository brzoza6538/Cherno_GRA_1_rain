package com.company.entity.spawner;

import com.company.entity.particle.Particle;
import com.company.graphics.Sprite;
import com.company.level.Level;

public class ParticleSpawner extends Spawner
{
    private int TTL;

    public ParticleSpawner(int x, int y, int TTL, Sprite particletype, int amount, Level level)
    {
        super(x, y,  particletype, amount, level);
        this.TTL = TTL;

        for(int i = 0 ; i < amount;i ++)
        {
            level.add(new Particle(x,y,TTL, particletype));
        }
    }
}

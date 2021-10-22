package com.company.entity.projectiles;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;

public class SpellProjectile_1 extends Projectile
{
    public SpellProjectile_1 (int x, int y, double dir)
    {
        super(x,y,dir);
        range = random.nextInt(100) + 100;
        damage = 20;
        rateOfFire = 15;
        speed = 2.3;

        sprite = Sprite.spell_projetile;
        nx = Math.cos(angle) * speed;
        ny = Math.sin(angle) * speed;
    }

    public void update()
    {
        move();
    }

    protected void move()
    {
        x += nx;
        y += ny;
        if(distance() > range)
        {
            remove();
        }

    }

    private double distance()
    {
        return Math.sqrt((Xorigin - x) * (Xorigin - x) + (Yorigin - y)*(Yorigin - y));
    }

    public void render(Screen screen)
    {
        screen.renderProjectile((int)x - 12,(int)y , this);
    }
}

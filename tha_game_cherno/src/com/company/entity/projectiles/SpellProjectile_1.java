package com.company.entity.projectiles;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;

public class SpellProjectile_1 extends Projectile
{
    public static final int FIRE_RATE = 25; //czas pomiedzy strzalami
    public SpellProjectile_1 (int x, int y, double dir)
    {
        super(x,y,dir);
        //range = random.nextInt(50) + 300;
        TTL = random.nextInt(50) + 250;
        damage = 20;
        speed = 3.5;
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
        if(  level.tileCollision(x,y,nx,0,15) )
        {
            TTL = TTL * 9 /10;
            speed = speed * 9 /10;
            angle = Math.PI  - angle;

            nx = Math.cos(angle) * speed;
            ny = Math.sin(angle) * speed;
        }

        if(  level.tileCollision(x,y,0,ny,15) )
        {
            TTL = TTL * 9 /10;
            speed = speed * 9 /10;
            angle = Math.PI *2 - angle;

            nx = Math.cos(angle) * speed;
            ny = Math.sin(angle) * speed;
        }

        x = x + (nx);
        y = y + (ny);

        if(TTL <= 0)
        {
            remove();
        }
        TTL -= 1;
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

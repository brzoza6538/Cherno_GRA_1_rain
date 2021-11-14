package com.company.entity.projectiles;

import com.company.entity.spawner.ParticleSpawner;
import com.company.entity.spawner.Spawner;
import com.company.entity.particle.Particle;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;

public class SpellProjectile_1 extends Projectile
{
    public static final int FIRE_RATE = 10; //czas pomiedzy strzalami

    private static final int SIZE = 6;

    private static final int offset = 5;

    public SpellProjectile_1 (int x, int y, double dir)
    {
        super(x,y,dir);
        //angle = angle - ( (double)random.nextInt(10) / 100) + (double)5/100;

        TTL = random.nextInt(20) + 70;
        damage = 20;
        speed = 3.3;
        sprite = Sprite.spell_projetile_1;
        nx = Math.cos(angle) * speed;
        ny = Math.sin(angle) * speed;

    }

    public void update()
    {
        move();
    }

    protected void move()
    {

        if(  level.Up_TileCollision((int)(x+nx),(int)(y),SIZE,offset,offset) )
        {
            //TTL = TTL *  (random.nextInt(20) + 30)/ 100;
            speed =  speed * 8 / 10;
            angle = Math.PI  - angle;
            damage = damage * 7 / 10;

            nx = Math.cos(angle) * speed;
            ny = Math.sin(angle) * speed;

            if(nx < 0 )
            {
                level.add(new ParticleSpawner((int)(x + nx + SIZE * 2),(int)(y + ny + SIZE),15, Sprite.particle_stone ,  Particle.Dir.LEFT,15,level));
            }
            else if (nx > 0)
            {
                level.add(new ParticleSpawner((int)(x + nx),(int)(y + ny+ SIZE),15, Sprite.particle_stone ,  Particle.Dir.RIGHT,15,level));
            }
        }

        if(  level.Up_TileCollision((int)x,(int)(y+ny),SIZE,offset,offset) )
        {
            //TTL = TTL *  (random.nextInt(20) + 30)/ 100;
            speed = speed   * 8 / 10;
            angle = Math.PI * 2 - angle;
            damage = damage * 7 / 10;

            nx = Math.cos(angle) * speed;
            ny = Math.sin(angle) * speed;

            if(ny > 0 )
            {
                level.add(new ParticleSpawner((int)(x + nx + SIZE),(int)(y + ny),15, Sprite.particle_stone ,  Particle.Dir.DOWN,15,level));
            }
            else if (ny < 0)
            {
                level.add(new ParticleSpawner((int)(x + nx + SIZE),(int)(y + ny + SIZE * 2),15, Sprite.particle_stone ,  Particle.Dir.UP,15,level));
            }
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

        screen.renderProjectile((int)x,(int)y, this); // dla 16x16

    }

    //double xt = ((x + xa) + c % 2 * (size - 2) - 11) / 16 ;
    //double yt = ((y + ya) + c / 2 * (size - 10) + 4) / 16 ; - dla granatu 16 x 16

    //double xt = ((x + xa) + c % 2 * (size - 2) - 6) / 16 ;
    //double yt = ((y + ya) + c / 2 * (size - 6) + 6 ) / 16 ; - dla pocisku 6x6
}

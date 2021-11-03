package com.company.entity.projectiles;

import com.company.Main;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;

public class SpellProjectile_1 extends Projectile
{
    public static final int FIRE_RATE = 20; //czas pomiedzy strzalami
    public SpellProjectile_1 (int x, int y, double dir)
    {
        super(x,y,dir);
        //angle = angle - ( (double)random.nextInt(10) / 100) + (double)5/100;

        //range = random.nextInt(50) + 300;
        TTL = random.nextInt(50) + 250;
        damage = 20;
        speed = 2.5;
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
        if(  level.tileCollision(x,y,nx,0,16) )
        {
            TTL = TTL *  (random.nextInt(8) + 7)/ 100;
            speed =  speed * 5 / 10;
            angle = Math.PI  - angle;
            damage = damage * 7 / 10;

            nx = Math.cos(angle) * speed;
            ny = Math.sin(angle) * speed;
        }

        if(  level.tileCollision(x,y,0,ny,16) )
        {
            TTL = TTL *  (random.nextInt(8) + 7)/ 100;
            speed = speed   * 5 / 10;
            angle = Math.PI * 2 - angle;
            damage = damage * 7 / 10;

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

        screen.renderProjectile((int)x - 8,(int)y - 8, this); // dla 16x16

    }

    //double xt = ((x + xa) + c % 2 * (size - 2) - 11) / 16 ;
    //double yt = ((y + ya) + c / 2 * (size - 10) + 4) / 16 ; - dla granatu 16 x 16

    //double xt = ((x + xa) + c % 2 * (size - 2) - 6) / 16 ;
    //double yt = ((y + ya) + c / 2 * (size - 6) + 6 ) / 16 ; - dla pocisku 6x6
}

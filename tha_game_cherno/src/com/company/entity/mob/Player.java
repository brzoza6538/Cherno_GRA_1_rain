package com.company.entity.mob;

import com.company.Main;
import com.company.entity.projectiles.Projectile;
import com.company.entity.projectiles.SpellProjectile_1;
import com.company.entity.projectiles.SpellProjectile_2;
import com.company.entity.spawner.ParticleSpawner;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.input.Keyboard;
import com.company.input.Mouse;
import com.company.level.Level;

public class Player extends Mob
{
    private int anim = 0;
    private boolean walking = false;
    private int SPEED = 2;
    private Keyboard input_k;

    private int fireRate = 0;

    private Sprite sprite = Sprite.player_S;


    public Player(int x, int y, Keyboard input)
    {
        this.x = x;
        this.y = y;
        this.input_k = input;

        fireRate = SpellProjectile_1.FIRE_RATE;

    }
    public Player(Keyboard input)
    {
        x = 0;
        y = 0;

        this.input_k = input;
        fireRate = SpellProjectile_1.FIRE_RATE;

    }

    public void update()
    {
        if(fireRate > 0)
        {
            fireRate -- ;
        }

        int xa = 0, ya =0;

        if(input_k.up)ya = ya - SPEED;
        if(input_k.down) ya = ya + SPEED;
        if(input_k.right) xa = xa + SPEED;
        if(input_k.left) xa = xa - SPEED;


        if(xa != 0 || ya != 0)
        {
            walking = true;
        }
        else{walking = false;}


        if(xa != 0 || ya != 0) move(xa,ya);

         updateShooting();
    }


    private void updateShooting()
    {
        if( (Mouse.getButton() == 1 || Mouse.getButton() == 3) && fireRate <= 0)
        {
            double dx = Mouse.getX() - ((Main.getWindowWidth() /2)) ; //+ (4 * Main.getScale() ); // width * scale
            double dy = Mouse.getY() - (((Main.getWindowWidth() / 16 * 9) / 2)) - (6 * Main.getScale() ); //- (4 * Main.getScale() );
            double dir = Math.atan2(dy,dx);

            //shoot(x - 4,y + 4,dir);
            if( Mouse.getButton() == 1)
            {
                if( ! level.Up_TileCollision((int)(x-8),(int)(y-2),SpellProjectile_1.SIZE,SpellProjectile_1.offset,SpellProjectile_1.offset))
                {
                    shoot_1(x - 8,y - 2,dir); // from where, to where   /musisz skonczyc na - 8 - 8
                    fireRate = SpellProjectile_1.FIRE_RATE;
                }
            }
            if( Mouse.getButton() == 3)
            {
                if( ! level.Up_TileCollision((int)(x-8),(int)(y-2),SpellProjectile_2.SIZE,SpellProjectile_2.offset,SpellProjectile_2.offset))
                {
                    shoot_2(x - 8, y - 2, dir);
                    fireRate = SpellProjectile_2.FIRE_RATE;
                }
            }
        }
    }

    public void render(Screen screen)
    {
        if(anim < 1000) {anim++;}
        else{anim = 0;}

    /*
     0 - N
     1 - E
     2 - S
     3 - W
    */
        if(walking == false)
        {
            if(dir == 0) {sprite = Sprite.player_N;}
            if(dir == 1) {sprite = Sprite.player_W;}
            if(dir == 2) {sprite = Sprite.player_S;}
            if(dir == 3) {sprite = Sprite.player_E;}
        }
        else if (anim % 20 > 10)
        {
            if(dir == 0) {sprite = Sprite.player_N_r;}
            if(dir == 1) {sprite = Sprite.player_W_r;}
            if(dir == 2) {sprite = Sprite.player_S_r;}
            if(dir == 3) {sprite = Sprite.player_E_r;}
        }
        else
        {
            if(dir == 0) {sprite = Sprite.player_N_l;}
            if(dir == 1) {sprite = Sprite.player_W_l;}
            if(dir == 2) {sprite = Sprite.player_S_l;}
            if(dir == 3) {sprite = Sprite.player_E_l;}
        }


        screen.renderPlayer(x-16,y - 16,sprite,false,false); // zrobilees obwodke wokol mapy


    }
}

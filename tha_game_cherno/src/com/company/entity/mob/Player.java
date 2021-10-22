package com.company.entity.mob;

import com.company.Main;
import com.company.entity.projectiles.Projectile;
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

    private Sprite sprite = Sprite.player_S;


    public Player(int x, int y, Keyboard input)
    {
        this.x = x;
        this.y = y;
        this.input_k = input;

    }
    public Player(Keyboard input)
    {
        x = 0;
        y = 0;
        this.input_k = input;
    }

    public void update()
    {

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

        clear();
        updateShooting();
    }
    private void clear()
    {
        for(int i = 0; i < level.getProjectiles().size(); i ++)
        {
            Projectile p = level.getProjectiles().get(i);
            if(p.isRemoved())
            {
                level.getProjectiles().remove(i);
            }
        }
    }

    private void updateShooting()
    {
        if(Mouse.getButton() == 1)
        {
            double dx = Mouse.getX() - ((Main.getWindowWidth() /2) );//+ 12); // width * scale
            double dy = Mouse.getY() - (((Main.getWindowWidth() / 16 * 9) / 2) );//+ 12);
            double dir = Math.atan2(dy,dx);

            shoot(x,y,dir); // from where, to where
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

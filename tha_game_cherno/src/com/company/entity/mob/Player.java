package com.company.entity.mob;

import com.company.Main;
import com.company.entity.projectiles.SpellProjectile_1;
import com.company.entity.projectiles.SpellProjectile_2;
import com.company.graphics.Screen;
import com.company.input.Keyboard;
import com.company.input.Mouse;
import com.company.objects.costumes.*;

import java.lang.Math;

public class Player extends Mob
{
    private int anim = 0;
    private long time_now = System.currentTimeMillis();
    private long time_then = System.currentTimeMillis();

    private double MouseX;
    private double MouseY;
    public double direction;
    private int SPEED = 2;
    private Keyboard input_k;

    //////////////////////////tymczasowe
    private int costumeRotation = 100;
    ///////////////////////////////

    private int fireRate = 0;


    private int FPS = 8;

    public Player(int x, int y, Keyboard input)
    {
        this.x = x;
        this.y = y;
        this.input_k = input;

        fireRate = SpellProjectile_1.FIRE_RATE;

        costume = new Basic();
        currentAnim = costume.down;

    }
    public Player(Keyboard input)
    {
        x = 1;
        y = 1;

        this.input_k = input;
        fireRate = SpellProjectile_1.FIRE_RATE;

        costume = new Basic();
        currentAnim = costume.down;


    }

    public void update()
    {
        MouseX = Mouse.getX() - ((Main.getWindowWidth() /2)) ; //+ (4 * Main.getScale() ); // width * scale
        MouseY = Mouse.getY() - (((Main.getWindowWidth() / 16 * 9) / 2)) - (6 * Main.getScale() ); //- (4 * Main.getScale() );

/////////////////////////////////////////tymczasowo
        //System.out.println(costumeRotation + " = = " +  Mouse.getRotation());

        if(!Mouse.rotationAlreadyUsed())
        {
            costumeRotation += Mouse.getRotation();
            Mouse.rotationUsed();


            if (costumeRotation >= 9999 || costumeRotation <= 1) {
                costumeRotation = costumeRotation % 6;
            }

            if (costumeRotation % 6 == 0) {costume = new Basic();}
            else if (costumeRotation % 6 == 1) {costume = new Baboon();}
            else if (costumeRotation % 6 == 2) {costume = new Scafander("clean");}
            else if (costumeRotation % 6 == 3) {costume = new Scafander("waist");}
            else if (costumeRotation % 6 == 4) {costume = new Scafander("chest");}
            else if (costumeRotation % 6 == 5) {costume = new StolenSprite();}

        }
        else
        {
            //costume = new Basic();
        }
//////////////////////////////////////////////////

        if(walking)
        {
            currentAnim.update();
        }
        else
        {
            currentAnim.setFrame(0);
        }

        if(MouseY <= - Math.abs(MouseX)) {currentAnim = costume.up;}
        else if(Math.abs(MouseY) < MouseX) {currentAnim = costume.right;}
        else if(MouseY >= Math.abs(MouseX)) {currentAnim = costume.down;}
        else if(- Math.abs(MouseY) > MouseX) {currentAnim = costume.left;}

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
        direction = Math.atan2(MouseY,MouseX);
        //System.out.println(direction);
        if( (Mouse.getButton() == 1 || Mouse.getButton() == 3) && fireRate <= 0)
        {

            //shoot(x - 4,y + 4,dir);

            if( Mouse.getButton() == 1)
            {
                if( ! level.Up_TileCollision((int)(x-8),(int)(y-2),SpellProjectile_1.SIZE,SpellProjectile_1.offset,SpellProjectile_1.offset))
                {
                    shoot_1(x - 8,y - 2,direction); // from where, to where   /musisz skonczyc na - 8 - 8
                    fireRate = SpellProjectile_1.FIRE_RATE;
                }
            }

            if( Mouse.getButton() == 3)
            {
                if( ! level.Up_TileCollision((int)(x-8),(int)(y-2),SpellProjectile_2.SIZE,SpellProjectile_2.offset,SpellProjectile_2.offset))
                {
                    shoot_2(x - 8, y - 2, direction);
                    fireRate = SpellProjectile_2.FIRE_RATE;
                }
            }
        }
    }

    public void render(Screen screen)
    {
        //  https://www.desmos.com/calculator/pew0rbns49?lang=pl
    /*
     0 - N
     1 - E
     2 - S
     3 - W - lewo
    */
        sprite = currentAnim.getSprite();
        screen.renderMob(x-16,y - 16,sprite,false,false);


    }
/*
    public static double getdirection()
    {
        return  Math.atan2(MouseY,MouseX);
    }
  */
}

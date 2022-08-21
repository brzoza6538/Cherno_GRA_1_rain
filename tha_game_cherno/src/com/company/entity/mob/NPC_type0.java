package com.company.entity.mob;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.level.Level;
import com.company.level.SpawnLevel;
import com.company.objects.costumes.Baboon;
import com.company.objects.costumes.Scafander;

public class NPC_type0 extends Mob
{


    public NPC_type0(int x, int y)
    {
        //System.out.println("npc");
        this.x = x;
        this.y = y;
        costume = new Scafander();
        currentAnim = costume.down;
    }

    @Override
    public void update()
    {
        int xa = 0;
        int ya = 0;
        if(walking)
        {
            currentAnim.update();
        }
        else
        {
            currentAnim.setFrame(0);
        }

        if(ya < 0)
        {
            dir = Direction.UP;
            ya = ya - SPEED;
            currentAnim = costume.up;
        }
        if(ya > 0)
        {
            dir = Direction.DOWN;
            ya = ya + SPEED;
            currentAnim = costume.down;
        }
        if(xa > 0)
        {
            dir = Direction.RIGHT;
            currentAnim = costume.right;
            xa = xa + SPEED;
        }
        if(xa < 0)
        {
            dir = Direction.LEFT;
            currentAnim = costume.left;
            xa = xa - SPEED;
        }

        if(xa != 0 || ya != 0)
        {
            move(xa,ya);
            walking = true;
        }
        else{walking = false;}


    }

    @Override
    public void render(Screen screen)
    {
        sprite =  currentAnim.getSprite();
        screen.renderMob(x-16,y-16,sprite, false, false);//currentAnim.getSprite()

    }


}

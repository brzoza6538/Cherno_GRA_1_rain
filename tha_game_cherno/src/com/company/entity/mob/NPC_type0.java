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
        this.x = x << 4;
        this.y = y << 4;
        costume = new Scafander();
        currentAnim = costume.up;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Screen screen)
    {
        sprite =  currentAnim.getSprite();
        screen.renderMob(x-8,y-24,sprite, false, false);//currentAnim.getSprite()

    }


}

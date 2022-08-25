package com.company.entity.mob;

import com.company.entity.spawner.ParticleSpawner;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.level.Level;
import com.company.level.SpawnLevel;
import com.company.objects.costumes.Baboon;
import com.company.objects.costumes.Scafander;

public class NPC_type0 extends Mob
{
    protected int SPEED = 1;
    private int time =0;
    private int xa = 0;
    private int ya = 0;

    private boolean Xcollision = false;
    private boolean Ycollision = false;


    public NPC_type0(int x, int y)
    {
        //System.out.println("npc");
        this.x = x;
        this.y = y;
        costume = new Scafander();
        currentAnim = costume.down;
    }

    public void AI()
    {
        time++;

        if(time > 100000 && time % 10 == 0)
        {
            time = 0;
        }
        if(time % (random.nextInt(90) + 30) == 1)
        {
            xa = random.nextInt(3) - 1;
            ya= random.nextInt(3) - 1;
            if(random.nextInt(10) == 0)
            {
                xa =0;
                ya =0;
            }

        }
        if(Xcollision == true)
        {
            xa = -xa;
            Xcollision = false;
        }
        if(Ycollision == true)
        {
            ya = -ya;
            Ycollision = false;
        }
        //w mainie robisz to zawsze 60 razy na sekunde
        /*
        if((time % 180)  >= 0 && (time % 180) <= 89)
        {
            xa = -1;
        }
        else
        {
            xa = 1;
        }

        if((time % 180)  >= 45 && (time % 180) <= 134)
        {
            ya = 1;
        }
        else
        {
            ya = -1;
        }
        */
    }
    @Override
    public void update()
    {
        AI();

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
            ya = ya * SPEED;
            currentAnim = costume.up;
        }
        if(ya > 0)
        {
            dir = Direction.DOWN;
            ya = ya * SPEED;
            currentAnim = costume.down;
        }
        if(xa > 0)
        {
            dir = Direction.RIGHT;
            currentAnim = costume.right;
            xa = xa * SPEED;
        }
        if(xa < 0)
        {
            dir = Direction.LEFT;
            currentAnim = costume.left;
            xa = xa * SPEED;
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

    public void     move(int xa, int ya)
    {

        if(xa > 0) dir = Direction.RIGHT;
        if(xa < 0) dir = Direction.LEFT;
        if(ya > 0) dir = Direction.DOWN;
        if(ya < 0) dir = Direction.UP;

        if(! collision(xa,0))
        {
            x += xa;
        }
        else
        {
            //x -= xa;
            Xcollision = true;
        } // w kazdej nowej rundzie dane sie i tak resetuja

        if(! collision(0,ya))
        {
            y += ya;
        }
        else
        {
            //y -= ya;
            Ycollision = true;

        }

        if(waterCheck(xa,ya))
        {
            level.add(new ParticleSpawner((x + xa),(y + ya) + 16 ,7, Sprite.particle_water ,7,level));
        }


    }

}

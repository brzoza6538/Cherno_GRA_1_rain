package com.company.entity.mob;

import com.company.entity.spawner.ParticleSpawner;
import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.objects.costumes.Baboon;
import com.company.objects.costumes.Scafander;

public class NPC_Chaser extends Mob
{
    protected int SPEED = 1;
    private int time =0;
    private int xa = 0;
    private int ya = 0;

    private static final int collision_timer = 10;
    private int Xcollision = 0;
    private int Ycollision = 0;

    public NPC_Chaser(int x, int y)
    {
        //System.out.println("npc");
        this.x = x;
        this.y = y;
        costume = new Scafander("clean");
        currentAnim = costume.down;
    }

    private void AI()
    {
        Player player = level.getClientPlayer();
        int wx = player.getX() - x;
        int wy = player.getY() - y;

        if(wy <= - Math.abs(wx)) {currentAnim = costume.up;}
        else if(Math.abs(wy) < wx) {currentAnim = costume.right;}
        else if(wy >= Math.abs(wx)) {currentAnim = costume.down;}
        else if(- Math.abs(wy) > wx) {currentAnim = costume.left;}


        time++;

        if(time > 100000 && time % 10 == 0)
        {
            time = 0;
        }

        if(time % (random.nextInt(20) + 10) == 1)
        {
            if(player.getX() > x && Xcollision == 0)
            {
                xa = 1;
            }
            else if(player.getX() < x && Xcollision == 0)
            {
                xa = -1;
            }

            if(player.getY() > y && Ycollision == 0)
            {
                ya = 1;
            }
            else if(player.getY() < y && Ycollision == 0)
            {
                ya = -1;
            }

            if(Xcollision != 0)
            {
                xa = random.nextInt(3) - 1;
                Xcollision --;
            }
            if(Ycollision != 0)
            {
                ya= random.nextInt(3) - 1;
                Ycollision --;
            }
        }




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
            //currentAnim = costume.up;
        }
        if(ya > 0)
        {
            dir = Direction.DOWN;
            ya = ya * SPEED;
            //currentAnim = costume.down;
        }
        if(xa > 0)
        {
            dir = Direction.RIGHT;
            //currentAnim = costume.right;
            xa = xa * SPEED;
        }
        if(xa < 0)
        {
            dir = Direction.LEFT;
            //currentAnim = costume.left;
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
        screen.renderMob(x-16,y-16,this);//currentAnim.getSprite()

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
            Xcollision = collision_timer;
        }

        if(! collision(0,ya))
        {
            y += ya;
        }
        else
        {
            Ycollision = collision_timer;
        }


        if(waterCheck(xa,ya))
        {
            level.add(new ParticleSpawner((x + xa),(y + ya) + 16 ,7, Sprite.particle_water ,7,level));
        }


    }

    @Override
    public String getType()
    {
        return "Chaser";
    }
}

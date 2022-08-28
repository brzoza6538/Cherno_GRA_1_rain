
package com.company.graphics;

public class AnimatedSprite extends Sprite
{

    private int frame = 0;
    private Sprite sprite;
    private int rate = 6;
    private int animationLength = -1; // ile klatek
    private int time = 0;

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int animationLength)
    {
        super(sheet,width, height);

        this.animationLength = animationLength;

        sprite = sheet.getSprites()[0];


        if(animationLength > sheet.getSprites().length)
        {
            System.err.println("ERROR! za długa animacja, za mało klatek");
        }


    }
    public void update()
    {
        time++;
        if(time % rate == 0)
        {
            if (frame >= animationLength - 1) {frame = 0;}
            else {frame++;}

            sprite = sheet.getSprites()[frame];
        }

    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public void setFrameRate(int frames)
    {
        rate = frames;
    }

    public void setFrame(int i)
    {
        sprite = sheet.getSprites()[i];
    }
}

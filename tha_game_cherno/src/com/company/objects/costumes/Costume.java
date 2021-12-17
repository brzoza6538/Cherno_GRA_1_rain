
package com.company.objects.costumes;


import com.company.graphics.AnimatedSprite;
import com.company.graphics.Sprite;
import com.company.graphics.SpriteSheet;

public abstract class Costume
{
    //collision data
    public int offX1=1;
    public int offX2=0;
    public int offY1=1;
    public int offY2=0;

    public int OOBX=23;
    public int OOBY=14; // out_of_bounds

    public static SpriteSheet costumeSprite;

    public static SpriteSheet sprite_down;
    public static SpriteSheet sprite_right;
    public static SpriteSheet sprite_up;
    public static SpriteSheet sprite_left;

    public AnimatedSprite down ;
    public AnimatedSprite right;
    public AnimatedSprite up ;
    public AnimatedSprite left ;

    public Costume()
    {

    }

}


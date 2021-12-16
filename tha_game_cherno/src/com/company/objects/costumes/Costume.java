package com.company.objects.costumes;


import com.company.graphics.Sprite;
import com.company.graphics.SpriteSheet;

public abstract class Costume
{
    //collision
    public int offX1=1;
    public int offX2=0;
    public int offY1=1;
    public int offY2=0;

    public int OOBX=23;
    public int OOBY=14; // out_of_bounds

    public static SpriteSheet costumeSprite;

    public static Sprite player_N_0;
    public static Sprite player_W_0;
    public static Sprite player_S_0;
    public static Sprite player_E_0;

    public static Sprite player_N_1;
    public static Sprite player_W_1;
    public static Sprite player_S_1;
    public static Sprite player_E_1;

    public static Sprite player_N_2;
    public static Sprite player_W_2;
    public static Sprite player_S_2;
    public static Sprite player_E_2;

    public static Sprite player_N_3;
    public static Sprite player_W_3;
    public static Sprite player_S_3;
    public static Sprite player_E_3;

    public Costume()
    {

    }

}

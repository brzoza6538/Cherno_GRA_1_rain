package com.company.level.tile;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.level.tile.spawn_level.*;

public class Tile
{
    public int x, y;
    public Sprite sprite;

    public static Tile spawn_grass_1 = new GrassTile(Sprite.spawn_grass_1);
    public static Tile spawn_grass_2 = new GrassTile(Sprite.spawn_grass_2);

    public static Tile spawn_flowers = new FlowerTile(Sprite.spawn_flowers);
    public static Tile spawn_rock_1 = new Rock_1Tile(Sprite.spawn_rock_1);
    public static Tile spawn_rock_2 = new Rock_2Tile(Sprite.spawn_rock_2);
    public static Tile spawn_puddle = new PuddleTile(Sprite.spawn_puddle);

    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public final static int col_spawn_grass_1 = 0xff00ff21;
    public final static int col_spawn_grass_2 = 0xffb6ff00;
    public final static int col_spawn_flowers = 0xffff00dc;
    public final static int col_spawn_rock_1 = 0xff404040;
    public final static int col_spawn_rock_2 = 0xff9c9c9c;
    public final static int col_spawn_puddle = 0xff00ffff;
    public final static int col_void = 0xffff0000;

    public Tile(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public String Name()
    {
        return null;
    }


    public void render(int  x, int y, Screen screen)
    {

    }

    public boolean solid()
    {
        return true; // default
    }
    public boolean breakable()
    {
        return false; // default
    }

}

package com.company.graphics;

public class Sprite
{
    public final int SIZE;
    private int x,y;
    private int width, height;
    public int[] pixels;

    private SpriteSheet sheet;


    /////////////////////////////////spawn level
    public static Sprite spawn_grass_1 = new Sprite(16,0,0, SpriteSheet.spawn_level);
    public static Sprite spawn_grass_2 = new Sprite(16,1,0, SpriteSheet.spawn_level);

    public static Sprite spawn_flowers = new Sprite(16,2,0, SpriteSheet.spawn_level);
    public static Sprite spawn_rock_1 = new Sprite(16,0,1, SpriteSheet.spawn_level);
    public static Sprite spawn_rock_2 = new Sprite(16,1,1, SpriteSheet.spawn_level);
    public static Sprite spawn_puddle = new Sprite(16,2,1, SpriteSheet.spawn_level);

    public static Sprite voidSprite = new Sprite(16, 0x0aa0f0);

    /////////////////////////////////player
    public static Sprite player_N = new Sprite(32,2,5, SpriteSheet.tiles);
    public static Sprite player_W = new Sprite(32,1,5, SpriteSheet.tiles);
    public static Sprite player_S = new Sprite(32,0,5, SpriteSheet.tiles);
    public static Sprite player_E = new Sprite(32,3,5, SpriteSheet.tiles);

    public static Sprite player_N_r = new Sprite(32,2,6, SpriteSheet.tiles);
    public static Sprite player_W_r = new Sprite(32,1,6, SpriteSheet.tiles);
    public static Sprite player_S_r = new Sprite(32,0,6, SpriteSheet.tiles);
    public static Sprite player_E_r = new Sprite(32,3,6, SpriteSheet.tiles);

    public static Sprite player_N_l = new Sprite(32,2,7, SpriteSheet.tiles);
    public static Sprite player_W_l = new Sprite(32,1,7, SpriteSheet.tiles);
    public static Sprite player_S_l = new Sprite(32,0,7, SpriteSheet.tiles);
    public static Sprite player_E_l = new Sprite(32,3,7, SpriteSheet.tiles);

/////////////////////////////////////////projecitles

    public static Sprite spell_projetile_1 = new Sprite(16,0,0, SpriteSheet.spell_projetiles);
    public static Sprite spell_projetile_2 = new Sprite(16,1,0, SpriteSheet.spell_projetiles);
/////////////////////////////////////////particles
    public static Sprite particle_water = new Sprite(2,0x30A8FF);
    public static Sprite particle_stone = new Sprite(2,0xAAAFAFAB);


    public Sprite(int size, int x , int y, SpriteSheet sheet)
    {
        this.SIZE = size;
        this.width = size;
        this.height = size;

        pixels = new int [SIZE*SIZE];
        this.x = x*size;
        this.y = y*size;
        //od kiedy zaczac - wszystko jest podzielone gridem
        this.sheet = sheet;
        load();
// nie potrzebne width,height,x,y?
    }

    public Sprite(int size, int color)
    {
        this.SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];

        setColor(color);
    }
    public Sprite(int width, int height, int color)
    {
        SIZE = -1;
        this.width = width;
        this.height = height;

        pixels = new int[height * width];

        setColor(color);
    }

    private void setColor(int color)
    {
        for(int i = 0; i < width * height; i++)
        {
            pixels[i] = color;
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    private void load()
    {
        for(int y = 0; y < SIZE; y++)
        {
            for(int x = 0; x < SIZE; x++)
            {
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

}

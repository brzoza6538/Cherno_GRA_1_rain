package com.company.level;

import com.company.level.tile.Tile;

import java.util.Random;


public class RandomLevel extends Level // nie ma default pustego constructora = blad
{

    private static final Random random = new Random();

    public RandomLevel(int width, int height)
    {
        super(width, height); // przewija do constructora w originalnej klasie
        spawnX = width/2;
        spawnY = height/2;


    }


    protected void generateLevel() // nadpisuje metode originalnej klasy
    {
        for(int y = 0 ; y < height; y++)
        {
            for(int x = 0 ; x < width; x++)
            {
                tilesInt[x + y*width]  = random.nextInt(8); // 0-3
            }
        }
    }

    protected void generateLevel(boolean xy, int x0, int y0 , int x1, int y1)
    {
        //xy = (xy+1)%2; 0,1,0,1,0,1,0,1,0...  - musi na zmiane robic linie pionowe i poziome
        //tablica[x][y]
        if(xy == true)//dla pionowej lini
        {
            if((Math.abs(y0-y1) > 3) && (Math.abs(x0-x1) > 3))
            {
                int sciana = random.nextInt(Math.abs(x1 - x0 -2)) + 1+x0;

                int przejscie = random.nextInt ( Math.abs(y1 - y0 -2)) + 1+y0;

                for(int i =y0; i <= y1; i++)
                {
                    if(i != przejscie)
                    {
                        tilesInt[sciana + Math.abs(i) * width] = 0;
                    }
                }
                tilesInt[sciana + przejscie* width] = random.nextInt(500)+1;

                generateLevel(!xy, x0, y0, sciana -1, przejscie-1);
                generateLevel(!xy, sciana +1, y0, x1, przejscie -1);
                generateLevel(!xy, x0, przejscie+1, sciana -1, y1);
                generateLevel(!xy, sciana+1, przejscie+1, x1, y1);
            }
        }
////////////////////////////////////////////////////////////////
        if(xy == false)//poziomej
        {
            if((Math.abs(y0-y1) > 3) && (Math.abs(x0-x1) > 3))
            {
                int sciana = random.nextInt (  Math.abs(y1 - y0 - 2)) + 1+y0;

                int przejscie = random.nextInt (  Math.abs(x1 - x0 -2)) + 1+x0;


                for(int i = x0; i <= x1 ; i++)
                {
                    tilesInt[Math.abs(i) + sciana* width]=0;
                }
                tilesInt[przejscie + sciana* width] = random.nextInt(500)+1;


                generateLevel(!xy, x0, y0, przejscie -1, sciana-1);
                generateLevel(!xy,  przejscie +1, y0, x1, sciana -1);
                generateLevel(!xy ,x0, sciana+1, przejscie -1, y1);
                generateLevel(!xy, przejscie+1, sciana+1, x1, y1);
            }
        }

    }
    public Tile getTile(int x, int y)
    {
        ///////////////////////////////  spawn dome

        // żeby łeb ci sie nie zaklinował na collision detection ściany
        for(int ys = 0; ys < 5; ys ++)
        {
            for(int xs = 0; xs < 5; xs ++)
            {
                if(xs != 5/2 && ys != 5/2)
                {
                    tilesInt[width/2 - 5/2  + xs + (height/2 - 5/2 + ys)* width] = 0;
                }
                else
                {
                    tilesInt[width/2 - 5/2  + xs + (height/2 - 5/2 + ys)* width] = 5;
                }
                if(xs <4 && xs > 0  && ys <4 && ys > 0)
                {
                    tilesInt[width/2 - 5/2  + xs + (height/2 - 5/2 + ys)* width] = 3;
                }

            }
        }
        tilesInt[width/2 + height/2 * width] = 4; // kałuża

        ///////////////////////////////  spawn dome

        if (x < 0 || y < 0 || x >= width || y >= height) {return Tile.voidTile;}

        else if(tilesInt[x+y * width] == 0) {return Tile.spawn_rock_1;}

        else if(tilesInt[x+y * width] == 1) {return Tile.spawn_rock_2;}
        else if(tilesInt[x+y * width] == 2) {return Tile.spawn_grass_2;}
        else if(tilesInt[x+y * width] == 3) {return Tile.spawn_flowers;}
        else if(tilesInt[x+y * width] == 4) {return Tile.spawn_puddle;}

        else{return Tile.spawn_grass_1;}
        //return Tile.voidTile; // render tile void pozniej - trzeba zrobic udawanego nulla;

    }
    protected void prepare()
    {
        for(int y = 0 ; y < height; y++)
        {
            for(int x = 0 ; x < width; x++)
            {
                tilesInt[x + y*width]  = random.nextInt(500)+1; // 0-3
            }
        }
    }

}

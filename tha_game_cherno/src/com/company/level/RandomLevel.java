package com.company.level;

import com.company.level.tile.Tile;

import java.util.Random;


public class RandomLevel extends Level // nie ma default pustego constructora = blad
{

    private static final Random random = new Random();

    public RandomLevel(int width, int height)
    {
        super(width, height); // przewija do constructora w originalnej klasie

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

        tilesInt[width] = 4; // kałuża

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

package com.company.level;

import com.company.level.tile.Tile;

public class TileCoordinate
{
    private int x,y;
    final static int TILE_SIZE = 16;

    public TileCoordinate(int x, int y)
    {
        this.x = x * TILE_SIZE + TILE_SIZE/2;
        this.y = y * TILE_SIZE - TILE_SIZE/2;
    }

    public int X()
    {
        return x;
    }

    public int Y()
    {
        return y;
    }

    public int[] XY()
    {
        int[] r = new int[2];
        r[0] = x;
        r[1] = y;
        return r;
    }
}

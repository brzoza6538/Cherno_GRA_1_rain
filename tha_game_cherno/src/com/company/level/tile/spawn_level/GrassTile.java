package com.company.level.tile.spawn_level;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.level.tile.Tile;

public class GrassTile extends Tile
{

    public GrassTile(Sprite sprite)
    {
        super(sprite);
    }

    public boolean solid()
    {
        return false;
    }

    public boolean breakable()
    {
        return false;
    }

    public String Name()
    {
        return "GrassTile";
    }

    public void render(int  x, int y, Screen screen)
    {
        screen.renderTile(x << 4, y << 4, this);
    }

}

package com.company.level.tile;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite sprite)
    {
        super(sprite);
    }

    public String Name()
    {
        return "VoidTile";
    }

    public boolean solid()
    {
        return true;
    }

    public boolean shootable_through()
    {
        return true;
    }

    public boolean breakable()
    {
        return false;
    }

    public void render(int x, int y, Screen screen)
    {
        screen.renderTile(x << 4,y << 4,this);
    }
}

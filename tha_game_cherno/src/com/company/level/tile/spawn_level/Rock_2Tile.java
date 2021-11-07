package com.company.level.tile.spawn_level;

        import com.company.graphics.Screen;
        import com.company.graphics.Sprite;
        import com.company.level.tile.Tile;

public class Rock_2Tile extends Tile
{

    public Rock_2Tile(Sprite sprite)
    {
        super(sprite);
    }

    public boolean solid()
    {
        return false;
    }

    public boolean shootable_through()
    {
        return true;
    }

    public boolean breakable()
    {
        return false;
    }

    public String Name()
    {
        return "Rock_2Tile";
    }

    public void render(int  x, int y, Screen screen)
    {
        screen.renderTile(x << 4, y << 4, this);
    }

}


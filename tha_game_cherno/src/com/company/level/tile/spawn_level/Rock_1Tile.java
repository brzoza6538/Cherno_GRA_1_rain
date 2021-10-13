package com.company.level.tile.spawn_level;

        import com.company.graphics.Screen;
        import com.company.graphics.Sprite;

        import com.company.graphics.Screen;
        import com.company.graphics.Sprite;
        import com.company.level.tile.Tile;

public class Rock_1Tile extends Tile
{

    public Rock_1Tile(Sprite sprite)
    {
        super(sprite);
    }

    public boolean solid()
    {
        return true;
    }

    public boolean breakable()
    {
        return true;
    }

    public String Name()
    {
        return "Rock_1Tile";
    }

    public void render(int  x, int y, Screen screen)
    {
        screen.renderTile(x << 4, y << 4, this);
    }

}

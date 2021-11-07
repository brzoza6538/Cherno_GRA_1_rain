package com.company.level.tile.spawn_level;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;

import com.company.graphics.Screen;
import com.company.graphics.Sprite;
import com.company.level.tile.Tile;

public class FlowerTile extends Tile
    {

        public FlowerTile(Sprite sprite)
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
            return true;
        }

        public String Name()
        {
            return "FlowerTile";
        }

        public void render(int  x, int y, Screen screen)
        {
            screen.renderTile(x << 4, y << 4, this);
        }

    }
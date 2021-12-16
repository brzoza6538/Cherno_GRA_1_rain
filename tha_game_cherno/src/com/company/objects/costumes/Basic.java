package com.company.objects.costumes;

import com.company.graphics.Sprite;
import com.company.graphics.SpriteSheet;

public class Basic extends Costume
{


    public Basic()
    {
        costumeSprite = new SpriteSheet("resources/textures/sheets/player_sheet.png", 128);

        offX1 = 12;
        offX2 = -6;
        offY1 = 15;
        offY2 = 0;

        OOBX=21;
        OOBY=14;

        player_N_0 = new Sprite(32,2,0, costumeSprite);
        player_W_0 = new Sprite(32,1,0, costumeSprite);
        player_S_0 = new Sprite(32,0,0, costumeSprite);
        player_E_0 = new Sprite(32,3,0, costumeSprite);

        player_N_1 = new Sprite(32,2,1, costumeSprite);
        player_W_1 = new Sprite(32,1,1, costumeSprite);
        player_S_1 = new Sprite(32,0,1, costumeSprite);
        player_E_1 = new Sprite(32,3,1, costumeSprite);

        player_N_2 = new Sprite(32,2,2, costumeSprite);
        player_W_2 = new Sprite(32,1,2, costumeSprite);
        player_S_2 = new Sprite(32,0,2, costumeSprite);
        player_E_2 = new Sprite(32,3,2, costumeSprite);

        player_N_3 = new Sprite(32,2,3, costumeSprite);
        player_W_3 = new Sprite(32,1,3, costumeSprite);
        player_S_3 = new Sprite(32,0,3, costumeSprite);
        player_E_3 = new Sprite(32,3,3, costumeSprite);


    }
}

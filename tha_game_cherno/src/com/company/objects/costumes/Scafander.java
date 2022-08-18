package com.company.objects.costumes;

import com.company.graphics.AnimatedSprite;
import com.company.graphics.Sprite;
import com.company.graphics.SpriteSheet;

public class Scafander extends Costume
{


    public Scafander()
    {
        costumeSprite = new SpriteSheet("tha_game_cherno/resources/textures/sheets/scafander_sprite_waist.png", 128);

        offX1 = 14;
        offX2 = -7;
        offY1 = 15;
        offY2 = 0;

        OOBX=23;
        OOBY=14;

        sprite_down =  new SpriteSheet(costumeSprite,0,0,1,4,32 );
        sprite_right = new SpriteSheet(costumeSprite,1,0,1,4,32 );
        sprite_up = new SpriteSheet(costumeSprite,2,0,1,4,32 );
        sprite_left = new SpriteSheet(costumeSprite,3,0,1,4,32 );

        down = new AnimatedSprite(sprite_down, 32, 32, 4);
        right = new AnimatedSprite(sprite_right, 32, 32, 4);
        up = new AnimatedSprite(sprite_up, 32, 32, 4);
        left = new AnimatedSprite(sprite_left, 32, 32, 4);

    }

    public Scafander(String version)
    {
        if(version == "waist")
        {
            costumeSprite = new SpriteSheet("tha_game_cherno/resources/textures/sheets/scafander_sprite_waist.png", 128);
        }
        else if (version == "clean")
        {
            costumeSprite = new SpriteSheet("tha_game_cherno/resources/textures/sheets/scafander_sprite_clean.png", 128);
        }
        else if (version == "chest")
        {
            costumeSprite = new SpriteSheet("tha_game_cherno/resources/textures/sheets/scafander_sprite_waist.png", 128);
        }

        offX1 = 14;
        offX2 = -7;
        offY1 = 15;
        offY2 = 0;

        OOBX=23;
        OOBY=14;

        sprite_down =  new SpriteSheet(costumeSprite,0,0,1,4,32 );
        sprite_right = new SpriteSheet(costumeSprite,1,0,1,4,32 );
        sprite_up = new SpriteSheet(costumeSprite,2,0,1,4,32 );
        sprite_left = new SpriteSheet(costumeSprite,3,0,1,4,32 );

        down = new AnimatedSprite(sprite_down, 32, 32, 4);
        right = new AnimatedSprite(sprite_right, 32, 32, 4);
        up = new AnimatedSprite(sprite_up, 32, 32, 4);
        left = new AnimatedSprite(sprite_left, 32, 32, 4);

    }
}
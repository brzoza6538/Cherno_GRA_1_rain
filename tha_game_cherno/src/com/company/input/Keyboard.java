package com.company.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {


    private boolean[] keys = new boolean[120]; // 655 = max

    public boolean up,down,left,right;

    public void update()
    {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
/*
        for(int i  = 0 ; i < keys.length; i ++)
        {

            if(keys[i])
            {
                System.out.println("KEY : " + (char)i + " - " + i);
            }

        }
*/

    }

    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true; // get key_id

    }

    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e)
    {

    }


}
//overide nic nie robi to tylko przypominajka
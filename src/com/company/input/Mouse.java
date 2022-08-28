package com.company.input;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener
{
    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;
    private static int rotation = 0;
    private static boolean alreadyUsed = false;

    public static int getX()
    {
        return mouseX;
    }

    public static int getY()
    {
        return mouseY;
    }

    public static int getRotation()
    {
        return rotation;
    }

    public static boolean rotationAlreadyUsed()
    {
        return alreadyUsed;
    }

    public static void rotationUsed()
    {
        alreadyUsed = true;
        rotation = 0;
    }

    public static int getButton()
    {
        return mouseB;
    }

    public void mouseClicked(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        mouseB = e.getButton();
        //System.out.println(mouseB);
    }

    public void mouseReleased(MouseEvent e)
    {
        mouseB = - 1;
    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        //System.out.println(mouseX + " - " + mouseY);
    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        alreadyUsed = false;
        rotation = e.getWheelRotation();
    }
}

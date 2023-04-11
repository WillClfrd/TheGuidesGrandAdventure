package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.graphics.Bitmap;

public class GameObject {
    private char orientation;
    private Bitmap charImage;
    private int x;
    private int y;
    private int prevX;
    private int prevY;

    public GameObject(char orientation, Bitmap charImage, int x, int y){
        this.orientation = orientation;
        this.charImage = charImage;
        this.x = x;
        this.y = y;
    }

    public char getOrientation(){
        return orientation;
    }

    public Bitmap getCharImage(){
        return charImage;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getPrevX(){
        return prevX;
    }

    public int getPrevY(){
        return prevY;
    }

    public void setOrientation(char newOrientation){
        this.orientation = newOrientation;
    }

    public void setCharImage(Bitmap newCharImage){
        this.charImage = newCharImage;
    }

    public void setX(int newX){
        this.x = newX;
    }

    public void setY(int newY){
        this.y = newY;
    }

    public void setPrevX(int newPrevX){
        this.prevX = newPrevX;
    }

    public void setPrevY(int newPrevY){
        this.prevY = newPrevY;
    }
}

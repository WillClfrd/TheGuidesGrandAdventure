package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.graphics.Bitmap;

public class GameObject {
    private char orientation;
    private Bitmap charImage;
    private Bitmap charImageRight;
    private Bitmap charImageLeft;
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    private int objectOffset;
    private char objectType;
    private char currentSprite;

    public GameObject(Bitmap charImageRight, Bitmap charImageLeft){
        this.orientation = 'r';
        this.charImage = charImageRight;
        this.currentSprite ='r';
        this.charImageRight = charImageRight;
        this.charImageLeft = charImageLeft;
    }

    public char getOrientation(){
        return orientation;
    }

    public Bitmap getCharImage(){
        return charImage;
    }
    public Bitmap getCharImageRight(){ return this.charImageRight; }
    public Bitmap getCharImageLeft(){ return this.charImageLeft; }

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

    public int getLeft(){ return x; }

    public int getRight(){ return x + objectOffset; }

    public int getTop(){ return y; }

    public int getBottom(){ return y + objectOffset; }

    public int getObjectOffset(){ return objectOffset; }

    public char getObjectType(){ return this.objectType; }

    public char getCurrentSprite(){ return this.currentSprite; }

    public void setOrientation(char newOrientation){
        this.orientation = newOrientation;
    }

    public void setCharImage(Bitmap newCharImage){
        this.charImage = newCharImage;
    }

    public void setCharImageRight(Bitmap newCharImageRight){
        this.charImageRight = newCharImageRight;
    }

    public void setCharImageLeft(Bitmap newCharImageLeft){
        this.charImageLeft = newCharImageLeft;
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

    public void setObjectOffset(int objectOffset){ this.objectOffset = objectOffset; }

    public void setObjectType(char objectType){ this.objectType = objectType; }

    public void setCurrentSprite(char currentSprite){ this.currentSprite = currentSprite; }
}

package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.graphics.Bitmap;

/**
 * This class represents GameObject objects.
 * The GameObject class maintains the orientation, charImage, charImageRight, charImageLeft, x, y, prevX, prevY, objectOffset, objectType, and currentSprite GameObject attributes.
 *
 * @author William Clifford (rye747), Meagan Baty (), Jose Gracia ()
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
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

    /**
     * Creates GameObject objects.
     * Initializes the orientation, charImage, and currentSprite attributes to default values.
     *
     * @param charImageRight the value to be assigned to the charImageRight attribute.
     * @param charImageLeft the value to be assigned to the charImageLeft attribute.
     */
    public GameObject(Bitmap charImageRight, Bitmap charImageLeft){
        this.orientation = 'r';
        this.charImage = charImageRight;
        this.currentSprite ='r';
        this.charImageRight = charImageRight;
        this.charImageLeft = charImageLeft;
    }

    /**
     * Returns the GameObject orientation attribute.
     *
     * @return the value of the GameObject orientation attribute.
     */
    public char getOrientation(){
        return orientation;
    }

    /**
     * Returns the GameObject charImage attribute.
     *
     * @return the value of the GameObject charImage attribute.
     */
    public Bitmap getCharImage(){
        return charImage;
    }

    /**
     * Returns the GameObject charImageRight attribute.
     *
     * @return the value of the GameObject charImageRight attribute.
     */
    public Bitmap getCharImageRight(){ return this.charImageRight; }

    /**
     * Returns the GameObject charImageLeft attribute.
     *
     * @return the value of the GameObject charImageLeft attribute.
     */
    public Bitmap getCharImageLeft(){ return this.charImageLeft; }

    /**
     * Returns the GameObject x attribute.
     *
     * @return the value of the GameObject x attribute.
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the GameObject y attribute.
     *
     * @return the value of the GameObject y attribute.
     */
    public int getY(){
        return y;
    }

    /**
     * Returns the GameObject prevX attribute.
     *
     * @return the value of the GameObject prevX attribute.
     */
    public int getPrevX(){
        return prevX;
    }

    /**
     * Returns the GameObject prevY attribute.
     *
     * @return the value of the GameObject prevY attribute.
     */
    public int getPrevY(){
        return prevY;
    }

    /**
     * Returns the x coordinate of the left side of the GameObject boundaries.
     *
     * @return the value of the x coordinate of the left side of the GameObject boundaries.
     */
    public int getLeft(){ return x; }

    /**
     * Returns the x coordinate of the right side of the GameObject boundaries.
     *
     * @return the value of the x coordinate of the right side of the GameObject boundaries.
     */
    public int getRight(){ return x + objectOffset; }

    /**
     * Returns the y coordinate of the top side of the GameObject boundaries.
     *
     * @return the value of the y coordinate of the top side of the GameObject boundaries.
     */
    public int getTop(){ return y; }

    /**
     * Returns the y coordinate of the bottom side of the GameObject boundaries.
     *
     * @return the value of the y coordinate of the bottom side of the GameObject boundaries.
     */
    public int getBottom(){ return y + objectOffset; }

    /**
     * Returns the GameObject objectOffset attribute.
     *
     * @return the value of the GameObject objectOffset attribute.
     */
    public int getObjectOffset(){ return objectOffset; }

    /**
     * Returns the GameObject objectType attribute.
     *
     * @return the value of the GameObject objectType attribute.
     */
    public char getObjectType(){ return this.objectType; }

    /**
     * Returns the GameObject currentSprite attribute.
     *
     * @return the value of the GameObject currentSprite attribute.
     */
    public char getCurrentSprite(){ return this.currentSprite; }

    /**
     * Sets the value of the GameObject orientation attribute to the specified value.
     *
     * @param newOrientation the value to be assigned to the GameObject orientation attribute.
     */
    public void setOrientation(char newOrientation){
        this.orientation = newOrientation;
    }

    /**
     * Sets the value of the GameObject charImage attribute to the specified object.
     *
     * @param newCharImage the object to be assigned to the GameObject charImage attribute.
     */
    public void setCharImage(Bitmap newCharImage){
        this.charImage = newCharImage;
    }

    /**
     * Sets the value of the GameObject charImageRight attribute to the specified object.
     *
     * @param newCharImageRight the object to be assigned to the GameObject charImageRight attribute.
     */
    public void setCharImageRight(Bitmap newCharImageRight){
        this.charImageRight = newCharImageRight;
    }

    /**
     * Sets the value of the GameObject charImageLeft attribute to the specified object.
     *
     * @param newCharImageLeft the object to be assigned to the GameObject charImageLeft attribute.
     */
    public void setCharImageLeft(Bitmap newCharImageLeft){
        this.charImageLeft = newCharImageLeft;
    }

    /**
     * Sets the value of the GameObject x attribute to the specified value.
     *
     * @param newX the value to be assigned to the GameObject x attribute.
     */
    public void setX(int newX){
        this.x = newX;
    }

    /**
     * Sets the value of the GameObject y attribute to the specified value.
     *
     * @param newY the value to be assigned to the GameObject y attribute.
     */
    public void setY(int newY){
        this.y = newY;
    }

    /**
     * Sets the value of the GameObject prevX attribute to the specified value.
     *
     * @param newPrevX the value to be assigned to the GameObject prevX attribute.
     */
    public void setPrevX(int newPrevX){
        this.prevX = newPrevX;
    }

    /**
     * Sets the value of the GameObject prevY attribute to the specified value.
     *
     * @param newPrevY the value to be assigned to the GameObject prevY attribute.
     */
    public void setPrevY(int newPrevY){
        this.prevY = newPrevY;
    }

    /**
     * Sets the value of the GameObject objectOffset attribute to the specified value.
     *
     * @param objectOffset the value to be assigned to the GameObject objectOffset attribute.
     */
    public void setObjectOffset(int objectOffset){ this.objectOffset = objectOffset; }

    /**
     * Sets the value of the GameObject objectType attribute to the specified value.
     *
     * @param objectType the value to be assigned to the GameObject objectType attribute.
     */
    public void setObjectType(char objectType){ this.objectType = objectType; }

    /**
     * Sets the value of the GameObject currentSprite attribute to the specified value.
     *
     * @param currentSprite the value to be assigned to the GameObject currentSprite attribute.
     */
    public void setCurrentSprite(char currentSprite){ this.currentSprite = currentSprite; }
}

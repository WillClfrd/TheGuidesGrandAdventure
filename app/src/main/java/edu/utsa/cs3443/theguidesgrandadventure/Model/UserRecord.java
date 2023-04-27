package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.graphics.Bitmap;

/**
 * This class represents UserRecord objects.
 * The UserRecord class maintains the userName, score, charName, and charImage attribute for UserRecord objects.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class UserRecord {
    private String userName;
    private int score;
    private String charName;
    private Bitmap charImage;

    /**
     * Creates a UserRecord object.
     *
     * @param userName the value specified to be assign to the UserObject userName attribute
     * @param score the value specified to be assign to the UserObject score attribute
     * @param charName the value specified to be assign to the UserObject charName attribute
     * @param charImage the value specified to be assign to the UserObject charImage attribute
     */
    public UserRecord(String userName, int score, String charName, Bitmap charImage){
        this.userName = userName;
        this.score = score;
        this.charName = charName;
        this.charImage = charImage;
    }

    /**
     * Returns the UserRecord object userName attribute.
     *
     * @return the value of the userObject userName attribute.
     */
    public String getUserName(){
        return this.userName;
    }

    /**
     * Returns the UserRecord object score attribute.
     *
     * @return the value of the userObject score attribute.
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Returns the UserRecord object charName attribute.
     *
     * @return the value of the userObject charName attribute.
     */
    public String getCharName(){
        return this.charName;
    }

    /**
     * Returns the UserRecord object charImage attribute.
     *
     * @return the value of the userObject charImage attribute.
     */
    public Bitmap getCharImage(){
        return charImage;
    }

    /**
     * Sets the value of the UserRecord userName attribute to the specified value.
     *
     * @param userName the value to be assigned to the UserRecord userName attribute.
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * Sets the value of the UserRecord score attribute to the specified value.
     *
     * @param score the value to be assigned to the UserRecord score attribute.
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Sets the value of the UserRecord charName attribute to the specified value.
     *
     * @param charName the value to be assigned to the UserRecord charName attribute.
     */
    public void setCharName(String charName){
        this.charName = charName;
    }

    /**
     * Sets the value of the UserRecord charImage attribute to the specified object.
     *
     * @param charImage the object to be assigned to the UserRecord charImage attribute.
     */
    public void setCharImage(Bitmap charImage){
        this.charImage = charImage;
    }
}

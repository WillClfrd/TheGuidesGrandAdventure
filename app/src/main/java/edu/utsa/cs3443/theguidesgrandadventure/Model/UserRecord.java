package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.graphics.Bitmap;

public class UserRecord {
    private String userName;
    private int score;
    private String charName;
    private Bitmap charImage;

    public UserRecord(String userName, int score, String charName, Bitmap charImage){
        this.userName = userName;
        this.score = score;
        this.charName = charName;
        this.charImage = charImage;
    }

    public String getUserName(){
        return this.userName;
    }

    public int getScore(){
        return this.score;
    }

    public String getCharName(){
        return this.charName;
    }

    public Bitmap getCharImage(){
        return charImage;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setCharName(String charName){
        this.charName = charName;
    }

    public void setCharImage(Bitmap charImage){
        this.charImage = charImage;
    }
}

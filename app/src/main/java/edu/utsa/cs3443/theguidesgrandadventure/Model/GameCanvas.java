package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import edu.utsa.cs3443.theguidesgrandadventure.R;

public class GameCanvas extends View {
    private Paint paint;
    private GameObject character;
    private GameObject collectible;
    private Bitmap background;
    private ArrayList<GameObject> followers;

    private int scoreCount;
    private boolean hasCollectible;
    private boolean isInitialDraw;
    private Random rand;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        rand = new Random();

        character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.character));
        collectible = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.collectible_item));
        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_background);
        followers = new ArrayList<GameObject>();
        character.setX(getWidth() / 2);
        character.setY(getHeight() / 2);
        character.setObjectOffset(100);
        collectible.setObjectOffset(100);
        isInitialDraw = true;
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(isInitialDraw) {
            background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), true);
            character.setCharImage(Bitmap.createScaledBitmap(character.getCharImage(), character.getObjectOffset(),character.getObjectOffset(),true));
            collectible.setCharImage(Bitmap.createScaledBitmap(collectible.getCharImage(), collectible.getObjectOffset(), collectible.getObjectOffset(), true));
            character.setX(getWidth() / 2);
            character.setY(getHeight() / 2);
            character.setObjectOffset(100);
            collectible.setObjectOffset(100);
            collectible.setX(rand.nextInt(getWidth() - collectible.getObjectOffset()));
            collectible.setY(rand.nextInt(getHeight() - collectible.getObjectOffset()));
            isInitialDraw = false;
            hasCollectible = true;
        }

        paint.setColor(Color.WHITE);
        canvas.drawBitmap(background, 0,0,paint);
        canvas.drawBitmap(character.getCharImage(), character.getX(), character.getY(), paint);
        canvas.drawBitmap(collectible.getCharImage(), collectible.getX(), collectible.getY(), paint);
    }

    public void updateCharacters(){
        switch(this.character.getOrientation()){
            case 'u':
                this.character.setPrevY(character.getY());
                this.character.setY(character.getY() - 100);
                break;
            case 'd':
                this.character.setPrevY(character.getY());
                this.character.setY(character.getY() + 100);
                break;
            case 'l':
                this.character.setPrevX(character.getX());
                this.character.setX(character.getX() - 100);
                break;
            case 'r':
                this.character.setPrevX(character.getX());
                this.character.setX(character.getX() + 100);
                break;
        }
    }

    public void updateCollectibles(){
        if(!(this.hasCollectible)){
            this.collectible.setX(rand.nextInt(getWidth() - collectible.getObjectOffset()));
            this.collectible.setY(rand.nextInt(getHeight() - collectible.getObjectOffset()));
        }
    }

    public boolean boundaryCollisionCheck(GameObject gameOb){
        if((gameOb.getLeft() < 0) || (gameOb.getRight() > getWidth()) || (gameOb.getTop() < 0) || (gameOb.getBottom() >getHeight())){
            return true;
        }
        return false;
    }

    public boolean objectCollisionCheck(GameObject gameOb1, GameObject gameOb2){
        if((gameOb1.getLeft() >= gameOb2.getLeft() && gameOb1.getLeft() <= gameOb2.getRight()) && ((gameOb1.getTop() >= gameOb2.getTop() && gameOb1.getTop() <= gameOb2.getBottom()) || (gameOb1.getBottom() >= gameOb2.getTop() && gameOb1.getBottom() <= gameOb2.getBottom()))){
            return true;
        }
        else if(((gameOb1.getRight() >= gameOb2.getLeft()) && (gameOb1.getRight() <= gameOb2.getRight())) && (((gameOb1.getTop() >= gameOb2.getTop()) && (gameOb1.getTop() <= gameOb2.getBottom())) || ((gameOb1.getBottom() >= gameOb2.getTop()) && (gameOb1.getBottom() <= gameOb2.getBottom())))){
            return true;
        }

        return false;
    }

    public GameObject getCharacter(){
        return this.character;
    }

    public GameObject getCollectible(){
        return this.collectible;
    }

    public int getScoreCount(){
        return this.scoreCount;
    }
    public boolean getHasCollectible(){return this.hasCollectible;}

    public void setScoreCount(int newScoreCount){
        this.scoreCount = newScoreCount;
    }

    public void setHasCollectible(boolean hasCollectible){
        this.hasCollectible = hasCollectible;
    }
}

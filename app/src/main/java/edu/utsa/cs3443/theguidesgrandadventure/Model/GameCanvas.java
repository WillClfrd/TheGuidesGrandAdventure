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

import edu.utsa.cs3443.theguidesgrandadventure.GameActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class GameCanvas extends View {
    private Paint paint;
    private GameObject character;
    private GameObject collectible;
    private Bitmap background;
    private ArrayList<GameObject> followers;
    private Bitmap[] followerImages;

    private int scoreCount;
    private boolean hasCollectible;
    private boolean isInitialDraw;
    private Random rand;
    private int numberOfFollowers;
    private int defaultObjectOffset;

    public GameCanvas(Context context) {
        super(context);

        this.paint = new Paint();

        this.rand = new Random();

        this.numberOfFollowers = 3;

        this.defaultObjectOffset = 100;

        this.followerImages = new Bitmap[this.numberOfFollowers];

        this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.character));

        this.collectible = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.collectible_item));

        this.background = BitmapFactory.decodeResource(getResources(), R.drawable.game_background);

        this.followerImages[0] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_1);
        this.followerImages[1] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_2);
        this.followerImages[2] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_3);

        this.followers = new ArrayList<GameObject>();

        this.character.setX(getWidth() / 2);
        this.character.setY(getHeight() / 2);
        this.character.setObjectOffset(this.defaultObjectOffset);
        this.character.setObjectType('p');

        this.collectible.setObjectOffset(this.defaultObjectOffset);

        this.isInitialDraw = true;
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int i;

        if(this.isInitialDraw) {
            this.background = Bitmap.createScaledBitmap(this.background, getWidth(), getHeight(), true);

            this.character.setCharImage(Bitmap.createScaledBitmap(this.character.getCharImage(), this.character.getObjectOffset(),this.character.getObjectOffset(),true));

            this.collectible.setCharImage(Bitmap.createScaledBitmap(this.collectible.getCharImage(), this.collectible.getObjectOffset(), this.collectible.getObjectOffset(), true));

            this.character.setX((int)((int)(getWidth() / 2) / this.character.getObjectOffset()) * this.character.getObjectOffset());
            this.character.setY((int)((int)(getHeight() / 2) / this.character.getObjectOffset()) * this.character.getObjectOffset());

            do {
                this.collectible.setX(generateCollectibleCoordinate(getWidth()));
                this.collectible.setY(generateCollectibleCoordinate(getHeight()));
            }while(!(isValidCollectibleLocation()));

            this.hasCollectible = true;

            this.scoreCount = 0;

            this.isInitialDraw = false;
        }

        this.paint.setColor(Color.WHITE);

        canvas.drawBitmap(this.background, 0,0,this.paint);
        canvas.drawBitmap(this.character.getCharImage(), this.character.getX(), this.character.getY(), this.paint);
        canvas.drawBitmap(this.collectible.getCharImage(), this.collectible.getX(), this.collectible.getY(), this.paint);
        for(i = 0; i < this.followers.size(); ++i){
            canvas.drawBitmap(this.followers.get(i).getCharImage(), this.followers.get(i).getX(), this.followers.get(i).getY(), this.paint);
        }

        this.paint.setTextSize(75);
        canvas.drawText("Score: " + this.scoreCount, 50, 100, this.paint);
    }

    public boolean updateCharacters(){
        int i;
        this.character.setPrevY(this.character.getY());
        this.character.setPrevX(this.character.getX());
        switch(this.character.getOrientation()){
            case 'u':
                this.character.setY(this.character.getY() - this.character.getObjectOffset());
                break;
            case 'd':
                this.character.setY(this.character.getY() + this.character.getObjectOffset());
                break;
            case 'l':
                this.character.setX(this.character.getX() - this.character.getObjectOffset());
                break;
            case 'r':
                this.character.setX(this.character.getX() + this.character.getObjectOffset());
                break;
        }

        for(i = 0; i < this.followers.size(); ++i){
            if(objectCollisionCheck(this.character, this.followers.get(i))){
                return false;
            }
        }

        if((this.scoreCount / 5) > this.followers.size()){
            GameObject temp = new GameObject(this.followerImages[this.rand.nextInt(this.numberOfFollowers)]);
            temp.setObjectOffset(this.defaultObjectOffset);
            temp.setCharImage(Bitmap.createScaledBitmap(temp.getCharImage(), temp.getObjectOffset(), temp.getObjectOffset(), true));
            temp.setObjectType('p');

            if(this.followers.size() == 0){
                temp.setX(this.character.getPrevX());
                temp.setY(this.character.getPrevY());
            }
            else{
                temp.setX(this.followers.get(this.followers.size() - 1).getPrevX());
                temp.setY(this.followers.get(this.followers.size() - 1).getPrevY());
            }

            this.followers.add(temp);
        }

        for(i = 0; i < this.followers.size(); ++i){
            this.followers.get(i).setPrevX(this.followers.get(i).getX());
            this.followers.get(i).setPrevY(this.followers.get(i).getY());
            if(i == 0){
                this.followers.get(i).setX(this.character.getPrevX());
                this.followers.get(i).setY(this.character.getPrevY());
            }
            else {
                this.followers.get(i).setX(this.followers.get(i - 1).getPrevX());
                this.followers.get(i).setY(this.followers.get(i - 1).getPrevY());
            }
        }
        return true;
    }

    public void updateCollectibles(){
        if(!(this.hasCollectible)){
            do {
                this.collectible.setX(generateCollectibleCoordinate(getWidth()));
                this.collectible.setY(generateCollectibleCoordinate(getHeight()));
            }while(!(isValidCollectibleLocation()));
            ++this.scoreCount;
        }
    }

    private int generateCollectibleCoordinate(int upperBound){
        int temp = this.collectible.getObjectOffset() * (this.rand.nextInt(upperBound - this.collectible.getObjectOffset()) / this.collectible.getObjectOffset());
        return temp;
    }

    private boolean isValidCollectibleLocation(){
        int i;

        if(objectCollisionCheck(this.character, this.collectible)){
            return false;
        }

        for(i = 0; i < followers.size(); ++i){
            if(objectCollisionCheck(followers.get(i), this.collectible)){
                return false;
            }
        }
        return true;
    }

    public boolean boundaryCollisionCheck(GameObject gameOb){
        if((gameOb.getLeft() < 0) || (gameOb.getRight() > getWidth()) || (gameOb.getTop() < 0) || (gameOb.getBottom() >getHeight())){
            return true;
        }
        return false;
    }

    public boolean objectCollisionCheck(GameObject gameOb1, GameObject gameOb2){
        int sideCollCount = 0;

        if(Math.abs(gameOb1.getLeft() - gameOb2.getLeft()) < 10){ ++sideCollCount; }
        else if(Math.abs(gameOb1.getLeft() - gameOb2.getRight()) < 10){ ++sideCollCount; }
        if(Math.abs(gameOb1.getRight() - gameOb2.getRight()) < 10){ ++sideCollCount; }
        else if(Math.abs(gameOb1.getRight() - gameOb2.getLeft()) < 10){ ++ sideCollCount; }
        if(Math.abs(gameOb1.getTop() - gameOb2.getTop()) < 10){ ++sideCollCount; }
        else if(Math.abs(gameOb1.getTop() - gameOb2.getBottom()) < 10){ ++sideCollCount; }
        if(Math.abs(gameOb1.getBottom() - gameOb2.getBottom()) < 10){ ++sideCollCount; }
        else if(Math.abs(gameOb1.getBottom() - gameOb2.getTop()) < 10){ ++sideCollCount; }

        if(sideCollCount < 4 && sideCollCount > 0) {
            return false;
        }

        if((gameOb1.getObjectType() == 'p') && (gameOb2.getObjectType() == 'p')){
            if((Math.abs(gameOb1.getLeft() - gameOb2.getLeft()) <= 10) && (Math.abs(gameOb1.getRight() - gameOb2.getRight()) <= 10) && (Math.abs(gameOb1.getTop() - gameOb2.getTop()) <= 10) && (Math.abs(gameOb1.getBottom() - gameOb2.getBottom()) <= 10)){
                return true;
            }
            else{
                return false;
            }
        }

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
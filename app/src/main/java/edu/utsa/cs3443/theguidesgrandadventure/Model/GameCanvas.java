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

        paint = new Paint();

        rand = new Random();

        numberOfFollowers = 3;

        defaultObjectOffset = 100;

        followerImages = new Bitmap[numberOfFollowers];

        character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.character));

        collectible = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.collectible_item));

        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_background);

        followerImages[0] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_1);
        followerImages[1] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_2);
        followerImages[2] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_3);

        followers = new ArrayList<GameObject>();

        character.setX(getWidth() / 2);
        character.setY(getHeight() / 2);
        character.setObjectOffset(defaultObjectOffset);
        character.setObjectType('p');

        collectible.setObjectOffset(defaultObjectOffset);

        isInitialDraw = true;
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int i;

        if(isInitialDraw) {
            background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), true);

            character.setCharImage(Bitmap.createScaledBitmap(character.getCharImage(), character.getObjectOffset(),character.getObjectOffset(),true));

            collectible.setCharImage(Bitmap.createScaledBitmap(collectible.getCharImage(), collectible.getObjectOffset(), collectible.getObjectOffset(), true));

            character.setX(getWidth() / 2);
            character.setY(getHeight() / 2);

            collectible.setX(rand.nextInt(getWidth() - collectible.getObjectOffset()));
            collectible.setY(rand.nextInt(getHeight() - collectible.getObjectOffset()));

            isInitialDraw = false;

            hasCollectible = true;
        }

        paint.setColor(Color.WHITE);
        canvas.drawBitmap(background, 0,0,paint);
        canvas.drawBitmap(character.getCharImage(), character.getX(), character.getY(), paint);
        canvas.drawBitmap(collectible.getCharImage(), collectible.getX(), collectible.getY(), paint);
        for(i = 0; i < followers.size(); ++i){
            canvas.drawBitmap(followers.get(i).getCharImage(), followers.get(i).getX(), followers.get(i).getY(), paint);
        }
    }

    public boolean updateCharacters(){
        int i;
        this.character.setPrevY(character.getY());
        this.character.setPrevX(character.getX());
        switch(this.character.getOrientation()){
            case 'u':
                this.character.setY(character.getY() - character.getObjectOffset());
                break;
            case 'd':
                this.character.setY(character.getY() + character.getObjectOffset());
                break;
            case 'l':
                this.character.setX(character.getX() - character.getObjectOffset());
                break;
            case 'r':
                this.character.setX(character.getX() + character.getObjectOffset());
                break;
        }

        for(i = 0; i < followers.size(); ++i){
            if(objectCollisionCheck(character, followers.get(i))){
                return false;
            }
        }

        if((scoreCount / 5) > followers.size()){
            GameObject temp = new GameObject(followerImages[rand.nextInt(numberOfFollowers)]);
            temp.setObjectOffset(defaultObjectOffset);
            temp.setCharImage(Bitmap.createScaledBitmap(temp.getCharImage(), temp.getObjectOffset(), temp.getObjectOffset(), true));
            temp.setObjectType('p');

            if(followers.size() == 0){
                temp.setX(character.getPrevX());
                temp.setY(character.getPrevY());
            }
            else{
                temp.setX(followers.get(followers.size() - 1).getPrevX());
                temp.setY(followers.get(followers.size() - 1).getPrevY());
            }

            followers.add(temp);
        }

        for(i = 0; i < followers.size(); ++i){
            followers.get(i).setPrevX(followers.get(i).getX());
            followers.get(i).setPrevY(followers.get(i).getY());
            if(i == 0){
                followers.get(i).setX(character.getPrevX());
                followers.get(i).setY(character.getPrevY());
            }
            else {
                followers.get(i).setX(followers.get(i - 1).getPrevX());
                followers.get(i).setY(followers.get(i - 1).getPrevY());
            }
        }
        return true;
    }

    public void updateCollectibles(){
        if(!(this.hasCollectible)){
            this.collectible.setX(rand.nextInt(getWidth() - collectible.getObjectOffset()));
            this.collectible.setY(rand.nextInt(getHeight() - collectible.getObjectOffset()));
            ++this.scoreCount;
        }
    }

    public boolean boundaryCollisionCheck(GameObject gameOb){
        if((gameOb.getLeft() < 0) || (gameOb.getRight() > getWidth()) || (gameOb.getTop() < 0) || (gameOb.getBottom() >getHeight())){
            return true;
        }
        return false;
    }

    public boolean objectCollisionCheck(GameObject gameOb1, GameObject gameOb2){
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
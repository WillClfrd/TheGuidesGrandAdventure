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

import edu.utsa.cs3443.theguidesgrandadventure.R;

public class GameCanvas extends View {
    private Paint paint;
    private GameObject character;
    private Bitmap background;
    private ArrayList<GameObject> followers;

    private int scoreCount;
    private boolean hasCollectable;
    private boolean isInitialDraw;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.character));
        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_background);
        followers = new ArrayList<GameObject>();
        character.setX(getWidth() / 2);
        character.setY(getHeight() / 2);
        isInitialDraw = true;
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), true);
        character.setCharImage(Bitmap.createScaledBitmap(character.getCharImage(), 100,100,true));

        if(isInitialDraw) {
            character.setX(getWidth() / 2);
            character.setY(getHeight() / 2);
            isInitialDraw = false;
        }

        paint.setColor(Color.WHITE);
        canvas.drawBitmap(background, 0,0,paint);
        canvas.drawBitmap(character.getCharImage(), character.getX(), character.getY(), paint);
    }

    public void update(){
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

    public GameObject getCharacter(){
        return this.character;
    }

    public int getScoreCount(){
        return this.scoreCount;
    }
    public boolean getHasCollectable(){return this.hasCollectable;}

    public void setScoreCount(int newScoreCount){
        this.scoreCount = newScoreCount;
    }
}

package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import edu.utsa.cs3443.theguidesgrandadventure.R;

public class GameCanvas extends View {
    private Paint paint;
    private GameObject character;
    private Bitmap background;

    private int scoreCount;
    private boolean needsCollectable;

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        character = new GameObject('r', BitmapFactory.decodeResource(getResources(), R.drawable.character), (getWidth() / 2), (getHeight() / 2));
        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_background);
        background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), true);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        paint.setColor(Color.WHITE);
        canvas.drawBitmap(background, 0,0,paint);
        canvas.drawBitmap(character.getCharImage(), character.getX(), character.getY(), paint);
    }

    public void update(){
        switch(this.character.getOrientation()){
            case 'u':
                this.character.setY(character.getY() - 10);
                break;
            case 'd':
                this.character.setY(character.getY() + 10);
                break;
            case 'l':
                this.character.setX(character.getX() - 10);
                break;
            case 'r':
                this.character.setX(character.getX() + 10);
                break;
        }
    }

    public GameObject getCharacter(){
        return this.character;
    }

    public int getScoreCount(){
        return this.scoreCount;
    }

    public void setScoreCount(int newScoreCount){
        this.scoreCount = newScoreCount;
    }
}

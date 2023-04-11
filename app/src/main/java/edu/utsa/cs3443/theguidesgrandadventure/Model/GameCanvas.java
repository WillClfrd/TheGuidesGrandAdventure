package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GameCanvas extends View {
    private Paint paint;
    private GameObject character;


    public GameCanvas(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){

    }
}

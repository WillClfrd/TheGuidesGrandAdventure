package edu.utsa.cs3443.theguidesgrandadventure.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Random;

import edu.utsa.cs3443.theguidesgrandadventure.Controller.SettingsController;
import edu.utsa.cs3443.theguidesgrandadventure.R;

/**
 * This class represents GameCanvas objects.
 * The GameObject class maintains the paint, character, collectible, background, scoreCount, hasCollectible, isInitialDraw, rand, numberOfFollowers, defaultObjectOffset, and soundManager properties.
 * The GameObject class also maintains the followers ArrayList, followerImagesLeft array, and the followerImagesRight array.
 * The GameObject class extends the View class.
 *
 * @author Will Clifford
 * @author Meagan Baty
 * @author Jose Gracia
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class GameCanvas extends View {
    private final Paint paint;
    private final GameObject character;
    private final GameObject collectible;
    private Bitmap background;
    private final ArrayList<GameObject> followers;
    private final Bitmap[] followerImagesRight;
    private final Bitmap[] followerImagesLeft;
    public static int scoreCount;
    private boolean hasCollectible;
    private boolean isInitialDraw;
    private final Random rand;
    private final int numberOfFollowers;
    private final int defaultObjectOffset;
    private final SoundManager soundManager;

    /**
     * Creates a GameCanvas object.
     * Initializes GameCanvas object paint, rand, soundManager, numberOfFollowers, defaultObjectOffset, character, background, collectible, and isInitialDraw properties.
     * Initializes the followerImagesLeft and followerImagesRight arrays, as well as the followers ArrayList.
     *
     * @param context
     */
    public GameCanvas(Context context) {
        super(context);

        this.paint = new Paint();

        this.rand = new Random();

        this.soundManager = new SoundManager(context);

        this.numberOfFollowers = 6;

        this.defaultObjectOffset = 100;

        this.followerImagesRight = new Bitmap[this.numberOfFollowers];
        this.followerImagesLeft = new Bitmap[this.numberOfFollowers];

        switch (SettingsController.chrId) {
            case(2):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.rob_right), BitmapFactory.decodeResource(getResources(), R.drawable.rob_left));
                break;
            case(3):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.will_right), BitmapFactory.decodeResource(getResources(), R.drawable.will_left));
                break;
            case(4):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.meagan_right), BitmapFactory.decodeResource(getResources(), R.drawable.meagan_left));
                break;
            case(5):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.gman_right), BitmapFactory.decodeResource(getResources(), R.drawable.gman_left));
                break;
            case(6):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.joao_right), BitmapFactory.decodeResource(getResources(), R.drawable.joao_left));
                break;
            case(7):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.addy_right), BitmapFactory.decodeResource(getResources(), R.drawable.addy_left));
                break;
            case(8):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.trent_right), BitmapFactory.decodeResource(getResources(), R.drawable.trent_left));
                break;
            case(9):
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.serena_right), BitmapFactory.decodeResource(getResources(), R.drawable.serena_left));
                break;
            default:
                this.character = new GameObject(BitmapFactory.decodeResource(getResources(), R.drawable.character_right), BitmapFactory.decodeResource(getResources(), R.drawable.character_left));
                break;
        }

        switch(SettingsController.bkgId) {
            case(2):
                this.background = BitmapFactory.decodeResource(getResources(), R.drawable.sandy_background);
                break;
            case(3):
                this.background = BitmapFactory.decodeResource(getResources(), R.drawable.snowy_background);
                break;
            case(4):
                this.background = BitmapFactory.decodeResource(getResources(), R.drawable.ocean_background);
                break;
            case(5):
                this.background = BitmapFactory.decodeResource(getResources(), R.drawable.cave_background);
                break;
            case(6):
                this.background = BitmapFactory.decodeResource(getResources(), R.drawable.brick_background);
                break;
            case(7):
                this.background = BitmapFactory.decodeResource(getResources(), R.drawable.volcano_background);
                break;
            default:
                this.background = BitmapFactory.decodeResource(getResources(), R.drawable.grassy_background);
                break;
        }

        this.followerImagesRight[0] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_1_right);
        this.followerImagesRight[0] = Bitmap.createScaledBitmap(this.followerImagesRight[0], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesRight[1] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_2_right);
        this.followerImagesRight[1] = Bitmap.createScaledBitmap(this.followerImagesRight[1], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesRight[2] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_3_right);
        this.followerImagesRight[2] = Bitmap.createScaledBitmap(this.followerImagesRight[2], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesRight[3] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_4_right);
        this.followerImagesRight[3] = Bitmap.createScaledBitmap(this.followerImagesRight[3], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesRight[4] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_5_right);
        this.followerImagesRight[4] = Bitmap.createScaledBitmap(this.followerImagesRight[4], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesRight[5] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_6_right);
        this.followerImagesRight[5] = Bitmap.createScaledBitmap(this.followerImagesRight[5], this.defaultObjectOffset, this.defaultObjectOffset,true);

        this.followerImagesLeft[0] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_1_left);
        this.followerImagesLeft[0] = Bitmap.createScaledBitmap(this.followerImagesLeft[0], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesLeft[1] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_2_left);
        this.followerImagesLeft[1] = Bitmap.createScaledBitmap(this.followerImagesLeft[1], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesLeft[2] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_3_left);
        this.followerImagesLeft[2] = Bitmap.createScaledBitmap(this.followerImagesLeft[2], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesLeft[3] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_4_left);
        this.followerImagesLeft[3] = Bitmap.createScaledBitmap(this.followerImagesLeft[3], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesLeft[4] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_5_left);
        this.followerImagesLeft[4] = Bitmap.createScaledBitmap(this.followerImagesLeft[4], this.defaultObjectOffset, this.defaultObjectOffset,true);
        this.followerImagesLeft[5] = BitmapFactory.decodeResource(getResources(), R.drawable.follower_6_left);
        this.followerImagesLeft[5] = Bitmap.createScaledBitmap(this.followerImagesLeft[5], this.defaultObjectOffset, this.defaultObjectOffset,true);

        this.collectible = new GameObject(followerImagesRight[rand.nextInt(numberOfFollowers)], BitmapFactory.decodeResource(getResources(), R.drawable.collectible_item));

        this.followers = new ArrayList<>();

        this.character.setX(getWidth() / 2);
        this.character.setY(getHeight() / 2);
        this.character.setObjectOffset(this.defaultObjectOffset);
        this.character.setObjectType('p');

        this.collectible.setObjectOffset(this.defaultObjectOffset);

        this.isInitialDraw = true;
    }

    /**
     * Responsible for drawing the character GameObject charImage, collectible GameObject charImage, and background Bitmaps onto the canvas object passed as a parameter as well as displaying text representing the scoreCount attribute.
     *
     * @param canvas the canvas object that the onDraw method draws the character and collectible charImage Bitmaps and the background Bitmap onto.
     */
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Typeface typeface;
        int i;

        if(this.isInitialDraw) {
            this.background = Bitmap.createScaledBitmap(this.background, getWidth(), getHeight(), true);
            this.character.setCharImageRight(Bitmap.createScaledBitmap(this.character.getCharImageRight(), this.character.getObjectOffset(),this.character.getObjectOffset(),true));
            this.character.setCharImageLeft(Bitmap.createScaledBitmap(this.character.getCharImageLeft(), this.character.getObjectOffset(),this.character.getObjectOffset(),true));
            this.character.setCharImage(this.character.getCharImageRight());
            this.collectible.setCharImage(Bitmap.createScaledBitmap(this.collectible.getCharImage(), this.collectible.getObjectOffset(), this.collectible.getObjectOffset(), true));
            this.character.setX((int)((int)(getWidth() / 2) / this.character.getObjectOffset()) * this.character.getObjectOffset());
            this.character.setY((int)((int)(getHeight() / 2) / this.character.getObjectOffset()) * this.character.getObjectOffset());

            do {
                this.collectible.setX(generateCollectibleCoordinate(getWidth()));
                this.collectible.setY(generateCollectibleCoordinate(getHeight()));
            }while(!(isValidCollectibleLocation()));

            this.hasCollectible = true;

            scoreCount = 0;

            this.isInitialDraw = false;
        }
        updateImage();
        updateBackground();
        canvas.drawBitmap(this.background, 0,0,this.paint);
        canvas.drawBitmap(this.character.getCharImage(), this.character.getX(), this.character.getY(), this.paint);
        canvas.drawBitmap(this.collectible.getCharImage(), this.collectible.getX(), this.collectible.getY(), this.paint);
        for(i = 0; i < this.followers.size(); ++i){
            canvas.drawBitmap(this.followers.get(i).getCharImage(), this.followers.get(i).getX(), this.followers.get(i).getY(), this.paint);
        }
        int acTest = SettingsController.bkgId;

        if((acTest == 2) || (acTest == 3) || (acTest == 5) || (acTest == 6) || (acTest == 7)) {
            this.paint.setColor(getResources().getColor(R.color.light_blue));
        }
        else {
            this.paint.setColor(getResources().getColor(R.color.navy_blue));
        }
        this.paint.setTextSize(60);
        typeface = ResourcesCompat.getFont(getContext(), R.font.retro_computer_personal_use);
        this.paint.setTypeface(typeface);
        canvas.drawText("Score: " + scoreCount, 60, 110, this.paint);

        if((acTest == 2) || (acTest == 3) || (acTest == 5) || (acTest == 6) || (acTest == 7)) {
            this.paint.setColor(getResources().getColor(R.color.navy_blue));
        }
        else {
            this.paint.setColor(getResources().getColor(R.color.light_blue));
        }
        this.paint.setTextSize(60);
        typeface = ResourcesCompat.getFont(getContext(), R.font.retro_computer_personal_use);
        this.paint.setTypeface(typeface);
        canvas.drawText("Score: " + scoreCount, 50, 100, this.paint);
    }

    /**
     * Responsible for updating the x and y properties as well as the prevX and prevY properties of GameObjects rendered on the GameCanvas.
     * Returns true if GameObjects were successfully updated, otherwise returns false if the collision check for the character GameObject and the GameObjects in the followers ArrayList returns false.
     *
     * @return the boolean value representing the success of the update.
     */
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

        if((scoreCount / 5) > this.followers.size()){

            int followerIndex = this.rand.nextInt(this.numberOfFollowers);
            GameObject temp = new GameObject(this.followerImagesRight[followerIndex], this.followerImagesLeft[followerIndex]);
            temp.setObjectOffset(this.defaultObjectOffset);
            temp.setCharImage(Bitmap.createScaledBitmap(temp.getCharImage(), temp.getObjectOffset(), temp.getObjectOffset(), true));
            temp.setObjectType('p');

            if(this.followers.size() == 0){
                temp.setX(this.character.getPrevX());
                temp.setY(this.character.getPrevY());
                temp.setOrientation(this.character.getOrientation());
            }
            else{
                temp.setX(this.followers.get(this.followers.size() - 1).getPrevX());
                temp.setY(this.followers.get(this.followers.size() - 1).getPrevY());
                temp.setOrientation(this.followers.get(this.followers.size() - 1).getOrientation());
            }

            this.followers.add(temp);
        }

        for(i = 0; i < this.followers.size(); ++i){
            this.followers.get(i).setPrevX(this.followers.get(i).getX());
            this.followers.get(i).setPrevY(this.followers.get(i).getY());
            if(i == 0){
                this.followers.get(i).setX(this.character.getPrevX());
                this.followers.get(i).setY(this.character.getPrevY());
                this.followers.get(i).setOrientation(this.character.getOrientation());
            }
            else {
                this.followers.get(i).setX(this.followers.get(i - 1).getPrevX());
                this.followers.get(i).setY(this.followers.get(i - 1).getPrevY());
                this.followers.get(i).setOrientation(this.followers.get(i - 1).getOrientation());
            }

            walkCharacter(followers.get(i));

            switch(this.followers.get(i).getOrientation()){
                case 'r':
                    this.followers.get(i).setCharImage(this.followers.get(i).getCharImageRight());
                    break;
                case 'l':
                    this.followers.get(i).setCharImage(this.followers.get(i).getCharImageLeft());
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    /**
     * Updates the collectible GameObject by generating new x and y coordinates and assigning a new charImage Bitmap.
     */
    public void updateCollectibles(){
        if(!(this.hasCollectible)){
            do {
                this.collectible.setX(generateCollectibleCoordinate(getWidth()));
                this.collectible.setY(generateCollectibleCoordinate(getHeight()));
                this.collectible.setCharImage(followerImagesRight[rand.nextInt(numberOfFollowers)]);
            }while(!(isValidCollectibleLocation()));

            if(SoundManager.soundPlaying) {
                if((scoreCount + 1) % 5 == 0) {
                    soundManager.playSound(R.raw.upgrade);
                }
                else {
                    soundManager.playSound(R.raw.pointget);
                }
            }

            ++scoreCount;
        }
    }

    /**
     * Alternates a GameObject's charImage attribute between the GameObject charImageRight and GameObject charImageLeft attributes.
     *
     * @param object the GameObject whose charImage attribute is to be modified.
     */
    public void walkCharacter(GameObject object){
        switch(object.getCurrentSprite()){
            case 'r':
                object.setCharImage(object.getCharImageLeft());
                object.setCurrentSprite('l');
                break;
            case 'l':
                object.setCharImage(object.getCharImageRight());
                object.setCurrentSprite('r');
                break;
            default:
                object.setCharImage(object.getCharImageRight());
                object.setCurrentSprite('r');
                break;
        }
    }

    /**
     * Updates the character charImageRight and charImageLeft attributes based on the value of the chrId attribute of the SettingsController class.
     */
    public void updateImage() {
        switch (SettingsController.chrId) {
            case(2):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rob_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rob_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            case(3):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.will_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.will_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            case(4):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.meagan_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.meagan_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            case(5):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gman_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gman_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            case(6):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.joao_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.joao_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            case(7):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.addy_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.addy_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            case(8):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trent_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.trent_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            case(9):
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.serena_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.serena_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
            default:
                this.character.setCharImageLeft(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.character_left), this.defaultObjectOffset, this.defaultObjectOffset, true));
                this.character.setCharImageRight(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.character_right), this.defaultObjectOffset, this.defaultObjectOffset, true));
                break;
        }
    }

    /**
     * Updates the background attribute based on the value of the bkgId property of the SettingsController class.
     */
    public void updateBackground() {
        switch(SettingsController.bkgId) {
            case(2):
                this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sandy_background), getWidth(), getHeight(), true);
                break;
            case(3):
                this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.snowy_background), getWidth(), getHeight(), true);
                break;
            case(4):
                this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ocean_background), getWidth(), getHeight(), true);
                break;
            case(5):
                this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cave_background), getWidth(), getHeight(), true);
                break;
            case(6):
                this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.brick_background), getWidth(), getHeight(), true);
                break;
            case(7):
                this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.volcano_background), getWidth(), getHeight(), true);
                break;
            default:
                this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.grassy_background), getWidth(), getHeight(), true);
                break;
        }
    }

    /**
     * Generates a number from 0 to the value specified by upperBound.
     *
     * @param upperBound the upperBound for generated number.
     * @return the value generated between 0 and the upperBound.
     */
    private int generateCollectibleCoordinate(int upperBound){
        return this.collectible.getObjectOffset() * (this.rand.nextInt(upperBound - this.collectible.getObjectOffset()) / this.collectible.getObjectOffset());
    }

    /**
     * Returns a boolean value dependent on the return values of collision checks between the character and collectible objects, and between the collectible object and member objects of the followers ArrayList..
     *
     * @return boolean true if there are no collisions, false otherwise.
     */
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

    /**
     * Checks for collisions between a GameObject and the GameCanvas boundaries.
     *
     * @param gameOb the object checked for collisions with the GameCanvas
     * @return boolean true if there is a collision between the GameObject and the GameCanvas, false otherwise.
     */
    public boolean boundaryCollisionCheck(GameObject gameOb){
        if((gameOb.getLeft() < 0) || (gameOb.getRight() > getWidth()) || (gameOb.getTop() < 0) || (gameOb.getBottom() >getHeight())){
            if(SoundManager.soundPlaying) {
                soundManager.playSound(R.raw.boundaryhit);
            }
            return true;
        }
        return false;
    }

    /**
     * Checks for collisions between two GameObjects.
     *
     * @param gameOb1 the first GameObject to be used for collision checking.
     * @param gameOb2 the second GameObject to be used for collision checking.
     * @return boolean true if a collision is detected between the two GameObjects, false otherwise.
     */
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
            return (Math.abs(gameOb1.getLeft() - gameOb2.getLeft()) <= 10) && (Math.abs(gameOb1.getRight() - gameOb2.getRight()) <= 10) && (Math.abs(gameOb1.getTop() - gameOb2.getTop()) <= 10) && (Math.abs(gameOb1.getBottom() - gameOb2.getBottom()) <= 10);
        }

        if((gameOb1.getLeft() >= gameOb2.getLeft() && gameOb1.getLeft() <= gameOb2.getRight()) && ((gameOb1.getTop() >= gameOb2.getTop() && gameOb1.getTop() <= gameOb2.getBottom()) || (gameOb1.getBottom() >= gameOb2.getTop() && gameOb1.getBottom() <= gameOb2.getBottom()))){
            return true;
        }
        else if(((gameOb1.getRight() >= gameOb2.getLeft()) && (gameOb1.getRight() <= gameOb2.getRight())) && (((gameOb1.getTop() >= gameOb2.getTop()) && (gameOb1.getTop() <= gameOb2.getBottom())) || ((gameOb1.getBottom() >= gameOb2.getTop()) && (gameOb1.getBottom() <= gameOb2.getBottom())))){
            return true;
        }

        return false;
    }

    /**
     * Returns the GameCanvas character attribute.
     *
     * @return the GameCanvas character attribute.
     */
    public GameObject getCharacter(){
        return this.character;
    }

    /**
     * Returns the GameCanvas collectible attribute.
     *
     * @return the GameCanvas collectible attribute.
     */
    public GameObject getCollectible(){
        return this.collectible;
    }

    /**
     * Returns the GameCanvas scoreCount attribute.
     *
     * @return the GameCanvas scoreCount attribute.
     */
    public int getScoreCount(){
        return scoreCount;
    }

    /**
     * Sets the value of the GameCanvas scoreCount attribute to the specified value.
     *
     * @param newScoreCount the value to be assigned to the GameCanvas scoreCount attribute.
     */
    public void setScoreCount(int newScoreCount){
        scoreCount = newScoreCount;
    }

    /**
     * Sets the value of the GameCanvas hasCollectible attribute to the specified value.
     *
     * @param hasCollectible the value to be assigned to the GameCanvas hasCollectible attribute.
     */
    public void setHasCollectible(boolean hasCollectible){
        this.hasCollectible = hasCollectible;
    }
}
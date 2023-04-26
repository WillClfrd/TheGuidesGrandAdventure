package edu.utsa.cs3443.theguidesgrandadventure.Model;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

/**
 * This class represents Leaderboard objects.
 * The Leaderboard class maintains the scores ArrayList, which is an ArrayList of UserRecord objects.
 *
 * @author William Clifford (rye747), Meagan Baty (), Jose Gracia ()
 * UTSA CS 3443 - Semester Project
 * Spring 2023
 */
public class Leaderboard {
    private final ArrayList<UserRecord> scores;

    /**
     * Creates Leaderboard objects.
     * Initializes the Leaderboard object scores ArrayList.
     */
    public Leaderboard(){
        scores = new ArrayList<>();
    }

    /**
     * Opens and parses the records.csv asset file in order to create UserRecord objects to insert into the scores ArrayList.
     *
     * @param manager the AssetManager Object used to open an InputStream for the records.csv asset file if the file is unable to be opened with the openFileInput method.
     * @param activity a GameOverActivity object used to open an InputStream for the records.csv asset file.
     * @throws IOException an exception thrown if the initial attempt to open the file fails due to the file not being found.
     */
    public void loadLeaderboard(AssetManager manager, GameOverActivity activity) throws IOException {
        InputStream inFile;
        Scanner read;

        try {
            inFile = activity.openFileInput("records.csv");
            read = new Scanner(inFile);
        } catch (IOException e) {
            inFile = manager.open("records.csv");
            read = new Scanner(inFile);
        }

        UserRecord tempRecord;
        String line;
        String[] lineTokens;
        Bitmap tempImage;

        while(read.hasNextLine()){
            line = read.nextLine();
            lineTokens = line.split(",");
            tempImage = findCharacterImage(lineTokens[2], activity);
            Log.d("Line value", line);
            tempRecord = new UserRecord(lineTokens[0], parseInt(lineTokens[1]), lineTokens[2], tempImage);
            scores.add(tempRecord);
        }
        inFile.close();
        read.close();
    }

    /**
     * Takes in a String charName in order to return the relevant Bitmap Object.
     *
     * @param charName the String passed in for comparison.
     * @param activity the GameOverActivity object used to retrieve drawable assets in order to create the relevant bitmap for return.
     * @return the Bitmap object corresponding to the charName parameter.
     */
    private Bitmap findCharacterImage(String charName, GameOverActivity activity){
        if(charName.equalsIgnoreCase("Rob")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.rob_left);
        }
        else if(charName.equalsIgnoreCase("Will")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.will_left);
        }
        else if(charName.equalsIgnoreCase("Meagan")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.meagan_left);
        }
        else if(charName.equalsIgnoreCase("Gman")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.gman_left);
        }
        else if(charName.equalsIgnoreCase("Joao")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.joao_left);
        }
        else if(charName.equalsIgnoreCase("Addy")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.addy_left);
        }
        else if(charName.equalsIgnoreCase("Trent")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.trent_left);
        }
        else if(charName.equalsIgnoreCase("Serena")){
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.serena_left);
        }
        else{
            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.character_left);
        }
    }

    /**
     * Opens the records.csv asset file and writes the elements of the scores ArrayList to the file as formatted strings.
     *
     * @param context the Context object used to open an OutputStream to the records.csv asset file.
     * @throws IOException an exception thrown when the attempt to open the OutputStream fails.
     */
    public void saveLeaderboard(Context context) throws IOException {
        OutputStream outFile = null;
        try {
            outFile = context.openFileOutput("records.csv", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int i;

        for(i = 0; i < scores.size(); ++i){
            String tempString = scores.get(i).getUserName() + "," + scores.get(i).getScore() + "," + scores.get(i).getCharName() + "\n";
            Log.d("Record", tempString);
            outFile.write(tempString.getBytes());
        }
        outFile.close();
    }

    /**
     * Adds a UserRecord object to the scores ArrayList at the specified index.
     *
     * @param record the UserRecord object to be added to the scores ArrayList.
     * @param index the index at which the UserRecord object will be inserted.
     */
    public void insertScoreRecord(UserRecord record, int index){
        scores.add(index, record);
    }

    /**
     * Adds a UserRecord object as the last element of the scores ArrayList.
     *
     * @param record the UserRecord to be added to the end of the scores ArrayList.
     */
    public void appendScoreRecord(UserRecord record){
        scores.add(record);
    }

    /**
     * Removes the last element of the scores ArrayList.
     */
    public void removeEndScore(){
        scores.remove(scores.size() - 1);
    }

    /**
     * Returns the UserRecord object at the specified index of the scores ArrayList.
     *
     * @return the UserRecord object located at the specified index of the scores ArrayList.
     */
    public UserRecord getRecord(int index){
        return scores.get(index);
    }

    /**
     * Returns the size of the scores ArrayList.
     *
     * @return the integer value of the size of the scores ArrayList.
     */
    public int getScoresSize(){
        return scores.size();
    }
}

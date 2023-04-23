package edu.utsa.cs3443.theguidesgrandadventure.Model;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.utsa.cs3443.theguidesgrandadventure.GameOverActivity;
import edu.utsa.cs3443.theguidesgrandadventure.R;

public class Leaderboard {
    private ArrayList<UserRecord> scores;

    public Leaderboard(){
        scores = new ArrayList<UserRecord>();
    }

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
            if(line != null) {
                Log.d("Line value", line);
                tempRecord = new UserRecord(lineTokens[0], parseInt(lineTokens[1]), lineTokens[2], tempImage);
                scores.add(tempRecord);
            }
        }
        inFile.close();
        read.close();
    }

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

    public void saveLeaderboard(Context context) throws IOException {
        OutputStream outFile = context.openFileOutput("records.csv", Context.MODE_PRIVATE);
        int i;

        for(i = 0; i < scores.size(); ++i){
            String tempString = scores.get(i).getUserName() + "," + scores.get(i).getScore() + "," + scores.get(i).getCharName() + "\n";
            Log.d("Record", tempString);
            outFile.write(tempString.getBytes());
        }
        outFile.close();
    }

    public void insertScoreRecord(UserRecord record, int index){
        scores.add(index, record);
    }

    public void appendScoreRecord(UserRecord record){
        scores.add(record);
    }

    public void removeEndScore(){
        scores.remove(scores.size() - 1);
    }

    public UserRecord getRecord(int index){
        return scores.get(index);
    }
    public int getScoresSize(){
        return scores.size();
    }
}

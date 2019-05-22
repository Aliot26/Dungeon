package com.codecool.quest;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Utils {

    public void playSound(String pathToFile) {
        try {
            Media sound = new Media(new File(pathToFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

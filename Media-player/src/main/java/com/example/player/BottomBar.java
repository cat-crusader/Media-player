package com.example.player;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import com.example.player.bottom.PlayButton;
import com.example.player.bottom.Time;
import com.example.player.bottom.Volume;

public class BottomBar extends HBox {
    public BottomBar(MediaPlayer mediaPlayer) {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));
        getChildren()
                .addAll(new PlayButton(mediaPlayer), new Time(mediaPlayer), new Volume(mediaPlayer));
        setStyle("-fx-background-color:#444444");
        HBox.setHgrow(getChildren().get(1), Priority.ALWAYS);
    }
}


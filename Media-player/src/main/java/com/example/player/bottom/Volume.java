package com.example.player.bottom;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class Volume extends HBox {
    public Volume(MediaPlayer mediaPlayer) {
        setPadding(new Insets(10));

        Label label = new Label("Volume: ");
        label.setMinWidth(50);

        Slider slider = new Slider();
        slider.setMinWidth(70);
        slider.setValue(100);

        slider
                .valueProperty()
                .addListener(
                        x -> {
                            if (slider.isPressed()) {
                                mediaPlayer.setVolume(slider.getValue() / 100);
                            }
                        });

        getChildren().addAll(label, slider);
    }
}
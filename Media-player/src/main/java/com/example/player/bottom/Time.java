package com.example.player.bottom;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Time extends HBox {
    public Time(MediaPlayer mediaPlayer) {

        setPadding(new Insets(10));

        Label label = new Label();
        label.setMinWidth(80);

        Slider slider = new Slider();
        slider.setPrefWidth(2000);

        slider.setPadding(new Insets(0));
        mediaPlayer
                .currentTimeProperty()
                .addListener(
                        x ->
                                Platform.runLater(
                                        () -> {
                                            slider.setValue(
                                                    mediaPlayer
                                                            .getCurrentTime()
                                                            .divide(mediaPlayer.getTotalDuration().toMillis())
                                                            .multiply(100)
                                                            .toMillis());
                                            label.setText(getTimeLabel(mediaPlayer));
                                        }));

        slider
                .valueProperty()
                .addListener(
                        x -> {
                            if (slider.isPressed()) {
                                mediaPlayer.seek(
                                        mediaPlayer.getMedia().getDuration().multiply(slider.getValue()).divide(100));
                                label.setText(getTimeLabel(mediaPlayer));
                            }
                        });
        getChildren().addAll(label, slider);
    }

    private String getTimeLabel(MediaPlayer mediaPlayer) {
        return "%s/%s"
                .formatted(
                        getTimeLabel(mediaPlayer.getCurrentTime()),
                        getTimeLabel(mediaPlayer.getTotalDuration()));
    }

    private String getTimeLabel(Duration duration) {
        int hours = (int) duration.toHours();
        int minutes = ((int) duration.toMinutes()) % 60;
        int seconds = ((int) duration.toSeconds()) % 60;

        if (hours == 0) {
            return "%02d:%02d".formatted(minutes, seconds);
        }
        return "%02d:%02d:%02d".formatted(hours, minutes, seconds);
    }
}
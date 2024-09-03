package com.dev.pos.controller;

import com.dev.pos.util.splash.LoadingTask;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class SplashScreenController {
    public Rectangle rectangleMain;
    public Rectangle rectangleSub;
    public Label lblProgress;

    public void initialize() {
        LoadingTask task = new LoadingTask();

        task.progressProperty().addListener((observable, oldValue, newValue) -> {

            String formattedNumber = String.format("%.0f", newValue.doubleValue() * 100);
            lblProgress.setText(formattedNumber + " %");

            rectangleSub.setWidth(rectangleMain.getWidth() * newValue.doubleValue());

        });

        new Thread(task).start();

    }

}

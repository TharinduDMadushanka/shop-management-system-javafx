package com.dev.pos.controller;

import com.dev.pos.util.splash.LoadingTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class SplashScreenController {
    public Rectangle rectangleMain;
    public Rectangle rectangleSub;
    public Label lblProgress;
    public AnchorPane context;

    public void initialize() throws IOException {
        LoadingTask task = new LoadingTask();

        task.progressProperty().addListener((observable, oldValue, newValue) -> {

            String formattedNumber = String.format("%.0f", newValue.doubleValue() * 100);
            lblProgress.setText(formattedNumber + " %");

            rectangleSub.setWidth(rectangleMain.getWidth() * newValue.doubleValue());

        });

        new Thread(task).start();

        task.setOnSucceeded(event -> {
            try {
                setUI("LoginForm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }

}

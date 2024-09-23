package com.dev.pos.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane mainContext;

    public void initialize() {
        setContext("DashboardForm");
    }


//    private void setUI(String location) throws IOException {
//        Stage stage = (Stage)btnLogOut.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/view/" + location + ".fxml"))));
//        stage.show();
//        stage.centerOnScreen();
//
//    }

    private void setContext(String location){

        try {
            mainContext.getChildren().clear();
            mainContext.getChildren().add(FXMLLoader.load(getClass().getResource("/com/dev/pos/view/"+location+".fxml")));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

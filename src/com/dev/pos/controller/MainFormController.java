package com.dev.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainFormController {

    @FXML
    public Button btnLogOut;
    @FXML
    private AnchorPane mainContext;

    public void initialize() {
        setContext("DashboardForm");
    }

    public void customerOnAction(ActionEvent actionEvent) {
    }

    public void productOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }

    public void OrderDetailOnAction(ActionEvent actionEvent) {
    }

    public void reportOnAction(ActionEvent actionEvent) {
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        // Create a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);  // Optionally, you can add a header
        alert.setContentText("Are you sure you want to log out?");

        // Display the alert and wait for user action
        Optional<ButtonType> result = alert.showAndWait();

        // Check if user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Navigate to the login page
            setUI("LoginForm");  // Implement setUI() to load the login form
        }
    }


    private void setUI(String location) throws IOException {
        Stage stage = (Stage)btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/dev/pos/view/" + location + ".fxml"))));
        stage.show();
        stage.centerOnScreen();

    }

    private void setContext(String location){

        try {
            mainContext.getChildren().clear();
            mainContext.getChildren().add(FXMLLoader.load(getClass().getResource("/com/dev/pos/view/"+location+".fxml")));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

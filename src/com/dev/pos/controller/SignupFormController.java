package com.dev.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupFormController {

    public AnchorPane context;
    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtReEnteredPW;

    @FXML
    private TextField txtUserName;

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        setUI("LoginForm");
    }

    @FXML
    void signupOnAction(ActionEvent event) {

    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

}

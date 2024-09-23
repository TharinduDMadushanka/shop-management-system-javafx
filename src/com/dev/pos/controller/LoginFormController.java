package com.dev.pos.controller;

import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.UserBo;
import com.dev.pos.dto.UserDTO;
import com.dev.pos.util.Enum.BoType;
import com.dev.pos.util.security.PasswordManager;
import com.dev.pos.util.security.UserSessionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane context;

    @FXML
    private Hyperlink signupLink;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @FXML
    void loginOnAction(ActionEvent event) {

        try {

            UserDTO user = userBo.findUser(txtEmail.getText());

            if(user != null){
                if(PasswordManager.checkPassword(txtPassword.getText(), user.getPassword())){
                    UserSessionData.email= txtEmail.getText().trim();
                    setUI("MainForm");
//                    new Alert(Alert.AlertType.INFORMATION, "Hi.!").show();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Wrong password.!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "User not found.!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void signupOnAction(ActionEvent event) throws IOException {
        setUI("SignupForm");
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

}

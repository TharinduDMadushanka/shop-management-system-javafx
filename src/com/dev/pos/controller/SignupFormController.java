package com.dev.pos.controller;

import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.UserBo;
import com.dev.pos.dto.UserDTO;
import com.dev.pos.util.Enum.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        setUI("LoginForm");
    }

    @FXML
    void signupOnAction(ActionEvent event) {

        try {

            UserDTO userDTO = new UserDTO(
                    txtEmail.getText(),
                    txtPassword.getText().trim()
            );

            String pw = txtPassword.getText().trim();
            String ReEnteredPW = txtReEnteredPW.getText().trim();

            if (pw.equals(ReEnteredPW)) {

                boolean isSaved = userBo.saveUser(userDTO);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION,"User successfully registered!").show();
                    setUI("LoginForm");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR,"Please Enter Confirm Password correctly..!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

}

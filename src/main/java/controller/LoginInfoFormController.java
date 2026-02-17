package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginInfoFormController {

    Stage stage = new Stage();

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
            String userName = txtUserName.getText();
            String password = txtPassword.getText();

            if (userName.equals("hashini") && password.equals("1234")) {

                Stage stage = (Stage) txtUserName.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
                stage.setTitle("YR Food City");
                stage.show();

            } else {
                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "Username or Password wrong!").show();
            }
    }
}



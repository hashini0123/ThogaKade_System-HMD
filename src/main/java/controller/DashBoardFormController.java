package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    Stage stage = new Stage();

    @FXML
    private Button txtCustomerManagement;

    @FXML
    private Button txtItemManagement;

    @FXML
    private Button txtOrderDetailManagement;

    @FXML
    private Button txtOrderManagement;

    @FXML
    void btnCustomerManagementOnAction(ActionEvent event) throws IOException {

        Stage newStage = new Stage();

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customer_info.fxml"));

        Scene scene = new Scene(rootNode);

        newStage.setScene(scene);
        newStage.setTitle("Customer Management");

        newStage.show();

    }

    @FXML
    void btnItemManagementOnAction(ActionEvent event) throws IOException {

        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/item_info.fxml"));

            Scene scene = new Scene(rootNode);


            stage.setScene(scene);
            stage.setTitle("Item Management");

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOrderDetailManagementOnAction(ActionEvent event) throws IOException {

       // Stage newStage = new Stage();

        Parent rootNode = null;

        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/order_details_info.fxml"));

            Scene scene = new Scene(rootNode);

            stage.setScene(scene);
            stage.setTitle("Order Details Management");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) throws IOException {

       // Stage newStage = new Stage();

        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/order_info.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(rootNode);

        stage.setScene(scene);
        stage.setTitle("Order Management");

        stage.show();

    }

}
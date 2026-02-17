package controller.order_details;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.OrderDetailsInfoDTO;
import service.OrderDetailsServiceImpl;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class OrderDetailsInfoFormController implements Initializable {

    OrderDetailsServiceImpl orderDetailsController = new OrderDetailsServiceImpl();

    ObservableList<OrderDetailsInfoDTO> orderDetailsInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableView<OrderDetailsInfoDTO> tblOrderDetailsInfo;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtOrderQty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadTableOrderDetails();

        tblOrderDetailsInfo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {

            if (newValue != null){
                txtOrderID.setText(newValue.getOrderID());
                txtItemCode.setText(newValue.getItemCode());
                txtOrderQty.setText(String.valueOf(newValue.getOrderQty()));
                txtDiscount.setText(String.valueOf(newValue.getDiscount()));
            }
        });
    }

    public void loadTableOrderDetails(){
        tblOrderDetailsInfo.setItems(orderDetailsController.getAllOrderDetails());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String orderID = txtOrderID.getText();
        String itemCode = txtItemCode.getText();
        Integer orderQty = Integer.valueOf(txtOrderQty.getText());
        double discount = Double.parseDouble(txtDiscount.getText());

        orderDetailsController.add(orderID,itemCode,orderQty,discount);

        clearFiled();
        loadTableOrderDetails();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String orderID = txtOrderID.getText();
        orderDetailsController.delete(orderID);

        clearFiled();
        loadTableOrderDetails();

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException {

        loadTableOrderDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String orderID = txtOrderID.getText();
        String itemCode = txtItemCode.getText();
        Integer orderQty = Integer.valueOf(txtOrderQty.getText());
        double discount = Double.parseDouble(txtDiscount.getText());
        orderDetailsController.update(itemCode,orderQty,discount,orderID);

        loadTableOrderDetails();
        clearFiled();

    }

    public void clearFiled(){
        txtOrderID.clear();
        txtItemCode.clear();
        txtOrderQty.clear();
        txtDiscount.clear();
    }


}

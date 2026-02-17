package controller.order;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.OrderInfoDTO;
import service.OrderServiceImpl;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderInfoFormContoller implements Initializable {

    OrderServiceImpl orderController = new OrderServiceImpl();

    ObservableList<OrderInfoDTO> orderInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableView<OrderInfoDTO> tblOrderInfo;

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtOrderID;
    private Object orderDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));

        tblOrderInfo.setItems(orderInfoDTOS);

        tblOrderInfo.getSelectionModel().selectedItemProperty().addListener((observableValue,
                                                                             oldValue, newValue) ->{

            if(newValue != null){
                txtOrderID.setText(newValue.getOrderID());
                txtOrderDate.setText(String.valueOf(newValue.getOrderDate()));
                txtCustID.setText(newValue.getCustID());

            }
                });

        try {
            loadTableOrder();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTableOrder() throws SQLException {

        orderInfoDTOS.clear();
        tblOrderInfo.setItems(orderController.getAllOrder());

    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        String orderID = txtOrderID.getText();
        LocalDate orderDate = LocalDate.parse(txtOrderDate.getText());
        String custID = txtCustID.getText();

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO(orderID,orderDate,custID);

        orderInfoDTOS.add(orderInfoDTO);

        orderController.add(orderID,orderDate,custID);

       loadTableOrder();
       clearField();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String orderID = txtOrderID.getText();
        orderController.delete(orderID);

        try {
            loadTableOrder();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clearField();

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException {

        loadTableOrder();
        clearField();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String orderID = txtOrderID.getText();
        LocalDate orderDate = LocalDate.parse(txtOrderDate.getText());
        String custID = txtCustID.getText();

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO(orderID,orderDate,custID);
        orderInfoDTOS.add(orderInfoDTO);
        orderController.update(orderDate,custID,orderID);

        try {
            loadTableOrder();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clearField();
    }

    public void clearField(){
        txtOrderID.clear();
        txtOrderDate.clear();
        txtCustID.clear();
    }

}


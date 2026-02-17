package controller.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.ItemInfoDTO;
import service.ItemServiceImpl;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ItemInfoFormController implements Initializable {

    ItemServiceImpl itemController = new ItemServiceImpl();

    ObservableList<ItemInfoDTO> itemInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableView<ItemInfoDTO> tblItemInfo;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TableColumn<?, ?> txtUnitPrice1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadTableItem();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItemInfo.getSelectionModel().selectedItemProperty().addListener(((observableValue,
                                                                             oldValue, newValue) -> {

            if(newValue != null) {
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPackSize());
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
            }
        }));
    }



    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());

        ItemInfoDTO itemInfoDTO = new ItemInfoDTO(itemCode, description, packSize, unitPrice, qtyOnHand);
        itemInfoDTOS.add(itemInfoDTO);
        itemController.add(itemCode, description, packSize, unitPrice, qtyOnHand);
        clearField();
        loadTableItem();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String itemCode = txtItemCode.getText();
        itemController.delete(itemCode);

        clearField();

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException {

        loadTableItem();
        clearField();
    }

    @FXML
    void btnUpadateOnAction(ActionEvent event)  {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());
        itemController.update(description, packSize, unitPrice, qtyOnHand,itemCode);

        loadTableItem();
        clearField();


    }

    public void clearField(){
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }
    public void loadTableItem(){
        tblItemInfo.setItems(itemController.getAllItems());

    }
}



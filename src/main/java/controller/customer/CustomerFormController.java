package controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerInfoDTO;
import service.CustomerServiceImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    CustomerServiceImpl customerController = new CustomerServiceImpl();

    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustAddress;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colCustName;

    @FXML
    private TableColumn<?, ?> colCustTitle;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<CustomerInfoDTO> tblCustomerInfo;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustAdderss;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtCustTitle;

    @FXML
    private DatePicker dateDOB;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();

        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        //tblCustomerInfo.setItems(customerInfoDTOS);

        tblCustomerInfo.getSelectionModel().selectedItemProperty().addListener(((
                observableValue, oldValue, newValue) -> {
            if(newValue != null){
                txtCustId.setText(newValue.getCustId());
                txtCustTitle.setText(newValue.getCustTitle());
                txtCustName.setText(newValue.getCustName());
                dateDOB.setValue(newValue.getDOB());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtCustAdderss.setText(newValue.getCustAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());

            }
        }));

    }

    private void loadTable() {
        tblCustomerInfo.setItems(customerController.getAllCustomers());
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

        loadTable();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String custId = txtCustId.getText();
        String custTitle = txtCustTitle.getText();
        String custName = txtCustName.getText();
        LocalDate DOB = dateDOB.getValue();
        double salary = Double.parseDouble(txtSalary.getText());
        String custAddress = txtCustAdderss.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalcode = txtPostalCode.getText();


        customerController.add(custId, custTitle, custName, DOB, salary, custAddress, city, province, postalcode);

        loadTable();
        clearField();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String custId = txtCustId.getText();
        customerController.delete(custId);

        clearField();
        loadTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String custId = txtCustId.getText();
        String custTitle = txtCustTitle.getText();
        String custName = txtCustName.getText();
        LocalDate DOB = dateDOB.getValue();
        double salary = Double.parseDouble(txtSalary.getText());
        String custAddress = txtCustAdderss.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalcode = txtPostalCode.getText();

        customerController.update(custTitle, custName, DOB, salary, custAddress, city, province, postalcode,custId);

        clearField();
        loadTable();

    }

    public void clearField(){
        txtCustId.clear();
        txtCustTitle.clear();
        txtCustName.clear();
        //txtDOB.clear();
        txtSalary.clear();
        txtCustAdderss.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }
}
package service;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

import java.sql.*;
import java.time.LocalDate;

public class CustomerServiceImpl implements CustomerService {


    @Override
    public void add(String custId, String custTitle, String custName, LocalDate DOB, double salary, String  custAddress,
                    String city, String province, String postalcode) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1,custId);
            preparedStatement.setObject(2,custTitle);
            preparedStatement.setObject(3,custName);
            preparedStatement.setObject(4,DOB);
            preparedStatement.setObject(5,salary);
            preparedStatement.setObject(6,custAddress);
            preparedStatement.setObject(7,city);
            preparedStatement.setObject(8,province);
            preparedStatement.setObject(9,postalcode);

            preparedStatement.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String custTitle, String custName, LocalDate DOB, double salary, String custAddress,
                       String city, String province, String postalcode,String custId) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root"
                    ,"1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET custTitle=?,custName=?, dob=?, salary=?, custAddress=?, city=?, province=?, postalcode=? WHERE CustID=?");

            preparedStatement.setObject(1,custTitle);
            preparedStatement.setObject(2,custName);
            preparedStatement.setObject(3,DOB);
            preparedStatement.setObject(4,salary);
            preparedStatement.setObject(5,custAddress);
            preparedStatement.setObject(6,city);
            preparedStatement.setObject(7,province);
            preparedStatement.setObject(8,postalcode);
            preparedStatement.setObject(9,custId);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String custId) {
        try {
            Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root",
                    "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE CustID=?");

            preparedStatement.setObject(1,custId);
            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<CustomerInfoDTO> getAllCustomers(){
        ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root",
                    "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerInfoDTOS.add(new CustomerInfoDTO(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerInfoDTOS;
    }
}

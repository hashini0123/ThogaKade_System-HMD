package service;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.OrderInfoDTO;

import java.sql.*;
import java.time.LocalDate;

public class OrderServiceImpl implements OrderService {

    @Override
    public void add(String orderId, LocalDate orderDate, String custID) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?)");

            preparedStatement.setObject(1,orderId);
            preparedStatement.setObject(2,orderDate);
            preparedStatement.setObject(3,custID);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(LocalDate orderDate,String custID,String orderId) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET orderDate=?,custID=?,WHERE OrderID=?");

            preparedStatement.setObject(1,orderDate);
            preparedStatement.setObject(2,custID);
            preparedStatement.setObject(3,orderId);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String orderId) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root",
                    "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM orders WHERE OrderID=?");

            preparedStatement.setObject(1,orderId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<OrderInfoDTO> getAllOrder(){

        ObservableList<OrderInfoDTO> orderInfoDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root",
                    "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                orderInfoDTOS.add(new OrderInfoDTO(
                        resultSet.getString("OrderID"),
                        resultSet.getDate("OrderDate").toLocalDate(),
                        resultSet.getString("CustID")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderInfoDTOS;
    }
}

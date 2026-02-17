package service;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.OrderDetailsInfoDTO;

import java.sql.*;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Override
    public void add(String orderID, String itemCode, Integer orderQty, double discount) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderdetail VALUES(?,?,?,?)");

            preparedStatement.setObject(1,orderID);
            preparedStatement.setObject(2,itemCode);
            preparedStatement.setObject(3,orderQty);
            preparedStatement.setObject(4,discount);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String itemCode, Integer orderQty, double discount, String orderID) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orderdetail SET ItemCode=?, OrderQty=?, Discount=? WHERE OrderID=? ");

            preparedStatement.setObject(1,itemCode);
            preparedStatement.setObject(2,orderQty);
            preparedStatement.setObject(3,discount);
            preparedStatement.setObject(4,orderID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(String orderID) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM orderdetail WHERE OrderID=?");

            preparedStatement.setObject(1,orderID);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public ObservableList<OrderDetailsInfoDTO> getAllOrderDetails() {
        ObservableList<OrderDetailsInfoDTO> orderDetailsInfoDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orderdetail");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                orderDetailsInfoDTOS.add(new OrderDetailsInfoDTO(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("OrderQty"),
                        resultSet.getDouble("Discount")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderDetailsInfoDTOS;
    }
}

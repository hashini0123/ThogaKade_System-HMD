package service;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.ItemInfoDTO;

import java.sql.*;

public class ItemServiceImpl implements ItemService {


    @Override
    public void add(String itemCode, String description, String packSize, Double unitPrice, Integer qtyOnHand) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?,?)");

            preparedStatement.setObject(1,itemCode);
            preparedStatement.setObject(2,description);
            preparedStatement.setObject(3,packSize);
            preparedStatement.setObject(4,unitPrice);
            preparedStatement.setObject(5,qtyOnHand);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String description, String packSize, Double unitPrice, Integer qtyOnHand, String itemCode) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item SET  Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=? ");

            preparedStatement.setObject(1,description);
            preparedStatement.setObject(2,packSize);
            preparedStatement.setObject(3,unitPrice);
            preparedStatement.setObject(4,qtyOnHand);
            preparedStatement.setObject(5,itemCode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String itemCode) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root",
                    "1234");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE ItemCode=?");

            preparedStatement.setObject(1,itemCode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ItemInfoDTO> getAllItems() {
        ObservableList<ItemInfoDTO> itemInfoDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root",
                    "1234");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                itemInfoDTOS.add(new ItemInfoDTO(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemInfoDTOS;
    }
}

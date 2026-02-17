package service;

import javafx.collections.ObservableList;
import model.dto.OrderInfoDTO;

import java.time.LocalDate;

public interface OrderService {
    void add(String orderId, LocalDate orderDate, String custID);

    void update(LocalDate orderDate,String custID,String orderId);

    void delete(String orderId);

    ObservableList<OrderInfoDTO> getAllOrder();
}

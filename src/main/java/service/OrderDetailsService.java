package service;

import javafx.collections.ObservableList;
import model.dto.OrderDetailsInfoDTO;

public interface OrderDetailsService {

    void add(String orderID, String itemCode, Integer orderQty, double discount);

    void update(String itemCode, Integer orderQty, double discount, String orderID);

    void delete(String orderID);

    ObservableList<OrderDetailsInfoDTO> getAllOrderDetails();

}

package service;

import javafx.collections.ObservableList;
import model.dto.ItemInfoDTO;

public interface ItemService {

    void add(String itemCode, String description, String packSize, Double unitPrice, Integer qtyOnHand);
    void update(String description, String packSize, Double unitPrice, Integer qtyOnHand,String itemCode);
    void delete(String itemCode);
    ObservableList<ItemInfoDTO> getAllItems();
}

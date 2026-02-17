package service;

import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

import java.time.LocalDate;

public interface CustomerService {
    void add(String custId, String custTitle, String custName, LocalDate DOB, double salary, String  custAddress, String city, String province, String postalcode);
    void update(String custTitle, String custName, LocalDate DOB, double salary, String custAddress, String city, String province, String postalcode,String custId);
    void delete(String custId);
    ObservableList<CustomerInfoDTO> getAllCustomers();

}

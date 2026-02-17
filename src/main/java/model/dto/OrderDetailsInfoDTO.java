package model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailsInfoDTO {

    private String orderID;
    private String itemCode;
    private Integer orderQty;
    private double discount;

//    public OrderDetailsInfoDTO(String orderID, String itemCode, double discount) {
//    }
}

package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfoDTO {
    private String custId;

    private String CustTitle ;

    private String CustName;

    private LocalDate DOB;

    private double Salary;

    private String CustAddress;

    private String City;

    private String Province;

    private String PostalCode;
}

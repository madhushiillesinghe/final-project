package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class productDto {
    private String p_code;
    private double unit_price;
    private String description;
    private int qty_on_stock;
    private Date expire_date;
}

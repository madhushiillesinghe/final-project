package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductDto {
    private String p_code;
    private double unit_price;
    private String description;
    private int qty_on_stock;
    private String expire_date;
}

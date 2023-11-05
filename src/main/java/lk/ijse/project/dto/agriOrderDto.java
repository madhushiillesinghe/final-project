package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class agriOrderDto {
    private String id;
    private String produtName;
    private double unitPrice;
    private int quantity;
    private Data expireDate;
}

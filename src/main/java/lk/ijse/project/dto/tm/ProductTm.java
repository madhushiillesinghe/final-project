package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductTm {
    private String id;
    private String name;
    private double unitPrice;
    private int qty;
    private String expireDate;
}

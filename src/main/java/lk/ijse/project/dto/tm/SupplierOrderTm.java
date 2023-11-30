package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierOrderTm {
    private String code;
    private String name;
    private double price;
    private int qty;
    private double total;
}

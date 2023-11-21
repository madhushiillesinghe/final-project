package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplyProductCartTm {
    private String OrderId;
    private String supId;
    private String productCode;
    private String qty;
    private String total;
}

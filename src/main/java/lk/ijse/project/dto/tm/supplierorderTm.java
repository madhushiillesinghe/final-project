package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class supplierorderTm {
    private String code;
    private String name;
    private double price;
    private int qty;
    private double total;
}

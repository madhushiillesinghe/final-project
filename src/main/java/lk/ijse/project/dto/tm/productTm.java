package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class productTm {
    private String id;
    private String name;
    private double unitPrice;
    private int qty;
    private String expireDate;
}

package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class supplierDto {
    private String sup_id;
    private int contact_no;
    private String emp_id;
    private String supplier_product_type;
    private String email;
    private String first_name;
    private String last_name;
    private int nic;
}

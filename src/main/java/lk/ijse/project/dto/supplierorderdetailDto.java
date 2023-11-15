package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class supplierorderdetailDto {
    private String p_id;
    private String  sup_o_id;
    private Date date;
    private String payment_method;
    private int count_of_supplyproduct;
    private double discount_fee;
    private double selling_product_amount;
    private double after_discount_total_amount;
}

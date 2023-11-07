package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class supplierpaymentDto {
    private String p_id;
    private String sup_o_id;
    private Date date;
    private String r_code;
    private String payment_method;
    private String count_of_supplyproduct;
    private double discount_fee;
    private double selling_product_amount;
    private double after_discount_total_amount;
    private double percentage_of_product;

}

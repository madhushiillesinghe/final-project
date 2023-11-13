package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class machineOrderDetailDto {
    private Date order_date;
    private String payment_method;
    private String pay_id;
    private int no_of_days_keep_the_machine;
    private int qty_on_hand;
    private String  o_id;
    private String m_id;
    private double rental_payment;
    private double deliver_charge;
    private double products_amount;
    private double full_amount_of_products;

}

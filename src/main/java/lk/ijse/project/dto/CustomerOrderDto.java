package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderDto {
    private String cus_order_id;
    private String cus_id;
    private String  date;
    private ArrayList<String[]> tmlist=new ArrayList<>();
}

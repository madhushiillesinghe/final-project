package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class SupplyOrderDto {
    private String sup_order_id;
    private String sup_id;
    private String date;
    private ArrayList<String[]>  tmlist=new ArrayList<>();

}

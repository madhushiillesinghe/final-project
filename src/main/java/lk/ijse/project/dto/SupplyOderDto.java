package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class SupplyOderDto {
    private String sup_order_id;
    private String sup_id;
    private String date;
    private ArrayList<String[]>  tmlist=new ArrayList<>();

}

package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class machineOrderDto {
    private String cus_rent_id;
    private String cus_id;
    private String m_id;
    private String  date;
    private ArrayList<String[]>  tmlist=new ArrayList<>();
}

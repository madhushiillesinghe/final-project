package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class machineDto {
    private String m_id;
    private String  m_name;
    private String m_task;
    private int machine_qty;
    private String order_id;
    private int machine_per_day_amount;
}

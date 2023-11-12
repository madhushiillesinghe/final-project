package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class addmachinedto {
    private String m_id;
    private String  m_name;
    private String m_task;
    private int machine_qty;
    private int machine_per_day_amount;
}

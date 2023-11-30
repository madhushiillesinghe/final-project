package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MachineTm {
    private String id;
    private  String Name;
    private int rent_perday;
    private int Quantity;
}

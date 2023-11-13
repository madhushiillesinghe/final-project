package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class machineTm {
    private String id;
    private  String Name;
    private int rent_perday;
    private int Quantity;
    private Icon delete;
    private  Icon update;
    private Icon detail;
}

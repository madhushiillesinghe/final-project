package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class machineorderTm {
    private String  id;
    private String name;
    private int rent;
    private int days;
    private int total;
}

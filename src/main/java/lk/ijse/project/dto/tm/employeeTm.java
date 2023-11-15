package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class employeeTm {
    private String Id;
    private String Name;
    private String Address;
    private String Role;
    private int Mobile;
    private String Email;

}

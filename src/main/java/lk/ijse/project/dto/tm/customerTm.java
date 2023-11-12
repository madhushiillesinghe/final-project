package lk.ijse.project.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class customerTm {
    private String Id;
    private String Name;
    private String Address;
    private String Account;
    private int Mobile;
    private String Email;
}

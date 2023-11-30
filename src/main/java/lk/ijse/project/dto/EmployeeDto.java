package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDto {
    private String emp_id;
    private String city;
    private String street;
    private  int house_no;
    private int contact_no;
    private String role;
    private String user_name;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String nic;

}

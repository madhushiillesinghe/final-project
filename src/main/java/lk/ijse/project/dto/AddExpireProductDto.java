package lk.ijse.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddExpireProductDto {
    private String p_code;
    private String description;
    private int count;
    private ArrayList<String[]> tmlist=new ArrayList<>();

}

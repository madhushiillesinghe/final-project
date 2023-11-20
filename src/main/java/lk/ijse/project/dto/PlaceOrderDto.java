package lk.ijse.project.dto;

import lk.ijse.project.dto.tm.supplierorderTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.dto.supplyOderDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderDto {
    private String orderId;
    private String supId;
    private Date date;
    private List<supplierorderTm> tmlist=new ArrayList<>();
}

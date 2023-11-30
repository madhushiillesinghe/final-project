//package lk.ijse.project.controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.text.Text;
//import lk.ijse.project.dto.productDto;
//import lk.ijse.project.model.ProductModel;
//import lk.ijse.project.model.supplierModel;
//import lk.ijse.project.util.Navigation;
//import javafx.util.Duration;
//
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class ExpireProductPopUpController implements Initializable {
//
//    @FXML
//    private Text txtBoatId;
//
//    @FXML
//    private Text txtDate;
//
//    @FXML
//    private Text txtproductname;
//
//    @FXML
//    private Text txtproductprice;
//
//    @FXML
//    private Text txtproductqty;
//
//    private String expiredaate;
//
//    private String id;
//    ProductModel promodel = new ProductModel();
//
//    productDto dto = new productDto();
//
//
//    @FXML
//    void closeOnMouseClicked(ActionEvent event) {
//        Navigation.close(event);
//    }
//
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////
////        try {
////            getAllIds();
////            promodel.searchProduct(id);
////            expiredaate = dto.getExpire_date();
////          checkExpiryDate();
////           /*String date= String.valueOf(exdate);
////*/
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////
////    }
//
////    private void checkExpiryDate() throws IOException {
////
////
////
////        int notifydate=Integer.parseInt("2023/12/31");
////        if(expiredate<notifydate){
////            txtBoatId.setText(dto.getP_code());
////            txtproductname.setText(dto.getDescription());
////            txtproductprice.setText(String.valueOf(dto.getUnit_price()));
////            txtproductqty.setText(String.valueOf(dto.getQty_on_stock()));
////            txtDate.setText(dto.getExpire_date());
////
////            Navigation.popupNavigation("ExpireProductPopUpForm.fxml");
////        }
////
////    }
//
//    private String getAllIds() throws SQLException {
//        ArrayList<String> list = promodel.getAllProductId();
//        for (int i = 0; i < list.size(); i++) {
//             id = list.get(i);
//        }
//        return id;
//    }
//}

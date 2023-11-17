package lk.ijse.project.util;

import java.util.ArrayList;

public class NewId {
    public static String newId(ArrayList<String> list, GetType getType) {
        String lastId = null;
        for (int i = 0; i < list.size(); i++) {
            lastId = list.get(i);
        }

        switch (getType) {
            case CUSTOMER:
                try {
                    System.out.println(lastId+" last id");
                    String[] split = lastId.split("C-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    System.out.println(idNum +"  id num");
                    return "C-0" + idNum;
                } catch (Exception e) {
                    return "C-01";
                }

            case SUPPLIER:
                try {
                    String[] split = lastId.split("S-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "S-0" + idNum;
                } catch (Exception e) {
                    return "S-01";
                }

            case EMPLOYEE:
                try {
                    String[] split = lastId.split("E-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "E-0" + idNum;
                } catch (Exception e) {
                    return "E-01";
                }

            case PRODUCT:
                try {
                    String[] split = lastId.split("P-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "P-0" + idNum;
                } catch (Exception e) {
                    return "P-01";
                }
/*
            case RENT:
                try {
                    String[] split = lastId.split("R-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "R-0" + idNum;
                } catch (Exception e) {
                    return "R-01";
                }

            case TAX:
                try {
                    String[] split = lastId.split("T-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "T-0" + idNum;
                } catch (Exception e) {
                    return "T-01";
                }
            case BOAT:
                try {
                    String[] split = lastId.split("B-0");
                    int idNum = Integer.parseInt(split[1]);
                    idNum++;
                    return "B-0" + idNum;
                } catch (Exception e) {
                    return "B-01";
                }
*/
            default:
                return null;
        }


    }

    public enum GetType {
        EMPLOYEE, SUPPLIER, CUSTOMER, PRODUCT,MACHINE
    }
}

package lk.ijse.project.model;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project.dto.machineDto;
import lk.ijse.project.dto.tm.machineTm;
import lk.ijse.project.fp.FpConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class machineModel {

        public static boolean saveMachine(machineDto mdto) throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();
            String sql = "INSERT INTO machine VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, mdto.getM_id());
            pstm.setString(2, mdto.getM_name());
            pstm.setString(3, mdto.getM_task());
            pstm.setInt(4, mdto.getMachine_qty());
            pstm.setInt(5,mdto.getMachine_per_day_amount());

            return pstm.executeUpdate() > 0;
        }

        public static boolean updateMachine(machineDto mdto) throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();

            String sql = "UPDATE machine SET  m_name = ?, m_task = ?, machine_qty = ?, rent_fee = ? WHERE m_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);


            pstm.setString(1, mdto.getM_name());
            pstm.setString(2, mdto.getM_task());
            pstm.setInt(3, mdto.getMachine_qty());
            pstm.setInt(4,mdto.getMachine_per_day_amount());
            pstm.setString(5, mdto.getM_id());


            return pstm.executeUpdate() > 0;
        }

        public machineDto searchMachine(String m_id) throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();
            String sql = "SELECT * FROM machine WHERE m_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, m_id);

            ResultSet resultSet = pstm.executeQuery();

           machineDto dto = null;

            if (resultSet.next()) {
                dto = new machineDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                );
            }
            return dto;
        }

        public static boolean deleteMachine(String m_id) throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();

            String sql = "DELETE FROM machine WHERE m_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, m_id);

            return pstm.executeUpdate() > 0;
        }

        public List<machineDto> loadAllMachine() throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();

            String sql = "SELECT * FROM machine";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            List<machineDto> dtoList = new ArrayList<>();

            while (resultSet.next()) {
                var dto = new machineDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                );

                dtoList.add(dto);
            }

            return dtoList;
        }
    public static machineTm getMachine(String mId) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine WHERE m_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,mId);

        ResultSet resultSet = pstm.executeQuery();

       machineTm machtm = null;

        if(resultSet.next()) {
            machtm = new machineTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(5),
                    resultSet.getInt(4)
            );
        }
        return machtm;
    }
    public static machineDto getMachineDto(String mId) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine WHERE m_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,mId);

        ResultSet resultSet = pstm.executeQuery();

        machineDto mdto= null;

        if(resultSet.next()) {
            mdto = new machineDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5)
            );
        }
        return mdto;
    }
    public ArrayList<String> getAllMachineId() throws SQLException{
        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT m_id FROM machine ORDER BY LENGTH(m_id),m_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }


}


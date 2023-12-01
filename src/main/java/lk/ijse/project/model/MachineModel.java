package lk.ijse.project.model;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project.dto.MachineDto;
import lk.ijse.project.dto.tm.MachineTm;
import lk.ijse.project.DB.DBConnection;

public class MachineModel {

        public static boolean saveMachine(MachineDto mdto) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO machine VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, mdto.getM_id());
            pstm.setString(2, mdto.getM_name());
            pstm.setString(3, mdto.getM_task());
            pstm.setInt(4, mdto.getMachine_qty());
            pstm.setInt(5,mdto.getMachine_per_day_amount());

            return pstm.executeUpdate() > 0;
        }

        public static boolean updateMachine(MachineDto mdto) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "UPDATE machine SET  m_name = ?, m_task = ?, machine_qty = ?, rent_fee = ? WHERE m_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);


            pstm.setString(1, mdto.getM_name());
            pstm.setString(2, mdto.getM_task());
            pstm.setInt(3, mdto.getMachine_qty());
            pstm.setInt(4,mdto.getMachine_per_day_amount());
            pstm.setString(5, mdto.getM_id());


            return pstm.executeUpdate() > 0;
        }

    public static MachineDto getData(String mId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine WHERE m_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,mId);

        ResultSet resultSet = pstm.executeQuery();

       MachineDto dto = null;

        if(resultSet.next()) {
            dto = new MachineDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5)
            );
        }
        return dto;
    }

    public static MachineDto searchMachine(String m_id) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM machine WHERE m_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, m_id);

            ResultSet resultSet = pstm.executeQuery();

           MachineDto dto = null;

            if (resultSet.next()) {
                dto = new MachineDto(
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
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "DELETE FROM machine WHERE m_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, m_id);

            return pstm.executeUpdate() > 0;
        }

        public static List<MachineDto> loadAllMachine() throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();

            String sql = "SELECT * FROM machine";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            List<MachineDto> dtoList = new ArrayList<>();

            while (resultSet.next()) {
                var dto = new MachineDto(
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
    public static MachineTm getMachine(String mId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine WHERE m_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,mId);

        ResultSet resultSet = pstm.executeQuery();

       MachineTm machtm = null;

        if(resultSet.next()) {
            machtm = new MachineTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(5),
                    resultSet.getInt(4)
            );
        }
        return machtm;
    }
    public static MachineDto getMachineDto(String mId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine WHERE m_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,mId);

        ResultSet resultSet = pstm.executeQuery();

        MachineDto mdto= null;

        if(resultSet.next()) {
            mdto = new MachineDto(
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
        Connection connection = DBConnection.getInstance().getConnection();
        String sql="SELECT m_id FROM machine ORDER BY LENGTH(m_id),m_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static boolean update(ArrayList<String[]> arrayList) throws SQLException {
        String sql = "UPDATE machine SET machine_qty = machine_qty - ? WHERE m_id=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < arrayList.size() ; i++) {
            statement.setInt(1, Integer.parseInt(arrayList.get(i)[1]));
            statement.setString(2,arrayList.get(i)[0]);
            int value = statement.executeUpdate();

            if (value == 0) {
                return false;
            }
        }
        return true;
    }
    public  static int dashboardMachineCount() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(m_id) FROM machine";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet set = pstm.executeQuery();
        if (set.next()) {
            int count = set.getInt(1);
            return count;
        }
        return  0;
    }
}


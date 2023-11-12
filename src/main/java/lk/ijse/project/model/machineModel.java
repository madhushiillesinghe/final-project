package lk.ijse.project.model;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project.dto.machineDto;
import lk.ijse.project.fp.FpConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class machineModel {

        public static boolean saveMachine(machineDto mdto) throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();
            String sql = "INSERT INTO machine VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, mdto.getM_id());
            pstm.setString(2, mdto.getM_name());
            pstm.setString(3, mdto.getM_task());
            pstm.setInt(4, mdto.getMachine_qty());
            pstm.setString(5, mdto.getRent_id());
            pstm.setInt(6,mdto.getMachine_per_day_amount());

            return pstm.executeUpdate() > 0;
        }

        public boolean updateMachine(machineDto mdto) throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();

            String sql = "UPDATE machine SET  m_name = ?, m_task = ?, machine_qty = ?, rent_id = ?, machine_per_day_amount = ? WHERE p_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);


            pstm.setString(1, mdto.getM_name());
            pstm.setString(2, mdto.getM_task());
            pstm.setInt(3, mdto.getMachine_qty());
            pstm.setString(4, mdto.getRent_id());
            pstm.setInt(5,mdto.getMachine_per_day_amount());
            pstm.setString(6, mdto.getM_id());


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
                        resultSet.getString(5),
                        resultSet.getInt(6)
                );
            }
            return dto;
        }

        public boolean deleteMachine(String m_id) throws SQLException {
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
                        resultSet.getString(5),
                        resultSet.getInt(6)
                );

                dtoList.add(dto);
            }

            return dtoList;
        }

    }


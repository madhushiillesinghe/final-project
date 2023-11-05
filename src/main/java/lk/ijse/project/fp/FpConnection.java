package lk.ijse.project.fp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FpConnection {
    private static FpConnection fpConnection;
    private Connection connection;

    private FpConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project_agri_company",
                "root",
                "Ijse1234"
        );
    }

    public static FpConnection getInstance() throws SQLException {
        return (null == fpConnection) ? fpConnection = new FpConnection() : fpConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}

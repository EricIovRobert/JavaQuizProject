package quiz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/QuizP3";
    private static final String USER = "root";
    private static final String PASSWORD = "WovenCrib12@";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Nu s-a putut realiza conexiunea la baza de date.");
        }
    }

    public static void disconnect(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

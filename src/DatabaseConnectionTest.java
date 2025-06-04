import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        // Configurați URL-ul bazei de date, utilizatorul și parola
        String url = "jdbc:mysql://localhost:3306/ticketing_platform";
        String user = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Conexiunea cu baza de date a fost realizată cu succes!");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la conectarea cu baza de date: " + e.getMessage());
        }
    }
}
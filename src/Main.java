import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {

        try {
            // Initialize the database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketing_platform", "root", "root");
            DatabaseService databaseService = new DatabaseService(connection);

            // Call the updateEventDates function
            databaseService.updateEventDates();

            System.out.println("Event dates updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Service service = new Service();

        service.startMenu();
    }
}
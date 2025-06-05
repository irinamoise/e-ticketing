import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private Connection connection;

    public DatabaseService(Connection connection) {
        this.connection = connection;
    }

    public void updateEventDates() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Disable safe update mode
            stmt.execute("SET SQL_SAFE_UPDATES = 0");

            // Update all rows in the Event table
            String updateQuery = "UPDATE Event SET start_date = '2025-07-01', start_time = '20:00'";
            stmt.executeUpdate(updateQuery);

            // Re-enable safe update mode (optional)
            stmt.execute("SET SQL_SAFE_UPDATES = 1");
        }
    }

    public List<Event> listEvents() throws SQLException {
        String query = "SELECT id, name, description, tickets_nr, start_date, start_time,  basic_price FROM Event";
        List<Event> events = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        null, // Location can be fetched separately if needed
                        rs.getString("description"),
                        rs.getInt("tickets_nr"),
                        rs.getInt("basic_price")
                );
                event.setStartDate(rs.getDate("start_date").toLocalDate());
                event.setStartTime(rs.getTime("start_time").toLocalTime());
                events.add(event);
            }
        }

        return events;
    }

    public Event getEventWithArtists(int eventId) throws SQLException {
        String query = "SELECT e.id AS event_id, e.name AS event_name, e.description, e.tickets_nr, e.start_date, e.start_time, e.basic_price, " +
                "a.id AS artist_id, a.name AS artist_name, a.profession, a.description AS artist_description " +
                "FROM Event e " +
                "JOIN Event_Artist ea ON e.id = ea.event_id " +
                "JOIN Artist a ON ea.artist_id = a.id " +
                "WHERE e.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();

            Event event = null;
            List<Artist> artists = new ArrayList<>();

            while (rs.next()) {
                if (event == null) {
                    event = new Event(
                            rs.getInt("id"),
                            rs.getString("event_name"),
                            null, // Location can be fetched separately if needed
                            rs.getString("description"),
                            rs.getInt("tickets_nr"),
                            rs.getInt("basic_price")
                    );
                    event.setStartDate(rs.getDate("start_date").toLocalDate());
                    event.setStartTime(rs.getTime("start_time").toLocalTime());
                }

                Artist artist = new Artist(

                        rs.getString("artist_name"),
                        rs.getString("profession"),
                        rs.getString("artist_description")
                );
                artists.add(artist);
            }

            if (event != null) {
                event.setArtists(artists); // Assuming you add a setter for artists in Event
            }

            return event;
        }
    }

    public List<Location> listLocation() throws SQLException {
        String query = "SELECT id, name, address FROM Location";
        List<Location> locations = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Location location = new Location(
                        rs.getString("name"),
                        rs.getString("address")
                );
                locations.add(location);
            }
        }

        return locations;
    }

    public List<Artist> listArtists() throws SQLException {
        String query = "SELECT id, name, profession, description FROM Artist";
        List<Artist> artists = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("name"),
                        rs.getString("profession"),
                        rs.getString("description")
                );
                artists.add(artist);
            }
        }

        return artists;
    }

    public List<User> listUsers() throws SQLException {
        String query = "SELECT id, username, first_name, last_name, email, password FROM User";
        List<User> users = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getString("email"),
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("password"),
                        rs.getString("username")
                );
                users.add(user);
            }
        }

        return users;
    }

    public List<Ticket> listTicketsByUsername(String username) throws SQLException {
        String query = "SELECT t.id, t.seat, e.name AS event_name, e.start_date, e.start_time " +
                "FROM Ticket t " +
                "JOIN Event e ON t.event_id = e.id " +
                "JOIN User u ON t.user_id = u.id " +
                "WHERE u.username = ?";
        List<Ticket> tickets = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket(
                        username,
                        rs.getInt("id"),
                        new Event(
                                rs.getInt("id"),
                                rs.getString("event_name"),
                                null, // Location can be fetched separately if needed
                                null, // Description not needed here
                                0,    // Tickets number not needed here
                                0     // Basic price not needed here
                        ),
                        rs.getString("seat")
                );
                ticket.getEventDetails().setStartDate(rs.getDate("start_date").toLocalDate());
                ticket.getEventDetails().setStartTime(rs.getTime("start_time").toLocalTime());
                tickets.add(ticket);
            }
        }

        return tickets;
    }


    // Insert a new user
    public void insertUser(String username, String firstName, String lastName, String email, String password) throws SQLException {
        String query = "INSERT INTO User (username, first_name, last_name, email, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.executeUpdate();
        }
    }

    public void insertTicket(int userId, int eventId, String seat) throws SQLException {
        String query = "INSERT INTO Ticket (user_id, event_id, seat) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setString(3, seat);
            stmt.executeUpdate();
        }
    }

    // Update user information
    public void updateUser(int userId, String newEmail) throws SQLException {
        String query = "UPDATE User SET email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newEmail);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

    // Delete a user
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    public boolean deleteUserAndTickets(String username) {
        String deleteTicketsQuery = "DELETE FROM Ticket WHERE user_id = (SELECT id FROM User WHERE username = ?)";
        String deleteUserQuery = "DELETE FROM User WHERE username = ?";

        try {
            connection.setAutoCommit(false); // Start transaction

            // Delete tickets
            try (PreparedStatement deleteTicketsStmt = connection.prepareStatement(deleteTicketsQuery)) {
                deleteTicketsStmt.setString(1, username);
                deleteTicketsStmt.executeUpdate();
            }

            // Delete user
            try (PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserQuery)) {
                deleteUserStmt.setString(1, username);
                int rowsAffected = deleteUserStmt.executeUpdate();

                if (rowsAffected == 0) {
                    connection.rollback(); // Rollback if user not found
                    return false; // User not found
                }
            }

            connection.commit(); // Commit transaction
            return true; // Deletion successful
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback on error
            } catch (SQLException rollbackEx) {
                System.err.println("Rollback failed: " + rollbackEx.getMessage());
            }
            System.err.println("Error deleting user and tickets: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true); // Restore auto-commit
            } catch (SQLException ex) {
                System.err.println("Failed to restore auto-commit: " + ex.getMessage());
            }
        }
    }


}
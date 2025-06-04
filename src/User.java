import java.util.ArrayList;

public class User {
    private static int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int nrTicketsPurchased;
    private ArrayList<Ticket>tickets;

    public User(String email, String firstName, String lastName, String password, String username) {
        this.email = email;
        this.firstName = firstName;
        id ++;
        this.lastName = lastName;
        this.password = password;
        this.tickets = new ArrayList<>();
        this.nrTicketsPurchased =0;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }
    public int getNrTicketsPurchased(){
        return nrTicketsPurchased;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return
                "username: " + username + '\n' +
                "name: " + firstName + ' ' + lastName + '\n' +
                "email: " + email + '\n' +
                "tickets: " +'\n' + tickets.toString() + '\n' +
                        "----------------------------"
                ;
    }
}

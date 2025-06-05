import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int nrTicketsPurchased;
    private ArrayList<Ticket>tickets;

    public User(String email, int id, String firstName, String lastName, String password, String username) {
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.password = password;
        this.tickets = new ArrayList<>();
        this.nrTicketsPurchased =0;
        this.username = username;
    }

    public User(User obj){
        this.email = obj.email;
        this.firstName = obj.firstName;
        this.id = obj.id;
        this.lastName = obj.lastName;
        this.password = obj.password;
        this.tickets = new ArrayList<>();
        for (Ticket ticket : obj.tickets) {
            this.tickets.add(new Ticket(ticket)); // Constructor de copiere al clasei Ticket
        }
        this.nrTicketsPurchased =obj.nrTicketsPurchased;
        this.username = obj.username;
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

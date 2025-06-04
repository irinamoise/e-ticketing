import java.util.*;

public class Service {
    private ArrayList<Event> events;
    private ArrayList<Location> locations;
    private List<Artist> artists;
    private HashMap<String,User> users = new HashMap<>();

    public Service() {

        this.locations = DataInitializer.initializeLocations();
        this.artists = DataInitializer.initializeArtists();
        this.events = DataInitializer.initializeEvents();
        this.users = DataInitializer.createUsers();
    }


    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Meniu ---");
            System.out.println("1. Listare evenimente");
            System.out.println("2. Listare locatii");
            System.out.println("3. Listare artisti");
            System.out.println("4. Log In");
            System.out.println("5. Creare cont de utilizator");
            System.out.println("6. Listare bilete pentru un user");
            System.out.println("7. Cumparare bilet");
            System.out.println("8. Vezi cele mai ieftine evenimente");
            System.out.println("9. Listare useri");
            System.out.println("0. Iesire");
            System.out.print("Introduceti optiunea dvs: ");


            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> listEvents();
                case 2 -> listLocations();
                case 3 -> listArtists();
                case 4 -> logIn(scanner);
                case 5 -> createNewUser(scanner);
                case 6 -> listUserTickets(scanner);
                case 7 -> purchaseTicket(scanner);
                case 8 -> sortEventsByPrice();
                case 9 -> listUsers();
                case 0 -> {
                    System.out.println("Se iese din meniu. La revedere!");
                    running = false;
                }
                default -> System.out.println("Optiune invalida. Incercati din nou.");
            }
        }
        scanner.close();
    }

    private void listEvents() {
        System.out.println("\n--- Evenimente ---");
        for (Event e : events) {
            System.out.println(e.toString());
            System.out.println();
            System.out.println();
        }
    }

    private void listLocations() {
        System.out.println("\n--- Locatii ---");
        for (Location l : locations) {
            System.out.println(l.toString());
        }
    }

    private void listArtists(){
        System.out.println("\n--- Artisti---");
        for (Artist a : artists) {
            System.out.println(a.toString());
        }
    }


    private void listUsers(){
        System.out.println("\n--- Useri---");
        for (User u : users.values()) {
            System.out.println(u.toString());
        }
    }


    private void logIn(Scanner scanner) {
        System.out.print("Introduceti username: ");
        String username = scanner.nextLine();

        User user = users.get(username); //
        if (user != null) {
            System.out.print("Introduceti parola: ");
            String password = scanner.nextLine();
            if (user.getPassword().equals(password)) {
                System.out.println("Login realizat cu succes! Bine ai venit, " + user.getFirstName() + "!");
                return;
            } else{
                System.out.println(" Parola invalida!");
            }
        } else {
            System.out.println("User negasit.");
        }


    }

    private void createNewUser(Scanner scanner) {
        System.out.print("Introdu email-ul: ");
        String email = scanner.nextLine();
        System.out.print("Introdu prenumele: ");
        String firstName = scanner.nextLine();
        System.out.print("Introdu numele de familie: ");
        String lastName = scanner.nextLine();
        System.out.print("Introdu username: ");
        String username = scanner.nextLine();
        System.out.print("Introdu parola: ");
        String password = scanner.nextLine();

        User newUser = new User(email, firstName,  lastName, password, username);
        users.put(newUser.getUsername(), newUser);
        System.out.println("Cont creat cu succes!");
    }

    private void listUserTickets(Scanner scanner) {
        System.out.print("Introdu username: ");
        String username = scanner.nextLine();
        User user = users.get(username);

        if (user != null) {
            System.out.println("\n--- Biletele tale  ---");
            for (Ticket ticket : user.getTickets()) {
                System.out.println(ticket);
            }

        }else {
            System.out.println("Utilizatorul nu a fost gasit.");
        }
    }

    private void sortEventsByPrice() {
        Collections.sort(events);
        System.out.println("\n--- Evenimente la super preturi ---");
        for (Event e : events) {
            System.out.println(e.toString());
        }
    }

    private void purchaseTicket(Scanner scanner) {
        System.out.print("Introdu username: ");
        String username = scanner.nextLine();

        User user = null;
        for (User u : users.values()) { // Fixed iteration
            if (u.getUsername().equals(username)) {
                user = u;
                break;
            }
        }

        if (user == null) {
            System.out.println("Utilizatorul nu a fost gasit.");
            return;
        }

        System.out.println("\n--- Evenimente ---");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i));
        }
        System.out.print("Selecteaza numarul unui eveniment: ");
        int eventIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (eventIndex < 0 || eventIndex >= events.size()) {
            System.out.println("Selectie invalida.");
            return;
        }

        String userFullName = user.getFirstName() + user.getLastName();

        Event selectedEvent = events.get(eventIndex);
        int presentTickets = selectedEvent.getTicketsNr();
        if (presentTickets > 0) {
            Ticket newTicket = new Ticket(userFullName, selectedEvent, "30A");
            user.getTickets().add(newTicket);
            selectedEvent.setTicketsNr(presentTickets - 1);
            System.out.println("Bilet achizitionat cu succes pentru evenimentul: " + selectedEvent.getName());
        } else {
            System.out.println("Nu mai exista bilete disponibile pentru acest eveniment");
        }
    }
}
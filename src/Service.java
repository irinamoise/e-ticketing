import java.util.*;
import java.sql.SQLException;

public class Service {

    private DatabaseService databaseService; // Add DatabaseService

    public Service() {

        // Initialize DatabaseService
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        this.databaseService = new DatabaseService(dbConnection.getConnection());

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
            System.out.println("10. Sterge-ti contul");
            System.out.println("0. Iesire");
            System.out.print("Introduceti optiunea dvs: ");

            try {
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
                    case 10 -> deleteUserAndTickets(scanner);
                    case 0 -> {
                        System.out.println("Se iese din meniu. La revedere!");
                        running = false;
                    }
                    default -> System.out.println("Optiune invalida. Incercati din nou.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Optiune invalida. Introduceti un numar.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close();
    }

    private final CsvLoggerService csvLoggerService = new CsvLoggerService();

    private void listEventWithArtists(Scanner scanner) {
        System.out.print("Introdu ID-ul evenimentului: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Event event = databaseService.getEventWithArtists(eventId);
            if (event != null) {
                System.out.println("\n--- Eveniment cu artisti ---");
                System.out.println(event);
                System.out.println("Artisti asociati:");
                for (Artist artist : event.getArtists()) {
                    System.out.println(artist);
                }
            } else {
                System.out.println("Evenimentul nu a fost gasit.");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }

    private void listEvents() {
        System.out.println("\n--- Evenimente din baza de date ---");
        try {
            List<Event> events = databaseService.listEvents();
            for (Event event : events) {
                System.out.println(event);
            }
            csvLoggerService.logAction("listEvents");
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }


    private void listLocations() {
        System.out.println("\n--- Locatii din baza de date ---");
        try {
            List<Location> locations = databaseService.listLocation();
            for (Location location : locations) {
                System.out.println(location);
            }
            csvLoggerService.logAction("citire locatii");
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }

    private void listArtists() {
        System.out.println("\n--- Artisti din baza de date ---");
        try {
            List<Artist> artists = databaseService.listArtists();
            for (Artist artist : artists) {
                System.out.println(artist);

            }
            csvLoggerService.logAction("citire artisti");
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }


    private void listUsers() {
        System.out.println("\n--- Useri din baza de date ---");
        try {
            List<User> users = databaseService.listUsers();
            for (User user : users) {
                System.out.println(user);
            }
            csvLoggerService.logAction("citire useri");
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }


    private void logIn(Scanner scanner) {
        System.out.print("Introduceti username: ");
        String username = scanner.nextLine();

        try {
            List<User> users = databaseService.listUsers();
            User user = users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);

            if (user != null) {
                System.out.print("Introduceti parola: ");
                String password = scanner.nextLine();
                if (user.getPassword().equals(password)) {
                    System.out.println("Login realizat cu succes! Bine ai venit, " + user.getFirstName() + "!");
                    csvLoggerService.logAction("citire useri");
                } else {
                    System.out.println("Parola invalida!");
                }
            } else {
                System.out.println("User negasit.");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
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

        try {

            databaseService.insertUser(username, firstName, lastName, email, password);
            System.out.println("Cont creat cu succes!");
            csvLoggerService.logAction("inserare user");
        } catch (SQLException e) {
            System.out.println("Eroare la inserarea utilizatorului in baza de date: " + e.getMessage());
        }
    }

    private void listUserTickets(Scanner scanner) {
        System.out.print("Introdu username: ");
        String username = scanner.nextLine();

        try {
            List<Ticket> tickets = databaseService.listTicketsByUsername(username);
            if (tickets.isEmpty()) {
                System.out.println("Utilizatorul nu are bilete.");
            } else {
                System.out.println("\n--- Biletele utilizatorului ---");
                for (Ticket ticket : tickets) {
                    System.out.println(ticket);
                    csvLoggerService.logAction("citire bilete");
                }
            }
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }

    private void sortEventsByPrice() {
        System.out.println("\n--- Evenimente la super preturi ---");
        try {
            List<Event> events = databaseService.listEvents();
            events.sort(Comparator.comparingInt(Event::getBasicPrice)); // Sort by basic price
            for (Event event : events) {
                System.out.println(event);

            }
            csvLoggerService.logAction("citire Events");
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }

    private void purchaseTicket(Scanner scanner) {
        System.out.print("Introdu username: ");
        String username = scanner.nextLine();

        try {
            List<User> users = databaseService.listUsers();
            User user = users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);


            if (user == null) {
                System.out.println("Utilizatorul nu a fost gasit.");
                return;
            }

            csvLoggerService.logAction("citire user");


            List<Event> events = databaseService.listEvents();
            csvLoggerService.logAction("citire Events");
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

            Event selectedEvent = events.get(eventIndex);
            if (selectedEvent.getTicketsNr() > 0) {
                System.out.print("Introdu locul (ex: 30A): ");
                String seat = scanner.nextLine();

                databaseService.insertTicket(user.getId(), selectedEvent.getEventId(), seat);
                selectedEvent.setTicketsNr(selectedEvent.getTicketsNr() - 1);
                System.out.println("Bilet achizitionat cu succes pentru evenimentul: " + selectedEvent.getName());
                csvLoggerService.logAction("inserare ticket");
            } else {
                System.out.println("Nu mai exista bilete disponibile pentru acest eveniment.");
            }
        } catch (SQLException e) {
            System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
        }
    }

    private void deleteUserAndTickets(Scanner scanner) {
        System.out.print("Introdu username-ul utilizatorului de sters: ");
        String username = scanner.nextLine();

        try {
            boolean success = databaseService.deleteUserAndTickets(username);
            if (success) {
                System.out.println("Utilizatorul si biletele sale au fost sterse cu succes!");
                csvLoggerService.logAction("delete user and tickets");
            } else {
                System.out.println("Utilizatorul nu a fost gasit sau stergerea a esuat.");
            }
        } catch (Exception e) {
            System.out.println("Eroare la stergerea utilizatorului si a biletelor din baza de date: " + e.getMessage());
        }
    }

}
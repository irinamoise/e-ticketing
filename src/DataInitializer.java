import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInitializer {

    public static ArrayList<Artist> initializeArtists() {
        ArrayList<Artist> artists = new ArrayList<>();

        artists.add(new Artist("Horia Brenciu", "cantaret pop-rock" , "Un artist versatil din România, cunoscut atât pentru muzica pop-rock, cât și pentru participarea la emisiuni TV."));
        artists.add(new Artist("Slash", "chitarist", "Chitarist legendar, cunoscut pentru activitatea sa cu Guns N' Roses și stilul său distinctiv de rock."));
        artists.add(new Artist("Nickelback", "trupa rock", "Trupa canadiană de rock/post-grunge, recunoscută pentru hituri precum <<How You Remind Me>>."));
        artists.add(new Artist("Alex Vlea", "cantaret pop", "Cântăreț și compozitor român, asociat cu genurile pop și latino."  ));
        artists.add(new Artist("Rihanna", "pop star international", "Superstarul internațional de muzică pop și R&B, cu multiple hituri și o marcă de beauty de succes."));
        artists.add(new Artist("Taraful Clejanilor", "formatie muzicala", "Trupa românească de manele, populară pentru piese energice și ritmuri orientale."));
        artists.add(new Artist("Alejandro Sanz", "cantaret latino", "Cântăreț și compozitor spaniol de muzică latino, cu multiple premii Grammy Latin."));
        artists.add(new Artist("Holograf", "trupa rock", "Legendara formație românească de rock, activă din anii '80."));
        artists.add(new Artist("B.U.G. Mafia", "trupa rap", "Grup influent de hip-hop românesc, pionier al scenei din anii '90."));
        artists.add(new Artist("Inna", "pop star", "Cântăreață română de muzică electronică, cu succes internațional."));

        artists.add(new Artist("Marcel Iureș", "actor"));
        artists.add(new Artist("Victor Rebengiuc", "actor"));
        artists.add(new Artist("Maia Morgenstern", "actrita"));
        artists.add(new Artist("Florin Piersic", "actor"));
        artists.add(new Artist("Oana Pellea", "actrita"));

        artists.add(new Artist("Rafael", "pictor"));
        artists.add(new Artist("Caravaggio", "pictor"));
        artists.add(new Artist("Theodor Pallady", "pictor"));

        return artists;
    }

    public static ArrayList<Location> initializeLocations(){
        ArrayList<Location> locations = new ArrayList<>();

        locations.add(new Location("Beraria H", "restaurant-berarie", "Populară pentru atmosfera vibrantă și mâncăruri tradiționale românești.", "Soseaua Kiseleff 32, Parcul Herastrau, București"));
        locations.add(new Location("Hard Rock Cafe", "restaurant-bar tematic", "Rețea internațională cu decor muzical și burgeri renumiți.", "Soseaua Kiseleff 32, Parcul Herastrau, București"));
        locations.add(new Location("Arena Națională", "stadion multifuncțional", "Gazdă la meciuri de fotbal, concerte și evenimente mari.", " Bulevardul Basarabia 37-39, București."));
        locations.add(new Location("Muzeul Colecțiilor de Artă", "Muzeu de artă", "Expune colecții private de artă românească și europeană", "Calea Victoriei 111, București"));
        locations.add(new Location("Art Safari", "galerie de arta", "Eveniment anual cu opere de artă românească și internațională.", "Strada Lipscani 18-20, Bucuresti"));
        locations.add(new Location("Teatrul de Comedie", "teatru", "Specializat în piese de comedie și spectacole satirice.", "Strada Sf. Dumitru 2, Bucuresti"));
        locations.add(new Location("Teatrul Național <<Ion Luca Caragiale>>", "teatru", "Unul dintre cele mai importante teatre din România.", "Bulevardul Nicolae Bălcescu 2, București."));

        return locations;

    }

    public static ArrayList<Event> initializeEvents(){
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Artist> artists = initializeArtists();
        ArrayList<Location> locations = initializeLocations();

        events.add(new Concert("Horia Brenciu Live", locations.get(0), "Dupa succesul revelionului la Sala Palatului, Horia Brenciu se intoarce cu un nou show memorabil.", 330 ,  150, new ArrayList<>(List.of(artists.get(0))), "Pop-Rock"));
        events.add(new Concert("Slash Rock Night", locations.get(1), "An electrifying rock concert featuring Slash.", 400 , 350, new ArrayList<>(List.of(artists.get(1))), "Hard Rock"));
        events.add(new Concert("Nickelback World Tour", locations.get(2), "A thrilling rock concert by Nickelback.", 60000 , 470,  new ArrayList<>(List.of(artists.get(2))),  "Divorced Dad Rock"));
        events.add(new Concert("Cel mai mare show al anului", locations.get(0), "Cei mai iubiti artisti romani vin la Beraria H pentru a va oferi o seara de neuitat", 400 , 200,  new ArrayList<>(List.of(artists.get(9), artists.get(3), artists.get(7), artists.get(8))), "muzica romaneasca"));

        events.add(new StagePlay("O scrisoare pierdută", locations.get(5), "O comedie clasică românească de Ion Luca Caragiale.", 200, 85, new ArrayList<>(List.of(
                artists.get(10), artists.get(11), artists.get(12), artists.get(13), artists.get(14)))));


        ArtExposition rafaelCaravaggio = new ArtExposition("Rafael și Caravaggio", locations.get(3), "O expoziție dedicată marilor maeștri ai Renașterii.", 150, 100, new ArrayList<>(List.of(artists.get(15), artists.get(16))));
        ArtExposition pallady = new ArtExposition("Theodor Pallady - Retrospectivă", locations.get(4), "O expoziție dedicată operei lui Theodor Pallady.", 100, 80, new ArrayList<>(List.of(artists.get(17))));


        HashMap<String, ArrayList<LocalTime>> schedule1 = new HashMap<>();
        schedule1.put("Luni", new ArrayList<>(List.of(LocalTime.of(10, 0), LocalTime.of(14, 0))));
        schedule1.put("Marti", new ArrayList<>(List.of(LocalTime.of(12, 0), LocalTime.of(16, 0))));
        schedule1.put("Miercuri", new ArrayList<>(List.of(LocalTime.of(12, 0), LocalTime.of(16, 0))));
        schedule1.put("Vineri", new ArrayList<>(List.of(LocalTime.of(10, 0), LocalTime.of(18, 0))));
        schedule1.put("Sambata", new ArrayList<>(List.of(LocalTime.of(10, 0), LocalTime.of(21, 0))));
        schedule1.put("Duminica", new ArrayList<>(List.of(LocalTime.of(10, 0), LocalTime.of(21, 0))));
        rafaelCaravaggio.setWeeklySchedule(schedule1);

        HashMap<String, ArrayList<LocalTime>> schedule2 = new HashMap<>();
        schedule2.put("Luni", new ArrayList<>(List.of(LocalTime.of(16, 0), LocalTime.of(19, 0))));
        schedule2.put("Marti", new ArrayList<>(List.of(LocalTime.of(11, 0), LocalTime.of(15, 0))));
        schedule2.put("Miercuri", new ArrayList<>(List.of(LocalTime.of(11, 0), LocalTime.of(15, 0))));
        schedule2.put("Joi", new ArrayList<>(List.of(LocalTime.of(13, 0), LocalTime.of(17, 0))));
        schedule1.put("Vineri", new ArrayList<>(List.of(LocalTime.of(10, 0), LocalTime.of(18, 0))));
        schedule2.put("Sambata", new ArrayList<>(List.of(LocalTime.of(10, 0), LocalTime.of(14, 0))));
        schedule1.put("Duminica", new ArrayList<>(List.of(LocalTime.of(10, 0), LocalTime.of(21, 0))));
        pallady.setWeeklySchedule(schedule2);

        events.add(rafaelCaravaggio);
        events.add(pallady);
        return events;

    }
    public static HashMap<String, User> createUsers() {
        HashMap<String, User> users = new HashMap<>();
        ArrayList<Event> events = initializeEvents();

        User user1 = new User("john.doe@example.com", "John", "Doe", "password123", "john_doe");
        User user2 = new User("jane.smith@example.com", "Jane", "Smith", "securepass", "jane_smith");
        User user3 = new User("alex.brown@example.com", "Alex", "Brown", "passw0rd", "alex_brown");
        User user4 = new User("emma.white@example.com", "Emma", "White", "mypassword", "emma_white");
        User user5 = new User("mike.jones@example.com", "Mike", "Jones", "admin123", "mike_jones");

        user1.getTickets().add(new Ticket("John Doe", events.get(0), "A1"));
        user1.getTickets().add(new Ticket("John Doe", events.get(1), "B2"));

        user2.getTickets().add(new Ticket("Jane Smith", events.get(2), "C3"));
        user2.getTickets().add(new Ticket("Jane Smith", events.get(3), "D4"));

        user3.getTickets().add(new Ticket("Alex Brown", events.get(0), "E5"));
        user3.getTickets().add(new Ticket("Alex Brown", events.get(2), "F6"));

        user4.getTickets().add(new Ticket("Emma White", events.get(1), "G7"));
        user4.getTickets().add(new Ticket("Emma White", events.get(3), "H8"));

        user5.getTickets().add(new Ticket("Mike Jones", events.get(0), "I9"));
        user5.getTickets().add(new Ticket("Mike Jones", events.get(1), "J10"));

        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
        users.put(user3.getUsername(), user3);
        users.put(user4.getUsername(), user4);
        users.put(user5.getUsername(), user5);


        return users;
    }


}

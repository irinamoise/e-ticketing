import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Concert extends Event{
    private ArrayList<Artist> artists;
    private String genre;
    private LocalTime duration;

    public Concert(String name, Location location, String description, Integer ticketsNr, Integer basicPrice, ArrayList<Artist> artists, String genre) {
        super(name, location, description, ticketsNr, basicPrice );
        this.artists = artists;
        this.genre = genre;
        this.duration = LocalTime.of(2, 0);
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public ArrayList<String> getArtistsName(){
        ArrayList<String>artistsNames = new ArrayList<>();

        for (Artist a : this.artists)
        {
            artistsNames.add(a.getName());
        }
        return artistsNames;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return
                "nume: " + name + '\n' +
                "locatie: " + location.getName() + '\n' +
                "artisti: " + this.getArtistsName() + '\n' + "pret de inceput: " + this.getBasicPrice() + '\n' +
                "gen: " + genre +  '\n' +
                "descriere: " + description + '\n' +
                "numar bilete: " + ticketsNr + '\n' +
                "data: " + startDate +
                ", ora: " + startTime +
                ", durata: " + duration + '\n' +
                "etichete: " + tags + '\n' +
                "------------------------------------------";
    }
}
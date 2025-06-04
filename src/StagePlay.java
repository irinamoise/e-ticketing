import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class StagePlay extends Event{

    //de implementat String salaSpectacol

    private ArrayList<Artist> cast;
    private Artist director;
    private LocalTime duration;

    public StagePlay(String name, Location location, String description, Integer ticketsNr, Integer basicPrice, ArrayList<Artist> cast) {
        super(name, location, description, ticketsNr, basicPrice);
        this.director = null;
        this.cast = cast;
        this.director = director;
        this.duration = LocalTime.of(1, 30);
    }

    public ArrayList<Artist> getCast() {
        return cast;
    }

    public Artist getDirector() {
        return director;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public ArrayList<String> getArtistsName(){
        ArrayList<String>artistsNames = new ArrayList<>();

        for (Artist a : this.cast)
        {
            artistsNames.add(a.getName());
        }
        return artistsNames;
    }

    @Override
    public String toString() {
        return
                "nume: " + name + '\n' +
                        "locatie: " + location.getName() + '\n' +
                        "actori: " + this.getArtistsName() + '\n' + "pret de inceput: " + this.getBasicPrice() + '\n' +
                        "descriere: " + description + '\n' +
                        "numar bilete: " + ticketsNr + '\n' +
                        "data: " + startDate +
                        ", ora: " + startTime +
                        ", durata: " + duration + '\n' +
                        "etichete: " + tags + '\n' +
                        "------------------------------------------";
    }

}

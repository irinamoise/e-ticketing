import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Event implements Comparable<Event>{

    protected int eventId;
    protected String name;
    protected Location location;
    protected Integer ticketsNr; //setez atributul ca Integer ca sa fie nullable
    protected String description;
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected ArrayList<String> tags;
    protected Integer basicPrice;
    private List<Artist> artists;

    public Event() {

        this.artists = new ArrayList<>(); // Initialize the list
    }


    public Event(int id, String name, Location location, String description, Integer ticketsNr, Integer basicPrice) {
        this.eventId = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.ticketsNr = ticketsNr;
        this.startDate =LocalDate.of(2025, 6, 1);
        this.startTime = LocalTime.of(19, 30);
        this.tags = new ArrayList<>(List.of("super", "cool"));
        this.basicPrice = basicPrice;
        this.artists = new ArrayList<>(); // Initialize the list
    }

    public int getEventId() {
        return eventId;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Integer getTicketsNr() {
        return ticketsNr;
    }

    public Integer getBasicPrice(){
        return basicPrice;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setTicketsNr(int ticketsNr){
        this.ticketsNr = ticketsNr;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<String> getArtistsName(){
        ArrayList<String>artistsNames = new ArrayList<>();

        for (Artist a : this.artists)
        {
            artistsNames.add(a.getName());
        }
        return artistsNames;
    }

    @Override
    public String toString() {
        return
                "nume: " + name + '\n' +

                        "artisti: " + this.getArtistsName() + '\n' + "pret de inceput: " + this.getBasicPrice() + '\n' +

                        "descriere: " + description + '\n' +
                        "numar bilete: " + ticketsNr + '\n' +
                        "data: " + startDate +
                        ", ora: " + startTime +'\n' +
                        "------------------------------------------";
    }

    @Override
    public int compareTo(Event other) {
        return this.basicPrice.compareTo(other.basicPrice);
    }
}




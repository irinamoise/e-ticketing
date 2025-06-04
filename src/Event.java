import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Event implements Comparable<Event>{

    protected static int eventId=0;
    protected String name;
    protected Location location;
    protected Integer ticketsNr; //setez atributul ca Integer ca sa fie nullable
    protected String description;
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected ArrayList<String> tags;
    protected Integer basicPrice;

    public Event() {

    }


    public Event(String name, Location location, String description, Integer ticketsNr, Integer basicPrice) {
        eventId ++;
        this.name = name;
        this.location = location;
        this.description = description;
        this.ticketsNr = ticketsNr;
        this.startDate =LocalDate.of(2025, 6, 1);
        this.startTime = LocalTime.of(19, 30);
        this.tags = new ArrayList<>(List.of("super", "cool"));
        this.basicPrice = basicPrice;

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

    @Override
    public int compareTo(Event other) {
        return this.basicPrice.compareTo(other.basicPrice);
    }
}




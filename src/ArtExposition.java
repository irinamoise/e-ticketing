import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtExposition extends Event{
    private String theme;
    private ArrayList<Artist> artists;
    private String curator;
    private LocalDate endDate;
    private HashMap<String, ArrayList<LocalTime>> weeklySchedule;

    public ArtExposition(String name, Location location, String description, Integer ticketsNr, Integer basicPrice, ArrayList<Artist> artists) {
        super(name, location, description, ticketsNr, basicPrice);
        this.artists = artists;
        this.curator = "-";
        this.endDate = LocalDate.of(2026, 1, 1);
        this.theme = "-";
        this.weeklySchedule = null;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public String getCurator() {
        return curator;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTheme() {
        return theme;
    }

    public HashMap<String, ArrayList<LocalTime>> getWeeklySchedule() {
        return weeklySchedule;
    }

    public ArrayList<String> getArtistsName(){
        ArrayList<String>artistsNames = new ArrayList<>();

        for (Artist a : this.artists)
        {
            artistsNames.add(a.getName());
        }
        return artistsNames;
    }

    public void setWeeklySchedule(HashMap<String, ArrayList<LocalTime>> weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    private String formatWeeklySchedule() {
        if (weeklySchedule == null || weeklySchedule.isEmpty()) {
            return "Orar indisponibil.";
        }

        StringBuilder scheduleString = new StringBuilder();
        for (String day : weeklySchedule.keySet()) {
            scheduleString.append(day).append(": ");
            ArrayList<LocalTime> times = weeklySchedule.get(day);
            for (LocalTime time : times) {
                scheduleString.append(time.toString()).append(" ");
            }
            scheduleString.append("\n");
        }
        return scheduleString.toString().trim();
    }


    @Override
    public String toString() {
        return

                "nume: " + name + '\n' +
                "tematica: " + theme + '\n' +
                "locatie: " + location.getName() + '\n' +
                "artisti: " + this.getArtistsName() + '\n' + "pret de inceput: " + this.getBasicPrice() + '\n' +
                "descriere: " + description + '\n' +
                "numar bilete: " + ticketsNr + '\n' +
                "data deschidere: " + startDate +
                ", ora deschidere: " + startTime +  '\n' +
                        "orar saptamanal: \n" + formatWeeklySchedule() + '\n' +
                "etichete: " + tags + '\n' +
                "------------------------------------------";

    }
}


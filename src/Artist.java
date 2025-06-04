public class Artist {
    private static int artistId;
    private String name;
    private String profession;
    private String description;

    public Artist(String name, String profession, String description) {
        artistId++;
        this.description = description;
        this.name = name;
        this.profession = profession;
    }

    public Artist(String name, String profession){
        artistId++;
        this.description = "-";
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProfession() {
        return profession;
    }

    public static int getArtistId() {
        return artistId;
    }

    @Override
    public String toString() {
        return
                "nume: " + name + " - " +
                profession + '\n' +
                "descriere: " + description + '\n';
    }
}



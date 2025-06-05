public class Location {
    private static int locationId;
    private String name;
    private String type; //ex:sala de spectacol, stadion, restaurant, muzeu, galerie
    private String address;
    private String description;

    public Location() {
        locationId++;
        name= "-";
        type = "-";
        description = "-";
        address = "-";
    }

    public Location(String name, String type, String description, String address) {

        this.name = name;
        this.type = type;
        this.address = address;
        this.description = description;
    }

    public Location(String name, String address) {
        this.name = name;

        this.address = address;
    }

    public String getName() {
        return name;
    }

    public static int getLocationId() {
        return locationId;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return
                "nume: " + name  + '\n' +
                "tip: " + type + '\n' +
                "descriere: " + description + '\n' +
                "adresa: " + address + '\n';
    }
}

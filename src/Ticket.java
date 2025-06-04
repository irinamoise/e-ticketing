

public class Ticket {
    protected static int ticketId = 0;
    protected Event eventDetails;
    protected String costumerName;
    protected String seat;
    protected int price;

    public Ticket(String costumerName, Event eventDetails, String seat) {
        ticketId++;
        this.costumerName = costumerName;
        this.eventDetails = eventDetails;
        this.price = eventDetails.getBasicPrice();
        this.seat = seat;

    }

    public static int getTicketId() {
        return ticketId;
    }

    public Event getEventDetails() {
        return eventDetails;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public String getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Bilet {" + '\n' +
                "    eveniment: " + eventDetails.getName() + '\n' +
                "    loc='" + seat + '\n' +
                "    pret=" + price  + " }" + '\n';
    }


}




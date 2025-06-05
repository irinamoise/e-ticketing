

public class Ticket {
    protected  int ticketId;
    protected Event eventDetails;
    protected String costumerName;
    protected String seat;
    protected int price;

    public Ticket() {

    }

    public Ticket(String costumerName, int ticketIdid, Event eventDetails, String seat) {

        this.costumerName = costumerName;
        this.eventDetails = eventDetails;
        this.price = eventDetails.getBasicPrice();
        this.seat = seat;
        this.ticketId = ticketIdid;

    }

    public Ticket(Ticket original) {
        this.costumerName = original.costumerName;
        this.eventDetails = original.eventDetails; // Assuming Event is immutable or a shallow copy is acceptable
        this.seat = original.seat;
        this.price = original.price;
        this.ticketId = original.ticketId;
    }

    public int getTicketId() {
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




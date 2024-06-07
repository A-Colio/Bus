public class Trip {
    private Bus bus;
    private int reservedSeats;
    private String date;
    private String depart;
    private String arrival;

    public Trip(Bus bus, int reservedSeats, String date, String depart, String arrival) {
        this.bus = bus;
        this.reservedSeats = reservedSeats;
        this.date = date;
        this.depart = depart;
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "# " + date + "\n-- " + depart + " → " + arrival + "\n---- " + bus.toString() + " " + reservedSeats + "석 예매";
    }

    public String getFormattedTrip() {
        return String.format("# %s\n-- %s → %s\n---- %s %d석 예매 (남은 좌석 %d석)",
                date, depart, arrival, bus.toString(), reservedSeats, bus.getRemainSeats());
    }

    // Getter and Setter methods...
}

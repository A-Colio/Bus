public class Bus {
    private String company;
    private String grade;
    private String time;
    private int remainSeats;
    private int maxSeats;

    public Bus(String company, String grade, String time, int remainSeats, int maxSeats) {
        this.company = company;
        this.grade = grade;
        this.time = time;
        this.remainSeats = remainSeats;
        this.maxSeats = maxSeats;
    }

    public String toString() {
        return company + " " + grade + " " + time + " " + remainSeats + "/" + maxSeats;
    }
}

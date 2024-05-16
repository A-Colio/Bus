import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 버스 예약하는 클래스
public class Reservation {
    private Bus[][] busTable = new Bus[7][90];
    private String[] weekStr = new String[7];
    private Bus[] trip = new Bus[3];
    public Reservation() {
        this.setBusTable();
        this.setWeekStr();
    }

    public void setBusTable() {
        this.busTable = Table.createBusTable();
    }

    public void setWeekStr() {
        for (int i = 0; i < 7; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M'월' d'일'");
            weekStr[i] = date.format(formatter);
        }
    }

    public void showBusTable(int date, int departNum, int arrivalNum) {
        System.out.println("\n" + weekStr[date] + " 버스 시간표입니다.");
        System.out.println("출발지: " + Table.terminals[departNum] + " -> 도착지: " + Table.terminals[arrivalNum]);

        for (int i = 0; i < 3; i++)
            System.out.println((i + 1) + ". " + busTable[departNum][(arrivalNum - 1) + i]);
    }

    public void addTrip(int date, int departNum, int arrivalNum){

    }
}

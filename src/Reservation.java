import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 버스 예약하는 클래스
public class Reservation {
    private Bus[][] busTable = new Bus[7][90];
    private String[] weekStr = new String[7];
    private Bus[] trip = new Bus[5];
    private int tripCnt = 0;

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
//        System.out.println("\n" + weekStr[date] + " 버스 시간표입니다.");
//        System.out.println("출발지: " + Table.terminals[departNum] + " -> 도착지: " + Table.terminals[arrivalNum]);
        System.out.print("\n" + Table.terminals[departNum] + " -> " + Table.terminals[arrivalNum]);
        System.out.println(" (" + weekStr[date] + ")");
        for (int i = 0; i < 3; i++)
            System.out.println((i + 1) + ". " + busTable[departNum][(arrivalNum - 1) + i]);
    }

    public void addTrip(int departNum, int arrivalNum, int resNum, int resSeats) {
        // departNum, dateNum, arrivalNum을 이용하여 trip에 버스 정보 저장
//        this.trip[tripCnt++] = busTable[departNum - 1][arrivalNum - 1 + (resNum - 1)];
        this.trip[tripCnt] = busTable[departNum - 1][arrivalNum - 1 + (resNum - 1)];
        this.trip[tripCnt++].reserve(resSeats);
    }

    public void printMyTrip() {
        // trip에 저장된 버스 정보 출력
        for (int i = 0; i < tripCnt; i++) {
            System.out.println("나의 여정");
            System.out.println(trip[i]);
        }
    }

    public Bus[][] getBusTable() {
        return busTable;
    }
}

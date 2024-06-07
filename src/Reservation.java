import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 버스 예약하는 클래스
public class Reservation {
    private Bus[][] busTable;
    private Map<String, ArrayList<Trip>> tripsByDate;

    public Reservation() {
        this.busTable = Table.createBusTable();
        this.tripsByDate = new HashMap<>();
    }

    public void showBusTable(int date, int departNum, int arrivalNum) {
        System.out.print("\n" + Table.getTerminal(departNum + 1) + " → " + Table.getTerminal(arrivalNum + 1));
        System.out.println(" (" + Table.getWeekStr()[date] + ")");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ". " + busTable[departNum][arrivalNum * 3 + i]);
        }
    }

    public void addTrip(int departNum, int arrivalNum, int resNum, int resSeats, String date, String depart, String arrival) {
        int busIndex = (arrivalNum - 1) * 3 + (resNum - 1);
        Bus bus = busTable[departNum - 1][busIndex];

        // 디버깅 메시지 추가
        System.out.println("선택된 버스 정보: " + bus);

        Trip trip = new Trip(bus, resSeats, date, depart, arrival);
        tripsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(trip);
        bus.reserve(resSeats);
        System.out.println("예약되었습니다");
        System.out.println(trip.getFormattedTrip());
    }

    public void printMyTrip() {
        System.out.println("나의 여정");
        for (String date : Table.getWeekStr()) {
            System.out.println("= " + date + " 예약정보 =");
            ArrayList<Trip> trips = tripsByDate.get(date);
            if (trips != null) {
                for (Trip trip : trips) {
                    System.out.println(trip.getFormattedTrip());
                }
            }
        }
    }

    public void showAllBusTables(int date) {
        for (int i = 0; i < Table.getTerminals().length; i++) {
            for (int j = 0; j < Table.getTerminals().length; j++) {
                if (i != j) {
                    showBusTable(date, i, j);
                }
            }
        }
    }

    public Bus[][] getBusTable() {
        return busTable;
    }
}

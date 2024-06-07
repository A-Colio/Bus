import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

// 버스 시간표 만드는 클래스
public class Table {
    private static String[] terminals = {"서울", "부산", "경기", "경상", "강원", "전라"};
    private static String[] weekStr = new String[7];
    private static Bus[][] busTable = createBusTable();

    public static Bus[][] createBusTable() {
        Random rand = new Random();

        String[] company = {"대진운수", "진리고속", "동아여객", "우정고속"};
        String[] grades = {"일반", "우등", "프리미엄"};
        int[] maxSeats = {45, 30, 25};

        Bus[][] tempArr = new Bus[6][90];

        int[] time = {0, 5, 10};
        int timeCnt = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 90; j++) {
                int comRand = rand.nextInt(4);
                int grdRand = rand.nextInt(3);
                int timeRand = rand.nextInt(5);
                int remRand = rand.nextInt(24) + 1;
                String timeStr = 8 + time[timeCnt++] + timeRand + ":00";

                tempArr[i][j] = new Bus(company[comRand], grades[grdRand], timeStr, remRand, maxSeats[grdRand]);
                if (timeCnt == 3) timeCnt = 0;
            }
        }
        return tempArr;
    }

    public static void setWeekStr() {
        DateTimeFormatter mmdd = DateTimeFormatter.ofPattern("M'월' d'일'", Locale.KOREAN);
        LocalDate date = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            String tempDate = date.plusDays(i).format(mmdd);
            weekStr[i] = tempDate;
        }
    }

    public static String[] getWeekStr() {
        return weekStr;
    }

    public static void showTerminals() {
        int no = 1;
        for (String des : terminals)
            System.out.println(no++ + ". " + des);
    }

    public static String[] getTerminals() {
        return terminals;
    }

    public static String getTerminal(int index) {
        return terminals[index - 1];
    }

    public static String getDate(int index) {
        return weekStr[index - 1];
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

// 버스 시간표 만드는 클래스
public class Table {
    public static String[] terminals = {"서울", "부산", "경기", "경상", "강원", "전라"};
    public static String[] weekStr = new String[7];

    // [요일][90개 버스 운행표]
    public static Bus[][] createBusTable() {
        Random rand = new Random();

        String[] company = {"대진운수", "진리고속", "동아여객", "우정고속"};
        String[] grades = {"일반", "우등", "프리미엄"};
        int[] maxSeats = {45, 30, 25};
        // 일주일 치의 시간표 생성
        Bus[][] tempArr = new Bus[7][90];

        // 오전, 오후, 저녁 시간대로 나눔
        int[] time = {0, 5, 10};
        // 매 카운트 마다 시간대 변경
        int timeCnt = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 90; j++) {
                int comRand = rand.nextInt(4);
                int grdRand = rand.nextInt(3);
                int timeRand = rand.nextInt(5);
                int remRand = rand.nextInt(24) + 1;
                String timeStr = 8 + time[timeCnt++] + timeRand + ":00";

                tempArr[i][j] = new Bus(company[comRand], grades[grdRand], timeStr, remRand, maxSeats[grdRand]);
                if (timeCnt == 3) timeCnt = 0;  // 저녁 버스시간표 설정했으면 다시 오전 시간대로
            }
        }
        return tempArr;
    }

    public static void showTerminals() {
        int no = 1;
        for (String des : terminals)
            System.out.println(no++ + ". " + des);
    }

    public static void showTerminals(int destNum) {
        int no = 1;
        for (int i = 0; i < terminals.length; i++) {
            if (i == (destNum - 1)) continue;
            System.out.println(no++ + ". " + terminals[i]);
        }

    }

    public static void setWeekStr() {
        DateTimeFormatter mmdd = DateTimeFormatter.ofPattern("M'월' d'일'", Locale.KOREAN);
        LocalDate date = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            String tempDate = date.plusDays(i).format(mmdd);
            weekStr[i] = tempDate;
        }
    }

    public static void showWeekStr() {
        for (int i = 0; i < 7; i++) {
            System.out.println((i + 1) + ". " + weekStr[i]);
        }
    }
}
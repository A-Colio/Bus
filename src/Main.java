// 코딩의 대진리: 원본은 보존하자.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Reservation myTrip = new Reservation();
        Table.setWeekStr();

        System.out.println("버스 예약을 진행합니다.\n");

        System.out.println("출발지의 번호를 입력해주세요.");
        Table.showTerminals();
        int departNum = in.nextInt();

        System.out.println("\n출발일을 선택해주세요.");
        Table.showWeekStr();
        int date = in.nextInt();

        System.out.println("\n도착지의 번호를 입력해주세요.");
        Table.showTerminals(departNum);
        int arrivalNum = in.nextInt();

        myTrip.showBusTable(date - 1, departNum - 1, arrivalNum);

        System.out.println("\n예약할 버스를 선택해주세요.");
        int resBus = in.nextInt();
//        myTrip.addTrip();
    }
}
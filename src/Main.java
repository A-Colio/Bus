// 코딩의 대진리: 원본은 보존하자.

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Reservation myTrip = new Reservation();
        Table.setWeekStr();

        while(true){
            System.out.println("버스 예약을 진행합니다.\n");

            int departNum, dateNum, arrivalNum, resBus, resSeats;
            while (true) {
                try {
                    System.out.println("출발지의 번호를 입력해주세요.");
                    Table.showTerminals();
                    System.out.print("→ ");
                    departNum = in.nextInt();
                    if (departNum < 1 || departNum > 6)
                        throw new InputMismatchException();

                    break;
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다.\n");
                    in.nextLine();
                }
            }

            // dateNum
            while (true) {
                try {
                    System.out.println("\n출발일을 선택해주세요.");
                    Table.showWeekStr();
                    System.out.print("→ ");
                    dateNum = in.nextInt();
                    if (dateNum < 1 || dateNum > 7)
                        throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다.\n");
                    in.nextLine();
                }
            }

            // arrivalNum
            while (true) {
                try {
                    System.out.println("\n도착지의 번호를 입력해주세요.");
                    Table.showTerminals(departNum);
                    System.out.print("→ ");
                    arrivalNum = in.nextInt();
                    if (arrivalNum < 1 || arrivalNum > 5)
                        throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다.\n");
                    in.nextLine();
                }
            }

            myTrip.showBusTable(dateNum - 1, departNum - 1, arrivalNum);

            // resBus
            while (true) {
                try {
                    System.out.println("\n예약할 버스의 번호를 선택해주세요.");
                    System.out.print("→ ");
                    resBus = in.nextInt();
                    if (resBus < 1 || resBus > 3)
                        throw new InputMismatchException();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다.\n");
                    in.nextLine();
                }
            }

            // resSeats
            while (true) {
                try {
                    System.out.println("\n예약할 좌석 수를 입력해주세요.");
                    System.out.print("→ ");
                    resSeats = in.nextInt();
                    int remain = myTrip.getBusTable()[departNum - 1][arrivalNum - 1 + (resBus - 1)].getRemainSeats();
                    if (resSeats < 1 || resSeats > remain)
                        throw new InputMismatchException();
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다.\n");
                    in.nextLine();
                }
            }

            myTrip.addTrip(departNum, arrivalNum, resBus, resSeats);
            myTrip.printMyTrip();

            System.out.println("계속 예약하시겠습니까? (y/n)");
            String answer = in.next();
            if (answer.equals("n")) {
                System.out.println("예약을 종료합니다.");
                break;
            }
        }
    }
}
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Reservation myTrip = new Reservation();
        Table.setWeekStr();
        int choice;

        while (true) {
            // 메뉴 출력
            System.out.println("\n=== 버스 예매 시스템 ===");
            System.out.println("1. 버스를 예약합니다");
            System.out.println("2. 버스 시간표를 조회합니다");
            System.out.println("3. 예약 정보를 조회합니다");
            System.out.println("0. 프로그램을 종료합니다");
            System.out.print("→ ");

            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                in.nextLine();  // 입력 버퍼 정리
                continue;
            }

            switch (choice) {
                case 1:
                    // 버스 예약 로직
                    reserveBus(in, myTrip);
                    break;
                case 2:
                    // 버스 시간표 조회
                    showBusSchedule(in, myTrip);
                    break;
                case 3:
                    // 예약 정보 조회
                    myTrip.printMyTrip();
                    break;
                case 0:
                    // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void reserveBus(Scanner in, Reservation myTrip) {
        int dateNum, departNum, arrivalNum, resBus, resSeats;
        String date, depart, arrival;

        // 출발일 선택
        dateNum = selectOption(in, "출발일을 선택해주세요.", Table.getWeekStr(), 1, 7);
        date = Table.getDate(dateNum);
        System.out.println();

        // 출발지 선택
        departNum = selectOption(in, "출발지의 번호를 입력해주세요.", Table.getTerminals(), 1, 6);
        depart = Table.getTerminal(departNum);
        System.out.println();

        // 도착지 선택
        arrivalNum = selectOptionWithExclude(in, "도착지의 번호를 입력해주세요.", Table.getTerminals(), departNum, 1, 6);
        arrival = Table.getTerminal(arrivalNum);
        System.out.println();

        myTrip.showBusTable(dateNum - 1, departNum - 1, arrivalNum - 1);

        // 예약할 버스 선택
        resBus = selectOption(in, "예약할 버스의 번호를 선택해주세요.", new String[]{"1", "2", "3"}, 1, 3);
        System.out.println();

        // 예약할 좌석 수 선택
        while (true) {
            try {
                System.out.println("예약할 좌석 수를 입력해주세요.");
                System.out.print("→ ");
                resSeats = in.nextInt();
                int busIndex = (arrivalNum - 1) * 3 + (resBus - 1);
                int remain = myTrip.getBusTable()[departNum - 1][busIndex].getRemainSeats();
                if (resSeats < 1 || resSeats > remain)
                    throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.\n");
                in.nextLine();
            }
        }
        System.out.println();

        myTrip.addTrip(departNum, arrivalNum, resBus, resSeats, date, depart, arrival);
        System.out.println();
    }

    private static void showBusSchedule(Scanner in, Reservation myTrip) {
        int dateNum = selectOption(in, "출발일을 선택해주세요.", Table.getWeekStr(), 1, 7);
        System.out.println();

        int departNum = selectOptionWithAll(in, "출발지의 번호를 입력해주세요.", Table.getTerminals(), 1, 6);
        System.out.println();

        if (departNum == 7) {
            myTrip.showAllBusTables(dateNum - 1);
        } else {
            int arrivalNum = selectOptionWithExclude(in, "도착지의 번호를 입력해주세요.", Table.getTerminals(), departNum, 1, 6);
            System.out.println();
            myTrip.showBusTable(dateNum - 1, departNum - 1, arrivalNum - 1);
        }
    }

    private static int selectOption(Scanner in, String message, String[] options, int min, int max) {
        int selection;
        while (true) {
            try {
                System.out.println(message);
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }
                System.out.print("→ ");
                selection = in.nextInt();
                if (selection < min || selection > max)
                    throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.\n");
                in.nextLine();
            }
        }
        return selection;
    }

    private static int selectOptionWithExclude(Scanner in, String message, String[] options, int exclude, int min, int max) {
        int selection;
        while (true) {
            try {
                System.out.println(message);
                for (int i = 0; i < options.length; i++) {
                    if (i + 1 == exclude) {
                        System.out.println((i + 1) + ". " + options[i] + " [출발지]");
                    } else {
                        System.out.println((i + 1) + ". " + options[i]);
                    }
                }
                System.out.print("→ ");
                selection = in.nextInt();
                if (selection < min || selection > max || selection == exclude)
                    throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.\n");
                in.nextLine();
            }
        }
        return selection;
    }

    private static int selectOptionWithAll(Scanner in, String message, String[] options, int min, int max) {
        int selection;
        while (true) {
            try {
                System.out.println(message);
                for (int i = 0; i < options.length; i++) {
                    System.out.println((i + 1) + ". " + options[i]);
                }
                System.out.println((options.length + 1) + ". All");
                System.out.print("→ ");
                selection = in.nextInt();
                if (selection < min || selection > max + 1)
                    throw new InputMismatchException();
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.\n");
                in.nextLine();
            }
        }
        return selection;
    }
}

// 예매한 버스 내역을 저장할 클래스
public class Trip {
    private Bus bus;
    private int reservedSeats;

    public Trip(Bus bus, int reservedSeats) {
        this.bus = bus;
        this.reservedSeats = reservedSeats;
    }

}

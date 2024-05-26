// 대진운수 일반 11:00 15/45
// 위와 같은 버스 객체를 생성하는 클래스
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

    @Override
    public String toString() {
        return company + " " + grade + " " + time + " " + remainSeats + "/" + maxSeats;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRemainSeats() {
        return remainSeats;
    }

    public void setRemainSeats(int remainSeats) {
        this.remainSeats = remainSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public void reserve(int seats) {
        this.remainSeats -= seats;
        System.out.println("예약이 완료되었습니다. 남은 좌석 수: " + this.remainSeats);
    }
}

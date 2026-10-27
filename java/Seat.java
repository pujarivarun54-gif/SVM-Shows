import java.io.Serializable;

public class Seat implements Serializable {
    private final String seatNumber;
    private boolean booked;
    public Seat(String seatNumber) { this.seatNumber = seatNumber; }
    public String getSeatNumber() { return seatNumber; }
    public boolean isBooked() { return booked; }
    public void book() { booked = true; }
    public void release() { booked = false; }
    @Override public String toString() { return seatNumber + (booked ? "[BOOKED]" : "[AVAILABLE]"); }
}

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Show implements Serializable {
    private final int showId;
    private final Movie movie;
    private final String date;
    private final String time;
    private final double ticketPrice;
    private final Map<String, Seat> seats = new LinkedHashMap<>();

    public Show(int showId, Movie movie, String date, String time, double ticketPrice) {
        this.showId = showId; this.movie = movie; this.date = date; this.time = time; this.ticketPrice = ticketPrice;
        for (char row = 'A'; row <= 'C'; row++) for (int n = 1; n <= 5; n++) seats.put(row + String.valueOf(n), new Seat(row + String.valueOf(n)));
    }
    public int getShowId() { return showId; }
    public Movie getMovie() { return movie; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public double getTicketPrice() { return ticketPrice; }
    public Seat getSeat(String number) { return seats.get(number.toUpperCase()); }
    public Collection<Seat> getSeats() { return seats.values(); }
    public void displaySeats() {
        System.out.println("\nSCREEN\n");
        for (char row='A'; row<='C'; row++) {
            for (int n=1; n<=5; n++) {
                Seat s = seats.get(row + String.valueOf(n));
                System.out.printf("%-14s", s.isBooked() ? "[X]" + s.getSeatNumber() : "[ ]" + s.getSeatNumber());
            }
            System.out.println();
        }
        System.out.println("[ ] Available   [X] Booked");
    }
    @Override public String toString() { return showId + " | " + movie.getMovieName() + " | " + date + " | " + time + " | Rs." + ticketPrice; }
}

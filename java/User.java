import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private final String name;
    private final String email;
    private final String phone;
    private final String password;
    private final List<Booking> bookingHistory = new ArrayList<>();

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public boolean checkPassword(String value) { return password.equals(value); }
    public List<Booking> getBookingHistory() { return bookingHistory; }
    public void addBooking(Booking booking) { bookingHistory.add(booking); }
    public void removeBooking(String bookingId) { bookingHistory.removeIf(b -> b.getBookingId().equalsIgnoreCase(bookingId)); }
    @Override public String toString() { return name + " | " + email + " | " + phone; }
}

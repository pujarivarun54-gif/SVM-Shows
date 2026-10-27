import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Serializable {
    private final String bookingId;
    private final String customerName;
    private final Movie movie;
    private final Theatre theatre;
    private final Show show;
    private final List<String> selectedSeats;
    private final double totalAmount;
    private String status;
    public Booking(String bookingId, String customerName, Movie movie, Theatre theatre, Show show, List<String> seats, double totalAmount) {
        this.bookingId=bookingId; this.customerName=customerName; this.movie=movie; this.theatre=theatre; this.show=show;
        this.selectedSeats=new ArrayList<>(seats); this.totalAmount=totalAmount; this.status="CONFIRMED";
    }
    public String getBookingId(){return bookingId;} public String getCustomerName(){return customerName;} public Movie getMovie(){return movie;}
    public Theatre getTheatre(){return theatre;} public Show getShow(){return show;} public List<String> getSelectedSeats(){return selectedSeats;}
    public double getTotalAmount(){return totalAmount;} public String getStatus(){return status;} public void cancel(){status="CANCELLED";}
    @Override public String toString(){return bookingId+" | "+movie.getMovieName()+" | "+theatre.getName()+" | "+show.getDate()+" "+show.getTime()+" | Seats: "+selectedSeats+" | Rs."+String.format("%.2f",totalAmount)+" | "+status;}
}

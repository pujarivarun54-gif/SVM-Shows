public class Ticket {
    public static void printTicket(Booking b) {
        System.out.println("\n========== BOOKING CONFIRMATION ==========");
        System.out.println("Booking ID : " + b.getBookingId());
        System.out.println("Customer   : " + b.getCustomerName());
        System.out.println("Movie      : " + b.getMovie().getMovieName());
        System.out.println("Theatre    : " + b.getTheatre().getName() + " (" + b.getTheatre().getLocation() + ")");
        System.out.println("Date       : " + b.getShow().getDate());
        System.out.println("Show Time  : " + b.getShow().getTime());
        System.out.println("Seats      : " + String.join(", ", b.getSelectedSeats()));
        System.out.println("Tickets    : " + b.getSelectedSeats().size());
        System.out.printf("Amount     : Rs.%.2f%n", b.getTotalAmount());
        System.out.println("Status     : " + b.getStatus());
        System.out.println("==========================================\n");
    }
}

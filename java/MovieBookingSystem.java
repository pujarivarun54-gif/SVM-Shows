import java.time.LocalDate;
import java.util.*;

public class MovieBookingSystem {
    private static final String ADMIN_EMAIL = "admin@svm.com";
    private static final String ADMIN_PASSWORD = "admin123";
    private final Scanner scanner;
    private final List<User> users = new ArrayList<>();
    private final List<Movie> movies = new ArrayList<>();
    private final List<Theatre> theatres = new ArrayList<>();
    private final Map<String, Booking> bookings = new LinkedHashMap<>();
    private User currentUser;
    private Movie selectedMovie;
    private Theatre selectedTheatre;
    private Show selectedShow;
    private List<String> selectedSeats = new ArrayList<>();
    private int movieIdCounter = 4, showIdCounter = 10;

    public MovieBookingSystem(Scanner scanner){ this.scanner=scanner; seedData(); FileManager.initialize(); }
    private void seedData(){
        movies.add(new Movie(1,"Jailer 2","Action Thriller","Tamil",165,8.5));
        movies.add(new Movie(2,"Ranabaali","Drama","Telugu",145,8.0));
        movies.add(new Movie(3,"Vikram","Action","Tamil",174,8.3));
        Theatre t1=new Theatre(1,"Ranga Cine Central","Dharmavaram");
        Theatre t2=new Theatre(2,"SVM Cinemas","Anantapur");
        for(Movie m:movies){ t1.addShow(new Show(showIdCounter++,m,LocalDate.now().toString(),"06:20 PM",150)); t1.addShow(new Show(showIdCounter++,m,LocalDate.now().toString(),"09:20 PM",150)); }
        t2.addShow(new Show(showIdCounter++,movies.get(0),LocalDate.now().plusDays(1).toString(),"06:30 PM",180));
        t2.addShow(new Show(showIdCounter++,movies.get(1),LocalDate.now().plusDays(1).toString(),"09:30 PM",160));
        theatres.add(t1); theatres.add(t2);
    }
    public void start(){
        while(true){
            System.out.println("\n===== MOVIE TICKET BOOKING SYSTEM =====");
            System.out.println("1. User Registration\n2. User Login\n3. Admin Login\n4. View Movies\n5. Exit");
            int choice=readInt("Enter choice: ");
            switch(choice){case 1: register(); break; case 2: login(); break; case 3: adminLogin(); break; case 4: displayMovies(); break; case 5: System.out.println("Thank you for using SVM Shows!"); return; default:System.out.println("Invalid choice.");}
        }
    }
    private void register(){
        System.out.println("\n--- USER REGISTRATION ---");
        String name=readNonEmpty("Name: "), email=readNonEmpty("Email: ").toLowerCase(), phone=readNonEmpty("Phone: "), password=readNonEmpty("Password: ");
        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){System.out.println("Invalid email.");return;}
        if(!phone.matches("\\d{10}")){System.out.println("Phone must contain exactly 10 digits.");return;}
        for(User u:users) if(u.getEmail().equalsIgnoreCase(email)){System.out.println("Email already registered.");return;}
        User u=new User(name,email,phone,password); users.add(u); FileManager.saveUser(u); System.out.println("Registration successful. Please login.");
    }
    private void login(){
        String email=readNonEmpty("Email: ").toLowerCase(), password=readNonEmpty("Password: ");
        for(User u:users) if(u.getEmail().equalsIgnoreCase(email)&&u.checkPassword(password)){ currentUser=u; userMenu(); return; }
        System.out.println("Invalid email or password.");
    }
    private void adminLogin(){
        String email=readNonEmpty("Admin email: "), password=readNonEmpty("Admin password: ");
        if(ADMIN_EMAIL.equalsIgnoreCase(email)&&ADMIN_PASSWORD.equals(password)) adminMenu(); else System.out.println("Invalid admin credentials.");
    }
    private void userMenu(){
        while(currentUser!=null){
            System.out.println("\n===== USER MENU =====\n1. View Movies\n2. Select Movie\n3. Select Theatre\n4. Select Show\n5. Select Seats\n6. Book Ticket\n7. Make Payment\n8. View Ticket\n9. Cancel Booking\n10. Booking History\n11. Logout");
            int c=readInt("Enter choice: ");
            try{switch(c){case 1:displayMovies();break;case 2:selectMovie();break;case 3:selectTheatre();break;case 4:selectShow();break;case 5:selectSeats();break;case 6:bookTicket();break;case 7:makePayment();break;case 8:viewTicket();break;case 9:cancelBooking();break;case 10:history();break;case 11:currentUser=null; selectedMovie=null;selectedTheatre=null;selectedShow=null;selectedSeats.clear();System.out.println("Logged out.");break;default:System.out.println("Invalid choice.");}}catch(BookingException e){System.out.println("Booking error: "+e.getMessage());}
        }
    }
    private void displayMovies(){ System.out.println("\nID | Movie                | Genre        | Language | Duration | Rating"); System.out.println("--------------------------------------------------------------------------"); for(Movie m:movies)System.out.println(m); }
    private void selectMovie(){ displayMovies(); int id=readInt("Enter movie ID: "); for(Movie m:movies)if(m.getMovieId()==id){selectedMovie=m;selectedTheatre=null;selectedShow=null;selectedSeats.clear();System.out.println("Selected: "+m.getMovieName());return;}System.out.println("Movie not found."); }
    private void selectTheatre(){
        if(selectedMovie==null){System.out.println("Select a movie first.");return;} for(Theatre t:theatres)System.out.println(t); int id=readInt("Enter theatre ID: "); for(Theatre t:theatres)if(t.getTheatreId()==id){selectedTheatre=t;selectedShow=null;selectedSeats.clear();System.out.println("Selected: "+t.getName());return;}System.out.println("Theatre not found.");
    }
    private void selectShow(){
        if(selectedTheatre==null){System.out.println("Select a theatre first.");return;} for(Show s:selectedTheatre.getShows())if(s.getMovie().getMovieId()==selectedMovie.getMovieId())System.out.println(s); int id=readInt("Enter show ID: "); for(Show s:selectedTheatre.getShows())if(s.getShowId()==id&&s.getMovie().getMovieId()==selectedMovie.getMovieId()){selectedShow=s;selectedSeats.clear();System.out.println("Selected show: "+s);return;}System.out.println("Show not found for selected movie.");
    }
    private void selectSeats() throws BookingException{
        if(selectedShow==null)throw new BookingException("Select movie, theatre and show first."); selectedShow.displaySeats(); String input=readNonEmpty("Enter seats separated by comma (example A1,A2): "); List<String> temp=new ArrayList<>(); for(String raw:input.split(",")){String seatNo=raw.trim().toUpperCase();Seat s=selectedShow.getSeat(seatNo);if(s==null)throw new BookingException("Invalid seat: "+seatNo);if(s.isBooked())throw new BookingException("Seat already booked: "+seatNo);if(temp.contains(seatNo))throw new BookingException("Duplicate seat: "+seatNo);temp.add(seatNo);} selectedSeats=temp;System.out.println("Selected seats: "+selectedSeats);
    }
    private void bookTicket() throws BookingException{
        if(currentUser==null||selectedShow==null||selectedSeats.isEmpty())throw new BookingException("Select a show and seats first.");
        String id="SVM-"+System.currentTimeMillis()%1000000; double total=selectedSeats.size()*selectedShow.getTicketPrice();
        for(String seat:selectedSeats)selectedShow.getSeat(seat).book(); Booking b=new Booking(id,currentUser.getName(),selectedMovie,selectedTheatre,selectedShow,selectedSeats,total); bookings.put(id,b);currentUser.addBooking(b);FileManager.saveBooking(b);Ticket.printTicket(b);selectedSeats=new ArrayList<>();
    }
    private void makePayment(){
        if(currentUser==null){return;} String id=readNonEmpty("Enter booking ID for payment: "); Booking b=bookings.get(id);if(b==null||!b.getCustomerName().equals(currentUser.getName())||b.getStatus().equals("CANCELLED")){System.out.println("Booking not found.");return;}
        System.out.printf("Amount: Rs.%.2f%n1. UPI\n2. Card\n3. Cash\n",b.getTotalAmount()); int c=readInt("Choose payment: "); Payment p; switch(c){case 1:p=new UPIPayment(readNonEmpty("UPI ID: "));break;case 2:p=new CardPayment(readNonEmpty("16-digit card number: "));break;case 3:p=new CashPayment();break;default:System.out.println("Invalid payment option.");return;} if(p.pay(b.getTotalAmount()))System.out.println("Payment successful via "+p.getMethodName()+". Booking confirmed!");else System.out.println("Payment validation failed.");
    }
    private void viewTicket(){String id=readNonEmpty("Booking ID: ");Booking b=bookings.get(id);if(b!=null&&b.getCustomerName().equals(currentUser.getName()))Ticket.printTicket(b);else System.out.println("Booking not found.");}
    private void cancelBooking(){String id=readNonEmpty("Booking ID to cancel: ");Booking b=bookings.get(id);if(b==null||!b.getCustomerName().equals(currentUser.getName())){System.out.println("Booking not found.");return;}if(b.getStatus().equals("CANCELLED")){System.out.println("Already cancelled.");return;}for(String seat:b.getSelectedSeats())b.getShow().getSeat(seat).release();b.cancel();System.out.printf("Booking cancelled. Refund amount: Rs.%.2f%n",b.getTotalAmount());}
    private void history(){System.out.println("\n--- BOOKING HISTORY ---");if(currentUser.getBookingHistory().isEmpty())System.out.println("No bookings.");else for(Booking b:currentUser.getBookingHistory())System.out.println(b);}
    private void adminMenu(){while(true){System.out.println("\n===== ADMIN MENU =====\n1. Add Movie\n2. Remove Movie\n3. View Movies\n4. View Bookings\n5. Logout");int c=readInt("Enter choice: ");switch(c){case 1:addMovie();break;case 2:removeMovie();break;case 3:displayMovies();break;case 4:viewAllBookings();break;case 5:return;default:System.out.println("Invalid choice.");}}}
    private void addMovie(){String name=readNonEmpty("Movie name: "),genre=readNonEmpty("Genre: "),lang=readNonEmpty("Language: ");int duration=readInt("Duration in minutes: ");double rating=readDouble("Rating (0-10): ");if(rating<0||rating>10){System.out.println("Rating must be 0-10.");return;}Movie m=new Movie(movieIdCounter++,name,genre,lang,duration,rating);movies.add(m);System.out.println("Movie added: "+m);}
    private void removeMovie(){displayMovies();int id=readInt("Movie ID to remove: ");boolean removed=movies.removeIf(m->m.getMovieId()==id);System.out.println(removed?"Movie removed.":"Movie not found.");}
    private void viewAllBookings(){System.out.println("\n--- ALL BOOKINGS ---");if(bookings.isEmpty())System.out.println("No bookings in this session.");else bookings.values().forEach(System.out::println);System.out.println("Saved file records: "+FileManager.readBookingRecords().size());}
    private String readNonEmpty(String prompt){while(true){System.out.print(prompt);String s=scanner.nextLine().trim();if(!s.isEmpty())return s;System.out.println("Input cannot be empty.");}}
    private int readInt(String prompt){while(true){try{System.out.print(prompt);return Integer.parseInt(scanner.nextLine().trim());}catch(NumberFormatException e){System.out.println("Please enter a valid integer.");}}}
    private double readDouble(String prompt){while(true){try{System.out.print(prompt);return Double.parseDouble(scanner.nextLine().trim());}catch(NumberFormatException e){System.out.println("Please enter a valid number.");}}}
}

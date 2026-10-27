import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("      SVM SHOWS - MOVIE BOOKING");
        System.out.println("====================================");
        try (Scanner scanner = new Scanner(System.in)) {
            new MovieBookingSystem(scanner).start();
        }
    }
}

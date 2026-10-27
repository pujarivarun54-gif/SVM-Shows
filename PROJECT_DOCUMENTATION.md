# SVM Shows - Project Documentation

## Title
Movie Ticket Booking System using Core Java

## Problem Statement
Manual movie ticket booking can make it difficult to manage movies, theatres, shows, seats, payments and cancellations. This project provides a simple console-based system that organizes these operations using Java OOP concepts.

## Objectives
1. Register and authenticate users.
2. Display and manage movies.
3. Select theatres, dates and show timings.
4. Display a 3 x 5 seat layout and prevent double booking.
5. Create tickets and calculate price.
6. Demonstrate UPI, Card and Cash payment options.
7. Cancel bookings and release seats.
8. Maintain booking history and file records.
9. Provide a separate admin module.
10. Demonstrate core Java and OOP concepts clearly.

## Requirements
- JDK 8 or later
- Eclipse, IntelliJ IDEA or VS Code, or terminal
- No external libraries
- Core Java only for the college application

## Class responsibilities
| Class | Responsibility |
|---|---|
| Main | Starts the application |
| MovieBookingSystem | Controls menus and complete booking workflow |
| User | Stores customer data and booking history |
| Admin | Extends User and demonstrates inheritance/overriding |
| Movie | Stores movie information |
| Theatre | Stores theatre information and shows |
| Show | Stores date/time/price and manages seats |
| Seat | Stores seat number and booked state |
| Booking | Stores booking details and cancellation state |
| Payment | Interface for payment abstraction |
| UPIPayment | UPI payment implementation |
| CardPayment | Card payment implementation |
| CashPayment | Cash payment implementation |
| Ticket | Prints booking confirmation; also demonstrates method overloading |
| BookingException | User-defined exception |
| FileManager | Demonstrates file handling |

## Relationships
- `Admin IS-A User` through inheritance.
- `Theatre HAS-A List<Show>`.
- `Show HAS-A Map<String, Seat>`.
- `Booking HAS-A Movie, Theatre, Show and seats`.
- `MovieBookingSystem HAS-A Lists/Map of users, movies, theatres and bookings`.
- `UPIPayment`, `CardPayment` and `CashPayment` implement `Payment` and demonstrate runtime polymorphism.

## OOP concepts demonstrated
- **Classes and Objects:** all model classes are instantiated by the system.
- **Encapsulation:** fields are private and accessed through methods.
- **Constructors:** every major model has a constructor.
- **Inheritance:** `Admin extends User`.
- **Polymorphism:** `Payment payment` can refer to UPI, Card or Cash objects.
- **Abstraction:** `Payment` defines the payment contract without exposing implementation details.
- **Method Overriding:** `Admin.toString()` and payment implementations override methods.
- **Method Overloading:** `Ticket.printTicket(Booking)` and `Ticket.printTicket(Booking, boolean)`.
- **Collections:** ArrayList, LinkedHashMap, List, Map and Collection.
- **Arrays/Strings/Loops/Conditions:** used throughout input validation, seat creation and menus.
- **Exception Handling:** `try/catch` handles invalid numeric input and `BookingException` handles booking errors.
- **User-defined Exception:** `BookingException`.
- **File Handling:** `FileManager` creates and writes `data/users.txt` and `data/bookings.txt` and reads saved booking records.
- **Static:** constants and utility methods.
- **Final:** immutable identifiers/constants and final fields.

## Run the Java project
From the repository root:

```bash
cd java
javac *.java
java Main
```

Admin credentials:
- Email: `admin@svm.com`
- Password: `admin123`

## Vercel deployment
The Java console program is kept in `java/` for the college requirement. Vercel hosts the browser frontend (`index.html`, `style.css`, `app.js`) as a static site. Vercel's Git integration can deploy a static site from the repository, while the Java console program should be compiled/run with a JDK locally. The browser demo stores demo user/booking data in localStorage.

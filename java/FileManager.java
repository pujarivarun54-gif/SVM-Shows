import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileManager {
    private FileManager() {}
    private static final String DATA_DIR = "data";
    private static final String USERS_FILE = DATA_DIR + File.separator + "users.txt";
    private static final String BOOKINGS_FILE = DATA_DIR + File.separator + "bookings.txt";

    public static void initialize() {
        File dir = new File(DATA_DIR); if (!dir.exists()) dir.mkdirs();
        touch(USERS_FILE); touch(BOOKINGS_FILE);
    }
    private static void touch(String path){ try { File f=new File(path); if(!f.exists()) f.createNewFile(); } catch(IOException ignored){} }
    public static void saveUser(User u){ initialize(); try(PrintWriter out=new PrintWriter(new FileWriter(USERS_FILE,true))){ out.println(u.getName().replace("|"," ")+"|"+u.getEmail()+"|"+u.getPhone()); }catch(IOException e){System.out.println("Could not save user: "+e.getMessage());} }
    public static void saveBooking(Booking b){ initialize(); try(PrintWriter out=new PrintWriter(new FileWriter(BOOKINGS_FILE,true))){ out.println(b.toString().replace("\n"," ")); }catch(IOException e){System.out.println("Could not save booking: "+e.getMessage());} }
    public static List<String> readBookingRecords(){ initialize(); List<String> rows=new ArrayList<>(); try(BufferedReader br=new BufferedReader(new FileReader(BOOKINGS_FILE))){String line; while((line=br.readLine())!=null) if(!line.isBlank()) rows.add(line);}catch(IOException e){System.out.println("Could not read bookings: "+e.getMessage());} return rows; }
}

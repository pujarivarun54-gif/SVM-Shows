import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Theatre implements Serializable {
    private final int theatreId;
    private final String name;
    private final String location;
    private final List<Show> shows = new ArrayList<>();
    public Theatre(int theatreId, String name, String location) { this.theatreId = theatreId; this.name = name; this.location = location; }
    public int getTheatreId() { return theatreId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public List<Show> getShows() { return shows; }
    public void addShow(Show show) { shows.add(show); }
    @Override public String toString() { return theatreId + " | " + name + " | " + location; }
}

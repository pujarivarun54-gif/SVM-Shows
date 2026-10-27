import java.io.Serializable;

public class Movie implements Serializable {
    private final int movieId;
    private String movieName;
    private String genre;
    private String language;
    private int durationMinutes;
    private double rating;

    public Movie(int movieId, String movieName, String genre, String language, int durationMinutes, double rating) {
        this.movieId = movieId; this.movieName = movieName; this.genre = genre; this.language = language;
        this.durationMinutes = durationMinutes; this.rating = rating;
    }
    public int getMovieId() { return movieId; }
    public String getMovieName() { return movieName; }
    public String getGenre() { return genre; }
    public String getLanguage() { return language; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getRating() { return rating; }
    public void setMovieName(String value) { movieName = value; }
    @Override public String toString() {
        return String.format("%d | %-20s | %-12s | %-8s | %3d min | %.1f/10", movieId, movieName, genre, language, durationMinutes, rating);
    }
}

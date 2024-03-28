import java.util.Objects;

public class Film implements Comparable<Film> {
    private String title;
    private String genre;
    private double totalRatings;
    private int numberOfRatings;

    public Film(String title, String genre, double totalRatings, int numberOfRatings) {
        this.title = title;
        this.genre = genre;
        this.totalRatings = totalRatings;
        this.numberOfRatings = numberOfRatings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(double totalRatings) {
        this.totalRatings = totalRatings;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", totalRatings=" + totalRatings +
                ", numberOfRatings=" + numberOfRatings +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public void addRating(double rating) {
        totalRatings += rating;
        numberOfRatings++;
    }

    public double getFinalRating() {
        if (numberOfRatings == 0) {
            return 0.0;
        }
        return totalRatings / (double) numberOfRatings;
    }

    @Override
    public int compareTo(Film o) {
        return this.title.compareTo(o.title);
    }
}
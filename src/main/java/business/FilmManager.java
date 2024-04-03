package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmManager {
    private static HashMap<String, Film> films= new HashMap<>();

    public FilmManager() {
        films = new HashMap<>();
    }

    public static Film getFilmByTitle(String title) {
        return films.get(title);
    }

    public static List<Film> getFilmsByGenre(String genre) {
        List<Film> filmList = new ArrayList<>();
        for (Film film : films.values()) {
            if (film.getGenre().equals(genre)) {
                filmList.add(film);
            }
        }
        return filmList;
    }

    public static void filmRating(String title, double rating) {
        Film film = films.get(title);
        film.addRating(rating);
    }

    public static boolean addFilm(Film film) {
        if (film == null) {
            System.out.println("Film cannot be null.");
            return false;
        }

        if (films.get(film.getTitle()) != null) {
            System.out.println("Film already exists.");
            return false;
        }

        films.put(film.getTitle(), film);
        return true;
    }

    public static boolean deleteFilmByTitle(String title) {
        if (title == null) {
            System.out.println("Film cannot be null.");
            return false;
        }

        films.remove(title);
        return true;
    }
}
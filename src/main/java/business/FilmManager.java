package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmManager {
    private HashMap<String, Film> films;

    public FilmManager() {
        films = new HashMap<String, Film>();
    }

    public Film getFilmByTitle(String title) {
        return films.get(title.toLowerCase());
    }

    public List<Film> getFilmsByGenre(String genre) {
        List<Film> filmList = new ArrayList<>();
        for (Film film : films.values()) {
            if (film.getGenre().equals(genre)) {
                filmList.add(film);
            }
        }
        return filmList;
    }

    public void filmRating(String title, double rating) {
        Film film = films.get(title);
        film.addRating(rating);
    }

    public boolean addFilm(Film film) {
        if (film == null) {
            System.out.println("Film.Film cannot be null.");
            return false;
        }

        if (film.equals(getFilmByTitle(film.getTitle()))) {
            System.out.println("Film.Film already exists.");
            return false;
        }

        films.put(film.getTitle(), film);
        return true;
    }

    public boolean deleteFilmByTitle(String title) {
        if (title == null) {
            System.out.println("Film.Film cannot be null.");
            return false;
        }

        films.remove(title);
        return true;
    }
}
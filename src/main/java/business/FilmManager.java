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
        for (String key : films.keySet()){
            if(key.equalsIgnoreCase(title)){
                return films.get(key);
            }
        }
        return null;
    }

    public static List<Film> getFilmsByGenre(String genre) {
        List<Film> filmList = new ArrayList<>();
        for (Film film : films.values()) {
            if (film.getGenre().toLowerCase().equals(genre)) {
                filmList.add(film);
            }
        }
        return filmList;
    }

    public static void filmRating(String title, double rating) {
        for (String key : films.keySet()){
            if(key.equalsIgnoreCase(title)){
                Film film = films.get(key);
                film.addRating(rating);
            }
        }
    }

    public static boolean addFilm(Film film) {
        if (film == null) {
            System.out.println("Film cannot be null.");
            return false;
        }

        for(String key : films.keySet()){
            if(key.equalsIgnoreCase(film.getTitle())){
                System.out.println("Film already exists.");
                return false;
            }
        }

        films.put(film.getTitle(), film);
        return true;
    }

    public static boolean deleteFilmByTitle(String title) {
        if (title == null) {
            System.out.println("Film cannot be null.");
            return false;
        }
        for (String key : films.keySet()){
            if(key.equalsIgnoreCase(title)){
                films.remove(key);
                return true;
            }
        }
        return false;
    }
}
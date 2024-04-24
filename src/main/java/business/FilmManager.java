package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilmManager {
    private final HashMap<String, Film> films= new HashMap<>();

    public FilmManager() { }

    public Film getFilmByTitle(String title) {
        synchronized (films){
            for (String key : films.keySet()){
                if(key.equalsIgnoreCase(title)){
                    return films.get(key);
                }
            }
            return null;
        }
    }

    public List<Film> getFilmsByGenre(String genre) {
        synchronized (films){
            List<Film> filmList = new ArrayList<>();
            for (Film film : films.values()) {
                if (film.getGenre().toLowerCase().equals(genre)) {
                    filmList.add(film);
                }
            }
            return filmList;
        }
    }

    public List<Film> getRecommendedFilms(){
        synchronized (films){
            List<Film> filmList = new ArrayList<>();
            for(Film film : films.values()){
                if(film.getTotalRatings() >= 8.0){
                    filmList.add(film);
                }
            }
            return filmList;
        }
    }

    public void filmRating(String title, double rating) {
        synchronized (films){
            for (String key : films.keySet()){
                if(key.equalsIgnoreCase(title)){
                    Film film = films.get(key);
                    film.addRating(rating);
                }
            }
        }
    }

    public boolean addFilm(Film film) {
        synchronized (films){
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
    }

    public boolean deleteFilmByTitle(String title) {
        synchronized (films){
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
}
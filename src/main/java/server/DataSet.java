package server;

import business.Film;
import business.User;
import business.UserManager;

import static business.FilmManager.addFilm;

public class DataSet {
    //Initial admin
    public void initialAdmin(){
        User admin = new User("Tom", "666", "ADMIN");
        UserManager.register(admin);
    }
    //Initial film data
    public void initialFilmData(){
        addFilm(new Film("Titanic", "Romance", 100, 10));
        addFilm(new Film("The Matrix", "Sci-Fi"));
        addFilm(new Film("Gladiator", "Action", 90, 10));
        addFilm(new Film("Pulp Fiction", "Crime"));
        addFilm(new Film("Forrest Gump", "Drama", 95, 10));
        addFilm(new Film("The Godfather", "Crime"));
        addFilm(new Film("Schindler's List", "Drama"));
        addFilm(new Film("The Shaw shank Redemption", "Drama"));
        addFilm(new Film("Inception", "Sci-Fi"));
        addFilm(new Film("Fight Club", "Drama"));
        addFilm(new Film("The Dark Knight", "Action"));
        addFilm(new Film("Star Wars: A New Hope", "Sci-Fi"));
        addFilm(new Film("Jurassic Park", "Sci-Fi"));
        addFilm(new Film("The Lord of the Rings: The Fellowship of the Ring", "Fantasy"));
        addFilm(new Film("Back to the Future", "Sci-Fi"));
        addFilm(new Film("Saving Private Ryan", "War"));
        addFilm(new Film("Casablanca", "Romance"));
        addFilm(new Film("The Silence of the Lambs", "Thriller"));
        addFilm(new Film("Avatar", "Sci-Fi"));
        addFilm(new Film("Interstellar", "Sci-Fi"));
    }
}
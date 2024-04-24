package server;

import business.Film;
import business.FilmManager;
import business.User;
import business.UserManager;

public class DataSet {
    private final UserManager userManager;
    private final FilmManager filmManager;

    public DataSet(UserManager userManager, FilmManager filmManager) {
        this.userManager = userManager;
        this.filmManager = filmManager;
    }

    //Initial admin
    public void initialAdmin(){
        User admin = new User("Tom", "666", "ADMIN");
        userManager.register(admin);
    }
    //Initial film data
    public void initialFilmData(){
        filmManager.addFilm(new Film("Titanic", "Romance", 100, 10));
        filmManager.addFilm(new Film("The Matrix", "Sci-Fi"));
        filmManager.addFilm(new Film("Gladiator", "Action", 90, 10));
        filmManager.addFilm(new Film("Pulp Fiction", "Crime"));
        filmManager.addFilm(new Film("Forrest Gump", "Drama", 95, 10));
        filmManager.addFilm(new Film("The Godfather", "Crime"));
        filmManager.addFilm(new Film("Schindler's List", "Drama"));
        filmManager.addFilm(new Film("The Shaw shank Redemption", "Drama"));
        filmManager.addFilm(new Film("Inception", "Sci-Fi"));
        filmManager.addFilm(new Film("Fight Club", "Drama"));
        filmManager.addFilm(new Film("The Dark Knight", "Action"));
        filmManager.addFilm(new Film("Star Wars: A New Hope", "Sci-Fi"));
        filmManager.addFilm(new Film("Jurassic Park", "Sci-Fi"));
        filmManager.addFilm(new Film("The Lord of the Rings: The Fellowship of the Ring", "Fantasy"));
        filmManager.addFilm(new Film("Back to the Future", "Sci-Fi"));
        filmManager.addFilm(new Film("Saving Private Ryan", "War"));
        filmManager.addFilm(new Film("Casablanca", "Romance"));
        filmManager.addFilm(new Film("The Silence of the Lambs", "Thriller"));
        filmManager.addFilm(new Film("Avatar", "Sci-Fi"));
        filmManager.addFilm(new Film("Interstellar", "Sci-Fi"));
    }
}
package server;

import business.Film;
import business.FilmManager;
import business.User;
import business.UserManager;
import protocol.FilmService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;


public class RequestHandler implements Runnable{
    private User currentUser;
    private final Socket dataSocket;
    private final UserManager userManager;
    private final FilmManager filmManager;

    public RequestHandler(Socket dataSocket, UserManager userManager, FilmManager filmManager) {
        this.dataSocket = dataSocket;
        this.userManager = userManager;
        this.filmManager = filmManager;
    }

    @Override
    public void run() {
        //Create input scanner
        try (Scanner input = new Scanner(dataSocket.getInputStream())) {
            //Output for response message
            PrintWriter output = new PrintWriter(dataSocket.getOutputStream());
            //Create string builder
            StringBuilder responseBuilder = new StringBuilder();
            //Valid session
            boolean validation = true;
            while (validation) {
                //Take in request
                String request = input.nextLine();
                System.out.println("Request received from " + dataSocket.getInetAddress() + ":" + dataSocket.getPort() + ":" + request);
                //Parse the request
                String[] component = request.split(FilmService.DELIMITER);
                //Create response
                String response = null;
                Film film;
                //Handle request
                switch (component[0]) {
                    //Register
                    case FilmService.REGISTER:
                        User newUser = new User(component[1], component[2], "USER");
                        if (userManager.getUserByUsername(component[1]) != null) {
                            response = FilmService.REJECTED;
                            break;
                        }
                        boolean isRegister = userManager.register(newUser);

                        if (isRegister) {
                            response = FilmService.ADDED;
                        } else {
                            response = FilmService.REJECTED;
                        }
                        break;
                    //Login
                    case FilmService.LOGIN:
                        User user = userManager.getUserByUsername(component[1]);
                        //Check user if null
                        if (user == null) {
                            response = FilmService.FAILED;
                            break;
                        }
                        //Check password
                        if (user.validatePassword(component[2])) {
                            //Set current user
                            this.currentUser = user;
                            response = user.getAdminStatus().equals("ADMIN") ? FilmService.SUCCESS_ADMIN : FilmService.SUCCESS_USER;
                        } else {
                            response = FilmService.FAILED; //Failed to validate password
                        }
                        break;
                    //Logout
                    case FilmService.LOGOUT:
                        response = FilmService.LOGGED_OUT;
                        this.currentUser = null;
                        break;
                    //Rate
                    case FilmService.RATE:
                        //Check if login
                        if (currentUser == null) {
                            response = FilmService.NOT_LOGGED_IN;
                            break;
                        }
                        film = filmManager.getFilmByTitle(component[1]);
                        //Check film if exists
                        if (film == null) {
                            response = FilmService.NOT_MATCH_FOUND;
                            break;
                        }
                        //Convert the third element of component to double
                        try {
                            double rating = Double.parseDouble(component[2]);
                            filmManager.filmRating(component[1], rating);
                            response = FilmService.SUCCESS;
                        } catch (NumberFormatException e) {
                            response = FilmService.INVALID_RATING_SUPPLIED;
                        }
                        break;
                    //Search film by title
                    case FilmService.SEARCH_BY_NAME:
                        film = filmManager.getFilmByTitle(component[1]);
                        //Check film if exists
                        if (film == null) {
                            response = FilmService.NOT_MATCH_FOUND;
                            break;
                        }

                        response = film.getTitle() + FilmService.DELIMITER + film.getGenre()
                                + FilmService.DELIMITER + film.getFinalRating()
                                + FilmService.DELIMITER + film.getNumberOfRatings();
                        break;
                    //Search film by genre
                    case FilmService.SEARCH_BY_GENRE:
                        List<Film> filmListByGenre = filmManager.getFilmsByGenre(component[1]);
                        //Check film list if empty
                        if (filmListByGenre.isEmpty()) {
                            response = FilmService.NOT_MATCH_FOUND;
                            break;
                        }

                        responseBuilder.append(filmListByGenre.get(0).getTitle()).append(FilmService.DELIMITER)
                                .append(filmListByGenre.get(0).getGenre()).append(FilmService.DELIMITER)
                                .append(filmListByGenre.get(0).getFinalRating()).append(FilmService.DELIMITER)
                                .append(filmListByGenre.get(0).getNumberOfRatings());

                        if (filmListByGenre.size() > 1) {
                            for (int i = 1; i < filmListByGenre.size(); i++) {
                                responseBuilder.append(FilmService.GENRE_DELIMITER)
                                        .append(filmListByGenre.get(i).getTitle()).append(FilmService.DELIMITER)
                                        .append(filmListByGenre.get(i).getGenre()).append(FilmService.DELIMITER)
                                        .append(filmListByGenre.get(i).getFinalRating()).append(FilmService.DELIMITER)
                                        .append(filmListByGenre.get(i).getNumberOfRatings());
                            }
                        }

                        response = responseBuilder.toString();
                        break;
                    //Search films by recommend
                    case FilmService.SEARCH_BY_RECOMMEND:
                        List<Film> filmListByRecommend = filmManager.getRecommendedFilms();
                        //Check film list if empty
                        if (filmListByRecommend.isEmpty()) {
                            response = FilmService.NOT_MATCH_FOUND;
                            break;
                        }

                        responseBuilder.append(filmListByRecommend.get(0).getTitle()).append(FilmService.DELIMITER)
                                .append(filmListByRecommend.get(0).getGenre()).append(FilmService.DELIMITER)
                                .append(filmListByRecommend.get(0).getFinalRating()).append(FilmService.DELIMITER)
                                .append(filmListByRecommend.get(0).getNumberOfRatings());

                        if (filmListByRecommend.size() > 1) {
                            for (int i = 1; i < filmListByRecommend.size(); i++) {
                                responseBuilder.append(FilmService.GENRE_DELIMITER)
                                        .append(filmListByRecommend.get(i).getTitle()).append(FilmService.DELIMITER)
                                        .append(filmListByRecommend.get(i).getGenre()).append(FilmService.DELIMITER)
                                        .append(filmListByRecommend.get(i).getFinalRating()).append(FilmService.DELIMITER)
                                        .append(filmListByRecommend.get(i).getNumberOfRatings());
                            }
                        }

                        response = responseBuilder.toString();
                        break;

                    case FilmService.ADD:
                        //Check permission
                        if (!this.currentUser.getAdminStatus().equals("ADMIN")) {
                            response = FilmService.INSUFFICIENT_PERMISSIONS;
                            break;
                        }
                        //Check if film exists
                        if (filmManager.getFilmByTitle(component[1]) != null) {
                            response = FilmService.EXISTS;
                            break;
                        }

                        Film newFilm = new Film(component[1], component[2]);
                        boolean isAdded = filmManager.addFilm(newFilm);
                        if (isAdded) {
                            response = FilmService.ADDED;
                        }
                        break;

                    case FilmService.REMOVE:
                        //Check permission
                        if (!this.currentUser.getAdminStatus().equals("ADMIN")) {
                            response = FilmService.INSUFFICIENT_PERMISSIONS;
                            break;
                        }
                        //Check if film exists
                        if (filmManager.getFilmByTitle(component[1]) == null) {
                            response = FilmService.NOT_FOUND;
                            break;
                        }

                        boolean isDeleted = filmManager.deleteFilmByTitle(component[1]);
                        if (isDeleted) {
                            response = FilmService.REMOVED;
                        }
                        break;

                    case FilmService.EXIT:
                        response = FilmService.GOODBYE;
                        validation = false;
                        break;

                    case FilmService.SHUTDOWN:
                        //Check permission
                        if (!currentUser.getAdminStatus().equals("ADMIN")) {
                            response = FilmService.INSUFFICIENT_PERMISSIONS;
                            break;
                        }
                        response = FilmService.SHUT_DOWN;
                        this.currentUser = null;
                        validation = false;
                        FilmServer.serverSwitch = false;
                        break;

                    case FilmService.PREVIOUS:
                        response = FilmService.PREVIOUS;
                        break;

                    default:
                        System.out.println(FilmService.INVALID_REQUEST);
                        continue;
                }
                //Send the response
                output.println(response);
                output.flush();
            }
        } catch (IOException e) {
            System.out.println("An IOException occurred on data socket when communicating with " + dataSocket.getInetAddress());
            System.out.println(e.getMessage());
        }
    }}
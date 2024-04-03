package client;

import protocol.FilmService;

import java.util.Scanner;

public class ResponseHandler {
    public ResponseHandler() {

    }

    private void userLoggedMenu() {
        System.out.println("1) Rate a Film (rate)");
        System.out.println("2) Search a Film by name (searchByName)");
        System.out.println("3) Search a Film by genre (searchByGenre)");
        System.out.println("4) Logout");
    }

    private void adminLoggedMenu() {
        System.out.println("1) Add a Film (add)");
        System.out.println("2) Remove a Film (remove)");
        System.out.println("3) Logout");
        System.out.println("4) Shutdown");
    }

    public String handleUser(Scanner userInput) {
        boolean validation = true;
        String request = null;

        while (validation) {
            userLoggedMenu();
            String order = userInput.nextLine();

            switch (order) {
                case FilmService.RATE:
                    System.out.println("Film title:");
                    String title = userInput.nextLine();

                    System.out.println("Rate Film (0 - 10): ");
                    double rating = userInput.nextDouble();

                    userInput.nextLine();
                    request = FilmService.RATE + FilmService.DELIMITER + title + FilmService.DELIMITER + rating;
                    break;

                case FilmService.SEARCH_BY_NAME:
                    System.out.println("Film title:");
                    String searchTitle = userInput.nextLine();
                    request = FilmService.SEARCH_BY_NAME + FilmService.DELIMITER + searchTitle;
                    break;
                case FilmService.SEARCH_BY_GENRE:
                    System.out.println("Film genre: ");
                    String searchGenre = userInput.nextLine();
                    request = FilmService.SEARCH_BY_GENRE + FilmService.DELIMITER + searchGenre;
                    break;

                case FilmService.LOGOUT:
                    request = FilmService.LOGOUT;
                    break;

                case FilmService.ADD:

                case FilmService.REMOVE:

                case FilmService.SHUTDOWN:
                    System.out.println(FilmService.INSUFFICIENT_PERMISSIONS);
                    continue;
                default:
                    System.out.println(FilmService.INVALID_REQUEST);
                    continue;
            }
            // Step out of Loop
            validation = false;
        }
        return request;

    }

    public String handleAdmin(Scanner userInput) {
        boolean validation = true;
        String request = null;

        while (validation) {
            adminLoggedMenu();
            String order = userInput.nextLine();

            switch (order) {
                //case FilmService.ADDED:
                case FilmService.ADD:
                    System.out.println("Film title:");
                    String title = userInput.nextLine();

                    System.out.println("Film genre:");
                    String genre = userInput.nextLine();

                    request = FilmService.ADD + FilmService.DELIMITER + title + FilmService.DELIMITER + genre;
                    break;

                case FilmService.REMOVE:

                    System.out.println("Film title:");
                    String removeTitle = userInput.nextLine();

                    request = FilmService.REMOVE + FilmService.DELIMITER + removeTitle;
                    break;

                case FilmService.LOGOUT:
                    request = FilmService.LOGOUT;
                    break;

                case FilmService.SHUTDOWN:
                    request = FilmService.SHUTDOWN;
                    break;

                default:
                    System.out.println(FilmService.INVALID_REQUEST);
                    continue;
            }
            //Step out of Loop
            validation = false;
        }
        return request;
    }
}





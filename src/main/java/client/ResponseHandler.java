package client;

import protocol.FilmService;

import java.util.Scanner;

public class ResponseHandler {
    public ResponseHandler() {
    }

    private void userLoggedMenu() {
        System.out.println("1) Rate film (rate)");
        System.out.println("2) Search film(search)");
        System.out.println("3) Logout");
    }

    private void adminLoggedMenu() {
        System.out.println("1) Add a film (add)");
        System.out.println("2) Remove a film (remove)");
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
                    String title = userInput.nextLine().trim().toLowerCase();
                    System.out.println("0 - 10:");
                    double rating = userInput.nextDouble();
                    userInput.nextLine();
                    request = FilmService.RATE + FilmService.DELIMITER + title + FilmService.DELIMITER + rating;
                    break;

                case FilmService.SEARCH:
                    boolean validInputForSearch = true;
                    //Search condition
                    while (validInputForSearch) {
                        //Hint command for user
                        System.out.println("Please enter title or genre:");
                        String option = userInput.nextLine().trim().toLowerCase();
                        switch (option) {
                            case FilmService.TITLE:
                                System.out.println("Film title:");
                                String searchTitle = userInput.nextLine().trim().toLowerCase();
                                request = FilmService.SEARCH_BY_NAME + FilmService.DELIMITER + searchTitle;
                                validInputForSearch = false;
                                break;

                            case FilmService.GENRE:
                                System.out.println("Film genre:");
                                String searchGenre = userInput.nextLine().trim();
                                request = FilmService.SEARCH_BY_GENRE + FilmService.DELIMITER + searchGenre;
                                validInputForSearch = false;
                                break;

                            case FilmService.PREVIOUS:
                                request = FilmService.PREVIOUS;
                                validInputForSearch = false;
                                break;

                            default:
                                System.out.println("Please enter correct command. Enter 'previous' go back to previous menu.");
                                break;
                        }
                    }
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
            //Jump out from loop
            validation = false;
        }
        return request;
    }

    public String handleAdmin(Scanner userInput) {
        boolean validation = true;
        String request = null;

        while (validation) {
            adminLoggedMenu();
            String order = userInput.nextLine().trim();

            switch (order) {
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
            //Jump out from loop
            validation = false;
        }
        return request;
    }
}
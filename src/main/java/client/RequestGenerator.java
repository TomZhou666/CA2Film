package client;

import protocol.FilmService;

import java.util.Scanner;

public class RequestGenerator {

    public RequestGenerator(){

    }
    private  void displayMenu(){
        System.out.println("Welcome to Fqilm Centre !");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit");
    }

    public String authenticateRequest(Scanner userInput) {
        boolean validation = true;
        String request = null;

        while (validation) {

            displayMenu();
            String order = userInput.nextLine();

            switch (order) {
                //Login request
                case FilmService.LOGIN:
                    System.out.println("Enter username:");
                    String username = userInput.nextLine();

                    System.out.println("Enter password:");
                    String password = userInput.nextLine();

                    request = FilmService.LOGIN + FilmService.DELIMITER + username + FilmService.DELIMITER + password;
                    break;

                // Register request
                case FilmService.REGISTER:
                    System.out.println("Enter username:");
                    String newUsername = userInput.nextLine();

                    System.out.println("Enter password:");
                    String newPassword = userInput.nextLine();

                    request = FilmService.REGISTER +
                            FilmService.DELIMITER + newUsername +
                            FilmService.DELIMITER + newPassword;
                    break;
                // Register request
                case FilmService.EXIT:
                    request = FilmService.EXIT;
                    break;

                case FilmService.RATE:
                    System.out.println(FilmService.NOT_LOGGED_IN);

                    continue;

                    // Invalid request
                default:
                    System.out.println(FilmService.INVALID_REQUEST);

                    continue;
            }
            //Exit Loop
            validation = false;
        }
        return request;

    }


}

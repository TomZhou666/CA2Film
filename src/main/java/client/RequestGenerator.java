package client;

import protocol.FilmService;

import java.util.Scanner;

public class RequestGenerator {
    public RequestGenerator() {
    }

    private void displayMenu(){
        System.out.println("Welcome to Film centre!");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit");
    }

    public String authenticateRequest(Scanner userInput){
        boolean validation = true;
        String request = null;

        while (validation) {
            displayMenu();
            String order = userInput.nextLine().trim();

            switch (order){
                //Login
                case FilmService.LOGIN:
                    System.out.println("username:");
                    String username = userInput.nextLine().trim();
                    System.out.println("password:");
                    String password = userInput.nextLine().trim();
                    request = FilmService.LOGIN + FilmService.DELIMITER + username + FilmService.DELIMITER + password;
                    break;
                //Register
                case FilmService.REGISTER:
                    System.out.println("username:");
                    String newUsername = userInput.nextLine().trim();
                    System.out.println("password:");
                    String newPassword = userInput.nextLine().trim();
                    request = FilmService.REGISTER + FilmService.DELIMITER + newUsername + FilmService.DELIMITER + newPassword;
                    break;
                //Exit
                case FilmService.EXIT:
                    request = FilmService.EXIT;
                    break;

                case FilmService.RATE:
                    System.out.println("Please login first.");
                    continue;

                //Invalid request
                default:
                    System.out.println("Please enter correct command.");
                    continue;
            }
            //Jump out from loop
            validation = false;
        }

        return request;
    }
}

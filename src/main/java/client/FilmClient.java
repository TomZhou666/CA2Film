package client;

import protocol.FilmService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FilmClient {
    public static void main(String[] args) {
        //User scanner
        Scanner userInput = new Scanner(System.in);
        //Create request generator
        RequestGenerator generator = new RequestGenerator();
        //Create response handler
        ResponseHandler handler = new ResponseHandler();
        //Client socket
        try (Socket datasocket = new Socket(FilmService.HOST, FilmService.PORT)) {
            //Creating input scanner
            try (Scanner input = new Scanner(datasocket.getInputStream())) {
                //Output for sending message
                PrintWriter output = new PrintWriter(datasocket.getOutputStream());
                //Valid session
                boolean validSession = true;
                while (validSession) {
                    //Authenticate request message
                    String authentication = generator.authenticateRequest(userInput);
                    //Send authenticate to server
                    output.println(authentication);
                    //Flush authenticate
                    output.flush();
                    //Received response from server
                    String response = input.nextLine();
                    boolean validation = true;
                    String request;
                    //Handle response
                    switch (response) {
                        case FilmService.SUCCESS_USER:
                            while (validation) {
                                //User handler
                                request = handler.handleUser(userInput);
                                //Send request message to server
                                output.println(request);
                                //Flush request
                                output.flush();
                                //Get response
                                response = input.nextLine();
                                //Display response
                                switch (response){
                                    case FilmService.SUCCESS:
                                        System.out.println("Rate successfully!");
                                        break;

                                    case FilmService.INVALID_RATING_SUPPLIED:
                                        System.out.println("Please enter correct rating format.");
                                        break;

                                    case FilmService.INVALID_REQUEST:
                                        System.out.println("Please enter correct command.");
                                        break;

                                    case FilmService.NOT_LOGGED_IN:
                                        System.out.println("Please login first.");
                                        break;

                                    case FilmService.NOT_MATCH_FOUND:
                                        System.out.println("Film was not found. Please try again later.");
                                        break;

                                    case FilmService.INSUFFICIENT_PERMISSIONS:
                                        System.out.println("Insufficient permissions.");
                                        break;

                                    case FilmService.LOGGED_OUT:
                                        System.out.println("Logout successfully.");
                                        validation = false;
                                        break;

                                    case FilmService.PREVIOUS:
                                        break;

                                    default:
                                        String[] films = response.split(FilmService.GENRE_DELIMITER);
                                        for (String film : films) {
                                            String[] details = film.split(FilmService.DELIMITER);
                                                System.out.println("Title: " + details[0]);
                                                System.out.println("Genre: " + details[1]);
                                                System.out.println("Total Ratings: " + details[2]);
                                                System.out.println("Number of raters: " + details[3]);
                                                System.out.println();
                                        }
                                        break;
                                }
                            }
                            break;

                        case FilmService.SUCCESS_ADMIN:
                            while (validation) {
                                //Admin handler
                                request = handler.handleAdmin(userInput);
                                //Send request message to server
                                output.println(request);
                                //Flush request
                                output.flush();
                                //Get response
                                response = input.nextLine();
                                //Display response
                                switch (response){
                                    case FilmService.ADDED:
                                        System.out.println("Add film successfully!");
                                        break;

                                    case FilmService.EXISTS:
                                        System.out.println("Film has existed. Please try again later.");
                                        break;

                                    case FilmService.REMOVED:
                                        System.out.println("Remove film successfully.");
                                        break;

                                    case FilmService.NOT_FOUND:
                                        System.out.println("Film was not found. Please try again.");
                                        break;

                                    case FilmService.LOGGED_OUT:
                                        System.out.println("Logout successfully.");
                                        validation = false;
                                        break;

                                    case FilmService.SHUT_DOWN:
                                        System.out.println("Server has been shut down.");
                                        validation = false;
                                        validSession = false;
                                        break;

                                    default:
                                        System.out.println(response);
                                        break;
                                }
                            }
                            break;

                        case FilmService.ADDED:
                            System.out.println("Register successfully!");
                            break;

                        case FilmService.REJECTED:
                            System.out.println("Failed to register. Please try again later.");
                            break;

                        case FilmService.EXISTS:
                            System.out.println("Username doesn't exist or password wrong. Please try again later.");
                            break;

                        case FilmService.GOODBYE:
                            validSession = false;
                            System.out.println("Welcome to user next time! Goodbye!");
                            break;

                        default:
                            //Display response
                            System.out.println("Something went wrong. Please try again later.");
                            break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Host cannot be found, please try again.");
        } catch (IOException e) {
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }}
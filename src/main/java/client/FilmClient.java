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
                                System.out.println("Received from server: " + response);
                                //Check if logout
                                if (response.equals(FilmService.LOGGED_OUT)) {
                                    System.out.println(FilmService.LOGGED_OUT);
                                    validation = false;
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
                                System.out.println("Received from server: " + response);
                                //Check if logout
                                if (response.equals(FilmService.LOGGED_OUT)) {
                                    System.out.println(FilmService.LOGGED_OUT);
                                    validation = false;
                                }
                                if(response.equals(FilmService.SHUT_DOWN)){
                                    validation = false;
                                    validSession = false;
                                }
                            }
                            break;

                        case FilmService.GOODBYE:
                            validSession = false;
                            System.out.println(FilmService.GOODBYE);
                            break;

                        default:
                            //Display response
                            System.out.println("Received from server: " + response);
                            break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Host cannot be found, please try again.");
        } catch (IOException e) {
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }
}
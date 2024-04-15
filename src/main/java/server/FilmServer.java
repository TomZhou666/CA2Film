package server;

import protocol.FilmService;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class FilmServer {
    //Create server switch
    public static boolean serverSwitch = true;
    //Create data set
    private static final DataSet dataSet = new DataSet();
    public static void main(String[] args) {
        //Creating request handler
        RequestHandler handler = new RequestHandler();
        //Call data set
        dataSet.initialAdmin();
        dataSet.initialFilmData();
        //Generate socket listener
        try (ServerSocket listeningSocket = new ServerSocket(FilmService.PORT)){
            while (serverSwitch){
                Socket dataSocket = listeningSocket.accept();
                handler.handleRequest(dataSocket);
            }
        } catch (BindException e){
            System.out.println("BindException occurred when attempting to bind to port " + FilmService.PORT);
        } catch (IOException e) {
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }
}

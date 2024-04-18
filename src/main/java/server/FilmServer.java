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
        //Call data set
        dataSet.initialAdmin();
        dataSet.initialFilmData();
        //Generate socket listener
        try (ServerSocket listeningSocket = new ServerSocket(FilmService.PORT)){
            while (serverSwitch){
                //Create socket to listen request for client
                Socket dataSocket = listeningSocket.accept();
                //Create request handler
                RequestHandler handler = new RequestHandler(dataSocket);
                //Create thread
                Thread thread = new Thread(handler);
                thread.start();
            }
        } catch (BindException e){
            System.out.println("BindException occurred when attempting to bind to port " + FilmService.PORT);
        } catch (IOException e) {
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }
}
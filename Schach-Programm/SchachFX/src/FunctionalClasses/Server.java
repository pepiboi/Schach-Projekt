package FunctionalClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
     ServerSocket serverSocket;
     PrintStream streamToClient;
     BufferedReader streamFromClient;
     Socket clientSocket;
     int count = 0;

    public Server() {
        try{
            serverSocket = new ServerSocket(1234);

        } catch (IOException e) {
            System.out.println("Serversocket creating failed");
        }
    }


    public void connect() {
        try{
            while(true)
            {
                System.out.println("Server has been created");
                clientSocket = serverSocket.accept();
                System.out.println("After");
                /*count++;
                System.out.println("Client connection number "+count);*/
                System.out.println("accepted");
                streamFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                System.out.println("Reader");
                streamToClient = new PrintStream(clientSocket.getOutputStream());
                System.out.println("Writer");
                String str = streamFromClient.readLine();
                System.out.println("Reader from client");

                System.out.println("Client connection name "+str);
                streamToClient.println("Welcome "+str);
            }
        }
        catch(Exception e){
            System.out.println("Exception "+e);
        }
        finally{
            try{
                clientSocket.close();
            }
            catch(Exception e)
            {
                System.out.println("Server could not be closed");
            }
        }
    }

    public static void main(String[] args) {
        /*Server server = new Server();
        server.connect();*/
    }
}

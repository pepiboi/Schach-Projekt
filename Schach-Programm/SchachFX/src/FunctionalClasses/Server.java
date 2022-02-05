package FunctionalClasses;

import controller.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.management.LockInfo;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
     ServerSocket serverSocket;
     PrintStream streamToClient;
     BufferedReader streamFromClient;
     Socket clientSocket;
     int count = 0;
     String positionAndPaneFromClient;
     String clientConnectionNameOne;
     String clientConnectionNameTwo;
     public static boolean clientSentToServerHeIsConnected = false;

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
                if (count <= 1){
                    if (count == 0){
                        System.out.println("Server has been created");
                        clientSocket = serverSocket.accept();
                /*count++;
                System.out.println("Client connection number "+count);*/
                        System.out.println("accepted");
                        streamFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                        System.out.println("Reader");
                        streamToClient = new PrintStream(clientSocket.getOutputStream());
                        System.out.println("Writer");
                        String str = streamFromClient.readLine();
                        System.out.println(str);
                        System.out.println("Reader from client");
                        clientConnectionNameOne = str;
                        System.out.println("Client connection name "+str);
                        streamToClient.println("Welcome "+str);
                        count++;
                    }

                }else{
                    streamFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                    positionAndPaneFromClient = streamFromClient.readLine();
                    System.out.println(positionAndPaneFromClient);
                }
                String booleanTrue = "";
                boolean r = true;
                while (r == true){
                    System.out.println("warten bis streamFromClient");
                    booleanTrue = streamFromClient.readLine();
                    if (booleanTrue != ""){
                        if (LoginController.clientConnected == true){
                            System.out.println("in clientConnected = true");
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("boardView.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 1177, 1007);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            LoginController.serverStage.setResizable(false);
                            LoginController.serverStage.setTitle("Board");
                            LoginController.serverStage.setScene(scene);
                            LoginController.serverStage.show();
                        }
                        r = false;
                    }
                }

                if (booleanTrue.contains("boolean = true")){
                    try {
                        System.out.println("in Readline boolean = true");
                        clientSentToServerHeIsConnected = true;
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FunctionalClasses/boardView.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 1177, 1007);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage serverStage = LoginController.serverStage;
                                serverStage.setResizable(false);
                                serverStage.setTitle("ServerBoard");
                                serverStage.setScene(scene);
                                serverStage.show();
                            }
                        });

                    }catch (IllegalStateException ise){
                        ise.printStackTrace();
                    }
                }

                /*if (LoginController.clientConnected == true){
                    System.out.println("in clientConnected = true");
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("boardView.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 1177, 1007);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    LoginController.stage.setResizable(false);
                    LoginController.stage.setTitle("Board");
                    LoginController.stage.setScene(scene);
                    LoginController.stage.show();
                }*/

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

    /*public static void main(String[] args) {
        *//*Server server = new Server();
        server.connect();*//*
    }*/
}

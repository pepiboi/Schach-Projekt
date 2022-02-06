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
     public static boolean clientSentToServerHeIsConnected = false;
     public static String fromTo;
     public static String pane;
     public static String position;

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
                if (count <= 0){
                    if (count == 0){
                        System.out.println("Server has been created");
                        clientSocket = serverSocket.accept();
                /*count++;
                System.out.println("Client connection number "+count);*/
                        System.out.println("accepted");
                        streamFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                        streamToClient = new PrintStream(clientSocket.getOutputStream());
                        String str = streamFromClient.readLine();
                        System.out.println(str);
                        clientConnectionNameOne = str;
                        System.out.println("Client connection name "+str);
                        streamToClient.println("Welcome "+str);
                        System.out.println("Welcome");
                        count++;
                    }

                }else{
                    streamFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                    positionAndPaneFromClient = streamFromClient.readLine();
                    System.out.println(positionAndPaneFromClient);
                    System.out.println("else count >= 0");
                }
                String booleanTrue = "";
                boolean r = true;
                while (r == true){
                    System.out.println("warten bis streamFromClient");
                    booleanTrue = streamFromClient.readLine();
                    System.out.println("after booleanTrue = streamFromClient.readLine()");
                    if (booleanTrue != ""){
                        System.out.println(booleanTrue);
                        System.out.println("in if booleanTrue != ");
                        if (booleanTrue != ""){
                            System.out.println("in clientConnected = true");
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
                        }else{
                            fromTo = booleanTrue;
                            System.out.println(fromTo);
                            pane = streamFromClient.readLine();
                            System.out.println(pane);
                            position = streamFromClient.readLine();
                            System.out.println(position);

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

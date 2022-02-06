package FunctionalClasses;

import Pieces.Board;
import controller.BoardController;
import controller.LoginController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.management.LockInfo;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    PrintStream streamToClient;
    BufferedReader streamFromClient;
    Socket clientSocket;
    int count = 0;
    String positionAndPaneFromClient;
    String clientConnectionNameOne;
    public static boolean clientSentToServerHeIsConnected = false;
    public static String pane;
    public static String position;
    public static String fromTo;
    private int firstTimeOpening = 0;
    public static String pieceID;
    public static boolean pieceUebergeben = false;
    private boolean nodeSet = false;

    public Server() {
        try {
            serverSocket = new ServerSocket(1234);


        } catch (IOException e) {
            System.out.println("Serversocket creating failed");
        }
    }


    public void connect() {
        try {
            while (true) {
                if (count <= 0) {
                    if (count == 0) {
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
                        System.out.println("Client connection name " + str);
                        streamToClient.println("Welcome " + str);
                        System.out.println("Welcome");
                        count++;
                    }

                } else if (count == 1){
                    streamFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                    positionAndPaneFromClient = streamFromClient.readLine();
                    System.out.println(positionAndPaneFromClient);
                    count++;
                    //System.out.println("else count >= 0");
                }
                String booleanTrue = "";
                boolean r = true;
                while (r == true) {
                    //System.out.println("warten bis streamFromClient");
                    booleanTrue = positionAndPaneFromClient;
                    //System.out.println("after booleanTrue = streamFromClient.readLine()");
                    if (booleanTrue != "") {
                        /*System.out.println(booleanTrue);
                        System.out.println("in if booleanTrue != ");*/
                        if (booleanTrue != "" && firstTimeOpening == 0) {
                            firstTimeOpening++;
                            //System.out.println("in clientConnected = true");
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
                        } else {
                            System.out.println(booleanTrue);
                            fromTo = streamFromClient.readLine();
                            System.out.println(fromTo);
                            pane = streamFromClient.readLine();
                            System.out.println(pane);
                            position = streamFromClient.readLine();
                            System.out.println(position);
                            String[] columnRowArray = position.split(" ");
                            int column = Integer.parseInt(columnRowArray[0]);
                            int row = Integer.parseInt(columnRowArray[1]);
                            System.out.println(column + " " + row + " Parse completed");

                            pieceID = streamFromClient.readLine();
                            //NumberFormat
                            System.out.println(pieceID);
                            //set Pieces auf sache
                            Node pieceNode = null;
                            for (Node node : Board.gp.getChildren()) {
                                if (node == null){
                                    continue;
                                }
                                if (node.toString().contains(pieceID) && nodeSet == false) {
                                    pieceNode = node;
                                    pieceUebergeben = true;
                                    System.out.println("Node found!");
                                    nodeSet = true;
                                }
                            }
                            if (pieceNode == null) {
                                System.out.println("ID wurde nicht gefunden!");
                                pieceUebergeben = false;
                                nodeSet = false;
                            } else if(nodeSet == true){
                                GridPane.setColumnIndex(pieceNode, column);
                                GridPane.setRowIndex(pieceNode, row);
                                System.out.println("Node set to: " + column + " " + row);
                                pieceUebergeben = false;
                                nodeSet = false;
                            }else{
                                System.out.println("ID wurde nicht gefunden! --> Node set false");
                                pieceUebergeben = false;
                                nodeSet = false;
                            }


                        }
                        r = false;
                    }
                }

                /*if (booleanTrue.contains("boolean = true")){
                    try {
                        //System.out.println("in Readline boolean = true");
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
                }*/

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                System.out.println("Server could not be closed");
            }
        }
    }

    /*public static String parseID(String fromTo) {

        StringBuilder id = new StringBuilder();
        try {
            String[] parseArray = fromTo.split("=");
            System.out.println("parse = ");
            String parseComma = parseArray[1];
            String[] parseCommaArray = parseComma.split(",");
            System.out.println("parse ,");
            String idString = parseCommaArray[0];
            char[] idCharArray = idString.toCharArray();
            for (int i = 0; i < idCharArray.length; i++) {
                id.append(idCharArray[i]);
                System.out.print(idCharArray[i]);
            }
        *//*String[] parseArray = fromTo.split(" ");
        if (parseArray[1].contains("ImageView")){
            String[] parseEquals = parseArray[1].split("=");
            char[] idCharArray = parseEquals[1].toCharArray();
            for (int i = 0; i < idCharArray.length-1; i++) {
                id += idCharArray[i];
            }
        }*//*
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("IndexOutOfBoundsException");
        }
        return id.toString();
    }*/

    /*public static void main(String[] args) {
     *//*Server server = new Server();
        server.connect();*//*
    }*/
}

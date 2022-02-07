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
    public static PrintStream streamToClient;
    BufferedReader streamFromClient;
    Socket clientSocket;
    int count = 0;
    String positionAndPaneFromClient;
    String clientConnectionNameOne;
    //public static boolean clientSentToServerHeIsConnected = false;
    public static String pane;
    public static String position;
    public static String fromTo;
    private int firstTimeOpening = 0;
    public static String pieceID;
    public static boolean pieceUebergeben = false;
    private boolean nodeSet = false;
    public static String desti;
    static boolean move;
    public static boolean moveClient;

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

                } else if (count == 1) {
                    streamFromClient = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
                    positionAndPaneFromClient = streamFromClient.readLine();
                    System.out.println(positionAndPaneFromClient);
                    count++;
                    //System.out.println("else count >= 0");
                }
                String booleanTrue = "";
                boolean r = true;
                while (r == true) {
                    booleanTrue = positionAndPaneFromClient;
                    if (booleanTrue != "") {
                        if (booleanTrue != "" && firstTimeOpening == 0) {
                            firstTimeOpening++;
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
                                    BoardController.serverOrClient = "Server";
                                    System.out.println("BoardView geoeffnet");
                                }
                            });
                        } else {
                            String killMaybe = streamFromClient.readLine();
                            if (killMaybe.equals("kill")) {
                                System.out.println("kill");
                                fromTo = streamFromClient.readLine();
                                pane = streamFromClient.readLine();
                                if(pane.equals("death")){
                                    Platform.runLater(() -> {
                                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FunctionalClasses/endingViewLoser.fxml"));
                                        Stage stage = new Stage();
                                        Scene scene = null;
                                        try {
                                            scene = new Scene(fxmlLoader.load());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        stage.setResizable(false);
                                        stage.setTitle("Ending");
                                        stage.setScene(scene);
                                        stage.show();
                                        LoginController.serverStage.close();
                                    });
                                }
                                System.out.println(pane);
                                System.out.println(fromTo);
                                position = streamFromClient.readLine();
                                System.out.println(position);
                                String[] columnRowArray = position.split(" ");
                                int column = Integer.parseInt(columnRowArray[0]);
                                int row = Integer.parseInt(columnRowArray[1]);
                                System.out.println(column + " " + row + " Parse completed");

                                pieceID = streamFromClient.readLine();
                                //NumberFormat
                                System.out.println(pieceID);
                                desti = streamFromClient.readLine();
                                System.out.println(desti);
                                moveClient = Boolean.parseBoolean(streamFromClient.readLine());
                                //set Pieces auf sache
                                Node pieceNode = null;
                                for (Node node : Board.gp.getChildren()) {
                                    if (node == null) {
                                        continue;
                                    }
                                    if (node.toString().contains(pieceID) && nodeSet == false) {
                                        pieceNode = node;
                                        pieceUebergeben = true;
                                        System.out.println("Node found!");
                                        nodeSet = true;
                                    }
                                }
                                Node destiNode = null;
                                for (Node node : Board.gp.getChildren()) {
                                    if (node == null) {
                                        continue;
                                    }
                                    if (node.toString().contains(desti)) {
                                        node.setVisible(false);
                                        destiNode = node;
                                        pieceUebergeben = true;
                                        System.out.println("Node found!");
                                        nodeSet = true;
                                    }
                                }
                                if (pieceNode == null) {
                                    System.out.println("ID wurde nicht gefunden!");
                                    pieceUebergeben = false;
                                    nodeSet = false;
                                } else if (nodeSet == true) {
                                    /*GridPane.setColumnIndex(pieceNode, column);
                                    GridPane.setRowIndex(pieceNode, row);
                                    System.out.println("Node set to: " + column + " " + row);
                                    pieceUebergeben = false;
                                    nodeSet = false;*/
                                    //Nullpointer
                                    destiNode.setVisible(false);
                                    GridPane.setColumnIndex(pieceNode, column);
                                    GridPane.setRowIndex(pieceNode, row);
                                    System.out.println("Node set to: " + column + " " + row);
                                    pieceUebergeben = false;
                                    nodeSet = false;
                                    moveClient = false;
                                } else {
                                    System.out.println("ID wurde nicht gefunden! --> Node set false");
                                    pieceUebergeben = false;
                                    nodeSet = false;
                                }
                            }else {
                                fromTo = killMaybe.toString();
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
                                moveClient = Boolean.parseBoolean(streamFromClient.readLine());
                                //set Pieces auf sache
                                Node pieceNode = null;
                                for (Node node : Board.gp.getChildren()) {
                                    if (node == null) {
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
                                } else if (nodeSet == true) {
                                    GridPane.setColumnIndex(pieceNode, column);
                                    GridPane.setRowIndex(pieceNode, row);
                                    System.out.println("Node set to: " + column + " " + row);
                                    pieceUebergeben = false;
                                    nodeSet = false;
                                    moveClient = false;
                                } else {
                                    System.out.println("ID wurde nicht gefunden! --> Node set false");
                                    pieceUebergeben = false;
                                    nodeSet = false;
                                }

                            }
                        }
                        r = false;
                    }
                }

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

    public static void sendCurrentPositionS(String witchPane, String position, String id) {
        boolean running = true;
        while (running) {
            if (Board.somethingMoved == true) {
                System.out.println(Board.movedNodeToString);
                streamToClient.println(Board.movedNodeToString);
                streamToClient.println(witchPane);
                streamToClient.println(position);
                streamToClient.println(id);
                streamToClient.println(move);
                System.out.println("Pane went through");
                Board.somethingMoved = false;
                running = false;
                System.out.println("Running at sending Position set to false");
                move = true;
            }
        }
    }

    public static void sendCurrentPositionKillS(String witchPane, String position, String id, String destination) {
        boolean running = true;
        while (running) {
            if (Board.somethingMoved == true) {
                System.out.println(Board.movedNodeToString);
                streamToClient.println("kill");
                streamToClient.println(Board.movedNodeToString);
                streamToClient.println(witchPane);
                streamToClient.println(position);
                streamToClient.println(id);
                streamToClient.println(destination);
                streamToClient.println(move);

                System.out.println("Pane went through");
                Board.somethingMoved = false;
                running = false;
                System.out.println("Running at sending Position set to false");
                move = true;
            }
        }
    }
}

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static PrintStream streamToServer;
    static BufferedReader streamFromServer;
    static Socket toServer;
    private static boolean nodeSet;
    private static BufferedReader streamFromClient;
    ServerSocket serverSocket;
    PrintStream streamToClient;
    Socket clientSocket;
    public static int count = 0;
    public static String positionAndPaneFromClient;
    String clientConnectionNameOne;
    //public static boolean clientSentToServerHeIsConnected = false;
    public static String pane;
    public static String position;
    public static String fromTo;
    private int firstTimeOpening = 0;
    public static String pieceID;
    public static boolean pieceUebergeben = false;
    public static String desti;
    static boolean move;
    public static boolean moveServer;

    public Client() {
        connectionToServer();
    }

    public void connectionToServer() {
        String name;
        try {
            toServer = new Socket(LoginController.ipClient, 1234);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream(), true);
            System.out.println("Enter Connection Name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //name = reader.readLine();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(LoginController.ipClient.getBytes())));
            name = toServer.getRemoteSocketAddress().toString();
            //name = LoginController.ipAddressID.toString();
            streamToServer.println(name);
            String str = streamFromServer.readLine();
            System.out.println("The Server Says " + str);
            count++;

            reseaveFromServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendCurrentPosition(String witchPane, String position, String id) {
        boolean running = true;
        while (running) {
            if (Board.somethingMoved == true) {
                System.out.println(Board.movedNodeToString);
                streamToServer.println(Board.movedNodeToString);
                streamToServer.println(witchPane);
                streamToServer.println(position);
                streamToServer.println(id);
                streamToServer.println(move);
                //System.out.println(destination);
                System.out.println("Pane went through");
                Board.somethingMoved = false;
                running = false;
                System.out.println("Running at sending Position set to false");
                move = true;
            }
        }
    }

    public static void sendCurrentPositionKill(String witchPane, String position, String id, String destination) {
        boolean running = true;
        while (running) {
            if (Board.somethingMoved == true) {
                System.out.println(Board.movedNodeToString);
                streamToServer.println("kill");
                streamToServer.println(Board.movedNodeToString);
                streamToServer.println(witchPane);
                streamToServer.println(position);
                streamToServer.println(id);
                streamToServer.println(destination);
                streamToServer.println(move);
                System.out.println("Pane went through");
                Board.somethingMoved = false;
                running = false;
                System.out.println("Running at sending Position set to false");
                move = true;
            }
        }
    }

    public static void clientHasOpenedBoardView(boolean isConnected) {
        if (isConnected == true) {
                /*toServer = new Socket(LoginController.ipClient,1234);
                streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
                streamToServer = new PrintStream(toServer.getOutputStream(), true);*/
            streamToServer.println("boolean = " + isConnected);
            System.out.println("boolean = " + isConnected);

        } else {
            System.out.println("isConnected = false");
        }
    }

    public static void reseaveFromServer() {
        try {
            while (true) {
                String booleanTrue = "";
                boolean r = true;
                while (r == true) {
                    System.out.println("warten bis streamFromServer");
                    String killMaybe = "";

                    killMaybe = streamFromServer.readLine();
                    System.out.println("Before Kill");
                    System.out.println(killMaybe);


                    if (killMaybe.equals("kill")) {
                        System.out.println("kill");
                        fromTo = streamFromServer.readLine();
                        System.out.println(fromTo);
                        pane = streamFromServer.readLine();
                        System.out.println(pane);
                        position = streamFromServer.readLine();
                        System.out.println(position);
                        String[] columnRowArray = position.split(" ");
                        int column = Integer.parseInt(columnRowArray[0]);
                        int row = Integer.parseInt(columnRowArray[1]);
                        System.out.println(column + " " + row + " Parse completed");

                        pieceID = streamFromServer.readLine();
                        //NumberFormat
                        System.out.println(pieceID);
                        desti = streamFromServer.readLine();
                        System.out.println(desti);
                        moveServer = Boolean.parseBoolean(streamFromServer.readLine());
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
                            moveServer = false;
                        } else {
                            System.out.println("ID wurde nicht gefunden! --> Node set false");
                            pieceUebergeben = false;
                            nodeSet = false;
                        }
                    } else {
                        fromTo = killMaybe.toString();
                        System.out.println(fromTo);
                        pane = streamFromServer.readLine();
                        System.out.println(pane);
                        position = streamFromServer.readLine();
                        System.out.println(position);
                        String[] columnRowArray = position.split(" ");
                        int column = Integer.parseInt(columnRowArray[0]);
                        int row = Integer.parseInt(columnRowArray[1]);
                        System.out.println(column + " " + row + " Parse completed");

                        pieceID = streamFromServer.readLine();
                        //NumberFormat
                        System.out.println(pieceID);
                        moveServer = Boolean.parseBoolean(streamFromServer.readLine());
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
                            moveServer = false;
                        } else {
                            System.out.println("ID wurde nicht gefunden! --> Node set false");
                            pieceUebergeben = false;
                            nodeSet = false;
                        }
                    }
                    r = false;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

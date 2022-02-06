package FunctionalClasses;

import Pieces.Board;
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

public class Client {
    static PrintStream streamToServer;
    static BufferedReader streamFromServer;
    static Socket toServer;
    private static boolean nodeSet;
    private static BufferedReader streamFromClient;
    ServerSocket serverSocket;
    PrintStream streamToClient;
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
    public static String desti;

    public Client() {
        connectionToServer();
    }

    private void connectionToServer() {
        try {
            String name;
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


        } catch (Exception e) {
            System.out.println("Exception " + e);
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
                //System.out.println(destination);
                System.out.println("Pane went through");
                Board.somethingMoved = false;
                running = false;
                System.out.println("Running at sending Position set to false");
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

                System.out.println("Pane went through");
                Board.somethingMoved = false;
                running = false;
                System.out.println("Running at sending Position set to false");
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
            String booleanTrue = "";
            boolean r = true;
            while (r == true) {
                //System.out.println("warten bis streamFromClient");
                //System.out.println("after booleanTrue = streamFromClient.readLine()");
                if (booleanTrue != "") {
                    //System.out.println(booleanTrue);
                    System.out.println("in if booleanTrue != ");

                    String killMaybe = streamFromClient.readLine();
                    if (killMaybe.equals("kill")) {
                        System.out.println("kill");
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
                        desti = streamFromClient.readLine();
                        System.out.println(desti);

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
                            //GridPane.setColumnIndex(pieceNode, column);
                            GridPane.setRowIndex(pieceNode, row);
                            System.out.println("Node set to: " + column + " " + row);
                            pieceUebergeben = false;
                            nodeSet = false;
                            //Nullpointer
                            destiNode.setVisible(false);
                            GridPane.setColumnIndex(pieceNode, column);
                            GridPane.setRowIndex(pieceNode, row);
                            System.out.println("Node set to: " + column + " " + row);
                            pieceUebergeben = false;
                            nodeSet = false;

                        } else {
                            System.out.println("ID wurde nicht gefunden! --> Node set false");
                            pieceUebergeben = false;
                            nodeSet = false;
                        }
                    } else {
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
                        } else {
                            System.out.println("ID wurde nicht gefunden! --> Node set false");
                            pieceUebergeben = false;
                            nodeSet = false;
                        }

                    }

                    r = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //new Client();
    }
}

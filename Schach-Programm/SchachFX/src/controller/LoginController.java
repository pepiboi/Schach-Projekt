package controller;

import FunctionalClasses.Client;
import FunctionalClasses.Main;
import FunctionalClasses.MyServerThread;
import FunctionalClasses.Server;
import FunctionalClasses.Client;
import FunctionalClasses.Main;
import FunctionalClasses.Server;
import Pieces.ChessColor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public Button boardButton;
    public TextField ipAddressID;
    public static String ipClient;
    public static Stage serverStage;
    public static Stage clientStage;
    int boardCount;
    public static boolean clientConnected = false;

    public LoginController(Stage primaryStage) {
        this.serverStage = primaryStage;
        this.clientStage = primaryStage;
        boardCount = 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Server server = new Server();
        //Client.connectToServer();
    }

    public void onClick(ActionEvent actionEvent) {
        try {
            /*FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("boardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1177, 1007);*/
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FunctionalClasses/boardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1177, 1007);
            clientStage.setResizable(false);
            clientStage.setTitle("Board");
            clientStage.setScene(scene);
            clientStage.show();


            /*stage.setResizable(false);
            stage.setTitle("Board");
            stage.setScene(scene);
            stage.show();*/
        } catch (IOException e) {
            System.out.println("open View did not work");
            e.printStackTrace();
        }

    }

    public void onEnterIP(MouseEvent mouseEvent) {
        try {
            if (ipAddressID == null || ipAddressID.equals("null")) {
                System.out.println("IP darf nicht null sein");
            }
            ipClient = ipAddressID.getText();
            if (ipClient.equals("null") || ipClient.equals("") || ipClient.isEmpty()) {
                System.out.println("IP-Address wurde nicht eingegeben");
                ipClient = "localhost";
                System.out.println("IP wurde auf localhost gesetzt");
            }

        } catch (NullPointerException npe) {
            System.out.println("Nullpointer at IP-Eingabe");
        }

        /*FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("boardView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1177, 1007);*/
        System.out.println("Before Client");
        Client client = new Client();
        System.out.println("After Client");

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FunctionalClasses/boardView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1177, 1007);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientStage.setResizable(false);
        clientStage.setTitle("ClientBoard");
        clientStage.setScene(scene);
        clientStage.show();
        clientConnected = true;

        System.out.println("Before clientHasOpenedBoardView");
        client.clientHasOpenedBoardView(clientConnected);
        System.out.println("After clientHasOpenedBoardView");

    }

    public void onStartServer(MouseEvent mouseEvent) {
        MyServerThread mst = new MyServerThread();
        Thread t = new Thread(mst);
        t.start();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("waitingLoungeView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 650, 650);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverStage.setResizable(false);
        serverStage.setTitle("WaitingLounge");
        serverStage.setScene(scene);
        serverStage.show();
        boardCount++;
    }
}

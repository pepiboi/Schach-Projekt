package controller;

import FunctionalClasses.Client;
import FunctionalClasses.Main;
import FunctionalClasses.Server;
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
    Stage stage;

    public LoginController(Stage primaryStage) {
        this.stage = primaryStage;
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
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("boardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1177, 1007);
            stage.setResizable(false);
            stage.setTitle("Board");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("open View did not work");
        }

    }

    public void onEnterIP(MouseEvent mouseEvent) {
        ipClient = ipAddressID.getText();
        if (ipClient.equals("null") || ipClient.equals("") || ipClient.isEmpty()) {
            System.out.println("IP-Address wurde nicht eingegeben");
            ipClient = "localhost";
            System.out.println("IP wurde auf localhost gesetzt");
        }

        new Client();

    }

    public void onStartServer(MouseEvent mouseEvent) {
        Server server = new Server();
        server.connect();
    }
}

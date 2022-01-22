package controller;

import classes.Main;
import com.sun.corba.se.spi.activation.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public Button boardButton;

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
            Stage stage = new Stage();
            stage.setTitle("Board");
            stage.setScene(scene);
            stage.show();

            FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("loginView.fxml"));
            Scene scene2 = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage2 = new Stage();
            stage2.close();
        } catch (IOException e) {
            System.out.println("open View did not work");
        }

    }
}

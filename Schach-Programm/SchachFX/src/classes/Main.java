package classes;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage fxmlLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{
<<<<<<< HEAD
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginView.fxml"));
        fxmlLoader.setControllerFactory(call -> new LoginController(primaryStage));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
=======
        Server server = new Server();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("boardView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1177, 1007);
>>>>>>> connectivity
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        Button button = new Button();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

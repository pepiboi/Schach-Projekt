package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static FXMLLoader fxmlLoader;
    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader = new FXMLLoader(Main.class.getResource("loginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
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

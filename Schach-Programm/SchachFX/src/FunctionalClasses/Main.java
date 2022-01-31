package FunctionalClasses;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginView.fxml"));
        fxmlLoader.setControllerFactory(call -> new LoginController(primaryStage));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

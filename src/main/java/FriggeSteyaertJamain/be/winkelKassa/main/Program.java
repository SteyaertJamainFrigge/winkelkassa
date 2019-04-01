package FriggeSteyaertJamain.be.winkelKassa.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root, 600, 550);
        scene.getStylesheets().add("/style/css/flatterfx.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/examen.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/examen.fxml"));

        root.getStylesheets().add(getClass().getResource("/css/Test1.css").toExternalForm());

        primaryStage.setHeight(500);
        primaryStage.setWidth(1000);
        primaryStage.setTitle("AtomFX");
        Image applicationIcon = new Image(getClass().getResourceAsStream("/img/atom.jpg"));
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }




}

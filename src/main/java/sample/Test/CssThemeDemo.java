package sample.Test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;

public class CssThemeDemo extends Application {

    private final String theme1Url = getClass().getResource("/css/Test1.css").toExternalForm();
    private final String theme2Url = getClass().getResource("/css/theme2.css").toExternalForm();

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        final Scene scene = new Scene(root, 300, 250);
        System.out.println("scene stylesheets: " + scene.getStylesheets());
        scene.getStylesheets().add(theme1Url);
        System.out.println("scene stylesheets: " + scene.getStylesheets());

        final Button btn = new Button("Load Theme 1");
        btn.getStyleClass().add("buttonStyle");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scene.getStylesheets().remove(theme2Url);
                System.out.println("scene stylesheets on button 1 click: " + scene.getStylesheets());
                if(!scene.getStylesheets().contains(theme1Url)) scene.getStylesheets().add(theme1Url);
                System.out.println("scene stylesheets on button 1 click: " + scene.getStylesheets());
            }
        });

        final Button btn2 = new Button("Load Theme 2");
        btn2.getStyleClass().add("buttonStyle");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{scene.getStylesheets().remove("troll.css");}catch (Exception ex){System.out.println(ex.getMessage());}
                scene.getStylesheets().remove(theme1Url);
                System.out.println("scene stylesheets on button 2 click: " + scene.getStylesheets());
                if(!scene.getStylesheets().contains(theme2Url)) scene.getStylesheets().add(theme2Url);
                System.out.println("scene stylesheets on button 2 click: " + scene.getStylesheets());
            }
        });

        ComboBox<String> comboBox = new ComboBox<String>(FXCollections.observableArrayList("Just", "another", "control"));
        root.getChildren().add(VBoxBuilder.create().spacing(10).children(btn, btn2, comboBox).build());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


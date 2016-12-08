package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.controllers.ExaminationsController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();


            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
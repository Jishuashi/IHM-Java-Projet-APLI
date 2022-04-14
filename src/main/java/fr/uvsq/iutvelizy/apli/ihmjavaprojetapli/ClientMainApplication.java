package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox vBoxMain = new VBox();
        Scene scene = new Scene(vBoxMain, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.Member;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.ModelManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view.ViewManager;
import javafx.application.Application;
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

    public static void main(String[] args) throws IOException {
        //launch();
        ModelManager model = ModelManager.getInstance();
        ViewManager view = ViewManager.getInstance();
        ControlerManager controler = ControlerManager.getInstance();
    }
}
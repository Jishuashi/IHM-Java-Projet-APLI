package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.ModelManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view.ViewManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMainApplication extends Application {
    public ViewManager view = ViewManager.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        HBox lRoot = view.getRoot();
        Scene scene = new Scene(lRoot, view.width, view.heigh);
        stage.setTitle(view.title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        ModelManager model = ModelManager.getInstance();
        ControlerManager controler = ControlerManager.getInstance();
        launch();
    }
}
package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.ModelManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view.ViewManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ClientMainApplication extends Application {
    public static ViewManager view = ViewManager.getInstance();
    private static Stage stage;
    private static HashMap<String, Scene> sceneHashMap;
    private static Scene currentScene;

    @Override
    public void start(Stage pStage) throws IOException {
        initViewScene();
        stage = pStage;
        switchScene("Menu");
    }

    public static void switchScene(String pView) {
        Scene scene = sceneHashMap.get(pView);
        stage.setTitle(view.title + pView);
        stage.setScene(scene);
        File css = new File("src/css" + File.separator + view.cssName);
        scene.getStylesheets().add(css.toURI().toString());
        stage.centerOnScreen();
        stage.show();
    }

    public static void initViewScene() throws IOException {
        sceneHashMap = new HashMap<>();

        Scene sceneSimu = new Scene(view.getRootSimulator() , view.width, view.heigh);
        Scene sceneMenu = new Scene(view.getRootMenu(), 700 , 557);
        Scene sceneEdi = new Scene(view.getRootEditor(), view.width, view.heigh);

        sceneHashMap.put("Editeur", sceneEdi);
        sceneHashMap.put("Simulateur", sceneSimu);
        sceneHashMap.put("Menu", sceneMenu);
    }


    public static void main(String[] args) throws IOException {
        ModelManager model = ModelManager.getInstance();
        ControlerManager controler = ControlerManager.getInstance();
        launch();
    }
}
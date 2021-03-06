package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.ClientMainApplication;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.ModelManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view.HBoxRootEditor;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view.ViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class ControlerManager implements EventHandler {
    private static ControlerManager instance;
    private ModelManager model;
    private ViewManager view;

    private ControlerManager() throws IOException {
        model = ModelManager.getInstance();
        view = ViewManager.getInstance();
    }

    public static ControlerManager getInstance() throws IOException{
        if(instance == null){
            instance = new ControlerManager();
        }

        return instance;
    }


    @Override
    public void handle(Event event) {
        if(event.getSource() instanceof MenuItem){
            if(((MenuItem) event.getSource()).getUserData() instanceof File) {
                File selectedFile = (File) ((MenuItem) event.getSource()).getUserData();

                try {
                    model.setScenarioFile(selectedFile);
                    model.updateModel();
                    view.updateViewSimulator(model.getContentScenario(), model.getPath(), model.getBestPath());
                } catch (Exception e){
                    System.out.println("Error : " + e);
                }
            } else {
                String action = (String) ((MenuItem) event.getSource()).getUserData();
                switch (action) {
                    case "Ouvrir" -> {
                        try {
                            model.openFile();
                        }
                        catch (Exception e){
                            System.out.println("Error : " + e);
                        }
                    }
                    case "Enrengistrer" -> {

                        try {
                            model.createFile(HBoxRootEditor.pathsToString(-1));
                        }catch (Exception e){
                            System.out.println("Error : " + e);
                        }

                    }
                    case "Menu principal" -> {
                        try {
                            ClientMainApplication.switchScene("Menu");
                        } catch (Exception e){
                            System.out.println("Error : " + e);
                        }
                    }
                    case "Simulateur" ->{
                        try {
                            ClientMainApplication.switchScene("Simulateur");
                            view.updateViewSimulator(model.getContentScenario(), model.getPath(), model.getBestPath());
                        } catch (Exception e){
                            System.out.println("Error");
                        }
                    }
                    case "Editeur" ->{
                        try {
                            ClientMainApplication.switchScene("Editeur");
                        } catch (Exception e){
                            System.out.println("Error : " + e);
                        }
                    }
                    default -> System.out.println("MenuItem Event");
                }
            }
        }

        if(event.getSource() instanceof Button){
            String buttonAction = ((Button) event.getSource()).getText();
            switch (buttonAction) {
                case "_Retour" -> {
                    try {
                        ClientMainApplication.switchScene("Menu");
                    } catch (Exception e){
                        System.out.println("Error : " + e);
                    }
                }
                case "_Simulateur de sc??narios" -> {
                    try {
                        ClientMainApplication.switchScene("Simulateur");
                        view.updateViewSimulator(model.getContentScenario(), model.getPath(), model.getBestPath());
                    } catch (Exception e){
                        System.out.println("Error : " + e);
                    }
                }
                case "_Nouveau Sc??nario" -> {
                    try {
                        ClientMainApplication.switchScene("Editeur");
                    } catch (Exception e){
                        System.out.println("Error : " + e);
                    }
                }
                default -> System.out.println("Button action");
            }
        }
    }

    public void updateSimulator(){
        view.updateViewSimulator(model.getContentScenario(), model.getPath(), model.getBestPath());
    }
}

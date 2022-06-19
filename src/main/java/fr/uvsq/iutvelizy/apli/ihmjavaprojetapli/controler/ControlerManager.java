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
                    view.updateViewSimulator(model.getContentScenario(), new ArrayList<>());
                } catch (Exception e){
                    System.out.println("Error");
                }
            } else {
                String action = (String) ((MenuItem) event.getSource()).getUserData();
                switch (action) {
                    case "Ouvrir" -> System.out.println("Open");
                    case "Enrengistrer" -> System.out.println(HBoxRootEditor.pathsToString(-1));
                    case "Exporter .csv" -> System.out.println("Export");
                    case "Menu principal" -> {
                        try {
                            ClientMainApplication.switchScene("menu");
                        } catch (Exception e){
                            System.out.println("Error");
                        }
                    }
                    case "Simulateur" ->{
                        try {
                            ClientMainApplication.switchScene("simulator");
                            view.updateViewSimulator(model.getContentScenario(), new ArrayList<>());
                        } catch (Exception e){
                            System.out.println("Error");
                        }
                    }
                    case "Editeur" ->{
                        try {
                            ClientMainApplication.switchScene("editor");
                        } catch (Exception e){
                            System.out.println("Error");
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
                        ClientMainApplication.switchScene("menu");
                    } catch (Exception e){
                        System.out.println("Error : " + e);
                    }
                }
                case "_Simulateur de scénarios" -> {
                    try {
                        ClientMainApplication.switchScene("simulator");
                        view.updateViewSimulator(model.getContentScenario(), new ArrayList<>());
                    } catch (Exception e){
                        System.out.println("Error : " + e);
                    }
                }
                case "_Nouveau Scénario" -> {
                    try {
                        ClientMainApplication.switchScene("editor");
                    } catch (Exception e){
                        System.out.println("Error : " + e);
                    }
                }
                default -> System.out.println("Button action");
            }
        }
    }
}

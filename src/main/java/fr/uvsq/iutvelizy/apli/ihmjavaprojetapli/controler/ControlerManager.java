package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.ClientMainApplication;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.Member;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.Scenario;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view.HBoxRootEditor;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view.ViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public final class ControlerManager implements EventHandler {
    private static ControlerManager instance;

    private ControlerManager(){

    }

    public static ControlerManager getInstance(){
        if(instance == null){
            instance = new ControlerManager();
        }

        return instance;
    }

    @Override
    public void handle(Event event) {
        if(event.getSource() instanceof MenuItem){
            if(((MenuItem) event.getSource()).getUserData() instanceof File){
                File selectedFile = (File) ((MenuItem) event.getSource()).getUserData();
                System.out.println(selectedFile.getName());
            } else {
                String action = (String) ((MenuItem) event.getSource()).getUserData();
                switch (action) {
                    case "Ouvrir" -> System.out.println("Open");
                    case "Enrengistrer" -> System.out.println(HBoxRootEditor.pathsToString(-1));
                    case "Exporter .csv" -> System.out.println("Export");
                    case "Menu principal" -> System.out.println("Main menu");
                    case "Simulateur" -> System.out.println("Simulator menu");
                    case "Editor" -> System.out.println("Editor menu");
                    default -> System.out.println("MenuItem Event");
                }
            }
        }

        if(event.getSource() instanceof Button){
            String buttonAction = ((Button) event.getSource()).getText();
            switch (buttonAction) {
                case "_Retour" -> {
                    System.out.println("Back");
                }
                case "_Simulateur de scénarios" -> System.out.println("Simulator menu");
                case "_Nouveau Scénario" -> System.out.println("Custom Scenarios menu");
                default -> System.out.println("Button action");
            }
        }
    }
}

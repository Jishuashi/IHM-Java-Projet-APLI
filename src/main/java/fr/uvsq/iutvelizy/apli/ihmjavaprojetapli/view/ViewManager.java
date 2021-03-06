package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public final class ViewManager {
    private static ViewManager instance;
    public int heigh = 560;
    public int width = 1280;
    public String title = "APLI - ";
    public String cssName = "simulator.css";


    private ViewManager(){

    }

    public static ViewManager getInstance(){
        if(instance == null){
            instance = new ViewManager();
        }

        return instance;
    }

    public void updateViewSimulator(ArrayList<String> fileContent, ArrayList<String> resultPaths, String bestPath){
           HBoxRootSimulator.fillFileResult(fileContent, resultPaths, bestPath);
    }

    public HBoxRootMenu getRootMenu() throws IOException{
        return HBoxRootMenu.getInstance();
    }

    public HBoxRootEditor getRootEditor() throws IOException{
        return HBoxRootEditor.getInstance();
    }

    public HBoxRootSimulator getRootSimulator() throws IOException {
        return HBoxRootSimulator.getInstance();
    }
}

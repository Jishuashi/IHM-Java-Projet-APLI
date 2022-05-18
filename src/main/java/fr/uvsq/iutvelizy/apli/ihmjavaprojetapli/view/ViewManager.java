package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.Scenario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public final class ViewManager {
    private static ViewManager instance;
    public int heigh = 720;
    public int width = 1280;
    public String title = "APLI";


    private ViewManager(){

    }

    public static ViewManager getInstance(){
        if(instance == null){
            instance = new ViewManager();
        }

        return instance;
    }

    public HBoxRoot getRoot() {
        return HBoxRoot.getInstance();

    }
}

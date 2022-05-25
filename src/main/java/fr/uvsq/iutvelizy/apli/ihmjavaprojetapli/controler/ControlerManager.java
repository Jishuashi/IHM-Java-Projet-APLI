package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.Member;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.Scenario;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public final class ControlerManager {
    private static ControlerManager instance;

    private ControlerManager(){

    }

    public static ControlerManager getInstance(){
        if(instance == null){
            instance = new ControlerManager();
        }

        return instance;
    }
}

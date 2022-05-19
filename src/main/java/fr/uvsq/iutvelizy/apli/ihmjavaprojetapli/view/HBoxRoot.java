package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public final class HBoxRoot extends HBox {
    private static HBoxRoot instance;

    private HBoxRoot(){
        Label hello = new Label("Bienvenue sur l'application de l'APLI");
        getChildren().add(hello);

    }

    protected static HBoxRoot getInstance() {

        if(instance == null){
            instance = new HBoxRoot();
        }

        return instance;
    }
}

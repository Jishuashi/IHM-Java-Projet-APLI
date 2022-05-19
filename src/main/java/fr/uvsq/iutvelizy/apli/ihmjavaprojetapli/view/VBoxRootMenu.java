package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class VBoxRootMenu extends VBox {
    private static VBoxRootMenu instance;

    private VBoxRootMenu(){
        //Ajout des labels ainsi que le leurs id pour le css
        Label lwelcomeTitle = new Label("Bienvenue dans l'application de l'APLI");
        lwelcomeTitle.setId("welcomeTitle");
        Label lsubTitle = new Label("Que voulez-vous faire ?");
        lsubTitle.setId("subTitle");
        getChildren().addAll(lwelcomeTitle,lsubTitle);
        //Ajout des boutons de navigation dans une HBox
        HBox lbuttonMenu = new HBox();

        Button lexistingScriptRoom = new Button("_Scénarios existant");
        lexistingScriptRoom.setId("existingButton");

        Button lnewScriptRoom = new Button("_Nouveau Scénario");
        lnewScriptRoom.setId("newScriptRoom");

        lbuttonMenu.getChildren().addAll(lexistingScriptRoom,lnewScriptRoom);
        getChildren().add(lbuttonMenu);
    }

    protected static VBoxRootMenu getInstance() {

        if(instance == null){
            instance = new VBoxRootMenu();
        }

        return instance;
    }
}

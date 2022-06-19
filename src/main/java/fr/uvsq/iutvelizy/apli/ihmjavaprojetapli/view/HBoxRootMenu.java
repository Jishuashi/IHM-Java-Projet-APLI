package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class HBoxRootMenu extends HBox {
    private static HBoxRootMenu instance;

    private HBoxRootMenu() {
        VBox content = new VBox();
        VBox contentLabels = new VBox();

        //Ajout des labels ainsi que le leurs id pour le css
        Label lwelcomeTitle = new Label("Bienvenue dans l'application de l'APLI");
        lwelcomeTitle.setId("welcomeTitle");
        VBox.setMargin(contentLabels, new Insets(150,0,50,250));
        Label lsubTitle = new Label("Que voulez-vous faire ?");
        lsubTitle.setId("subTitle");
        VBox.setMargin(lwelcomeTitle, new Insets(0,0,25,0));
        contentLabels.getChildren().addAll(lwelcomeTitle,lsubTitle);

        content.getChildren().add(contentLabels);

        //Ajout des boutons de navigation dans une HBox
        HBox lbuttonMenu = new HBox();
        Button lexistingScriptRoom = new Button("_Simulateur de scénarios");
        lexistingScriptRoom.setId("existingButton");
        lexistingScriptRoom.setPrefSize(150,50);

        Button lnewScriptRoom = new Button("_Nouveau Scénario");
        lnewScriptRoom.setId("newScriptRoom");
        lnewScriptRoom.setPrefSize(150,50);

        HBox lseparator = new HBox();
        lseparator.getChildren().add(new Label("            "));
        VBox.setMargin(lbuttonMenu, new Insets(0,0,0,170));

        lbuttonMenu.getChildren().addAll(lexistingScriptRoom,lseparator,lnewScriptRoom);
        content.getChildren().add(lbuttonMenu);

        lexistingScriptRoom.setOnAction(ControlerManager.getInstance());
        lnewScriptRoom.setOnAction(ControlerManager.getInstance());

        getChildren().add(content);
    }

    protected static HBoxRootMenu getInstance() {

        if(instance == null){

            instance = new HBoxRootMenu();
        }

        return instance;
    }
}

package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;

public class HBoxRootEditor extends HBox implements InterfaceMenu {
    private static HBoxRootEditor instance;

    private HBoxRootEditor() {
        MenuBar menuBar = new MenuBar();
        Menu menuTest = new Menu();
        getChildren().add(menuBar);
    }
    protected static HBoxRootEditor getInstance() {

        if(instance == null){

            instance = new HBoxRootEditor();
        }

        return instance;
    }
}

package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceMenu;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfacePokemon;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class HBoxRootSimulator extends HBox implements InterfaceMenu, InterfacePokemon {
    private static HBoxRootSimulator instance;


    private HBoxRootSimulator() {
        super();
        //Ajout des composants des menus
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("_" + MENU_LABELS[0]);
        Menu menuScenarioFiles = new Menu("_" + MENU_LABELS[1]);
        ToggleGroup scenarioFilesToggleGroup = new ToggleGroup();
        Menu quickMenu = new Menu("_" + MENU_LABELS[2]);
        ToggleGroup quickMenuToggleGroup = new ToggleGroup();

        Button backButton = new Button("_" + "Retour");
        backButton.setPrefSize(80, 50);
        HBox hBoxBackButton = new HBox(backButton);
        VBox.setMargin(hBoxBackButton, new Insets(50, 0, 0, 0));

        //Ajout des menuItems des menu
        for (String fileMenuContent : FILE_MENU_CONTENT) {
            if(fileMenuContent.equals("Ouvrir") || fileMenuContent.equals("Quitter")) {
                MenuItem menuItem = new MenuItem("_" + fileMenuContent);
                menuItem.setUserData(fileMenuContent);
                menuFile.getItems().add(menuItem);
            }
        }

        String path = "src/main/resources/fr/uvsq/iutvelizy/apli";
        List<File> scenarios = new ArrayList<>();
        File scenario0 = new File(path + File.separator +"scenario_0.txt");
        scenarios.add(scenario0);
        File scenario1_1 = new File(path + File.separator +"scenario_1_1.txt");
        scenarios.add(scenario1_1);
        File scenario1_2 = new File(path + File.separator +"scenario_1_2.txt");
        scenarios.add(scenario1_2);
        File scenario2_1 = new File(path + File.separator +"scenario_2_1.txt");
        scenarios.add(scenario2_1);
        File scenario2_2 = new File(path + File.separator +"scenario_2_2.txt");
        scenarios.add(scenario2_2);

        for (File file : scenarios){
            RadioMenuItem menuItem = new RadioMenuItem(file.getName());
            menuItem.setUserData(file);
            menuItem.setSelected(file.equals(scenario0));
            menuItem.setToggleGroup(scenarioFilesToggleGroup);
            menuScenarioFiles.getItems().add(menuItem);
        }

        for (String quickMenuContent : QUICK_MENU_CONTENT) {
            RadioMenuItem menuItem = new RadioMenuItem("_" + quickMenuContent);
            menuItem.setUserData(quickMenuContent);
            menuItem.setSelected(quickMenuContent.equals(QUICK_MENU_CONTENT[1]));
            menuItem.setToggleGroup(quickMenuToggleGroup);
            quickMenu.getItems().add(menuItem);
        }

        //Ajout de la VBox qui contient tout le pannel de gauche
        VBox vBoxLeftPannel = new VBox();
        vBoxLeftPannel.setId("LeftPannel");

        //Ajout des composants du pannel de gauche
        //Ajout de la scrollPane des chemins custom et de ses composants
        ScrollPane scrollPaneCustomPath = new ScrollPane();
        scrollPaneCustomPath.setPrefViewportWidth(600);
        scrollPaneCustomPath.setPrefViewportHeight(320);

        //la VBox contenant les chemins et bouttons
        VBox vBoxCustomPath = new VBox();
        vBoxCustomPath.setId("ScrollContent");

        scrollPaneCustomPath.setContent(vBoxCustomPath);

        VBox vBoxCustomPathLabel = new VBox();
        vBoxCustomPathLabel.setId("ScrollContentAndLabel");

        Label scrollPaneLeftLabel = new Label("Apercu du fichier :");
        scrollPaneLeftLabel.setId("ScrollPaneLeftLabel");
        vBoxCustomPathLabel.getChildren().addAll(scrollPaneLeftLabel, scrollPaneCustomPath);

        vBoxLeftPannel.getChildren().addAll(menuBar, vBoxCustomPathLabel, hBoxBackButton);

        //Ajout de la VBox du pannel de droite
        VBox vBoxRightPannel = new VBox();

        //Ajout des compostants du pannel droit
        //Ajout de la scroll pane
        ScrollPane scrollPaneResult = new ScrollPane();
        scrollPaneResult.setPrefViewportWidth(550);
        scrollPaneResult.setPrefViewportHeight(320);
        VBox vBoxResultPath = new VBox();
        vBoxResultPath.setId("ScrollContent");

        scrollPaneResult.setContent(vBoxResultPath);

        Label scrollPaneRightLabel = new Label("Resultat :");
        scrollPaneRightLabel.setId("ScrollPaneRightLabel");

        VBox vBoxResultPathLabel = new VBox();
        vBoxResultPathLabel.setId("ScrollContentAndLabel");
        VBox.setMargin(vBoxResultPathLabel, new Insets(25, 0, 0, 0));
        vBoxResultPathLabel.getChildren().addAll(scrollPaneRightLabel, scrollPaneResult);

        vBoxRightPannel.getChildren().addAll(vBoxResultPathLabel);

        menuBar.getMenus().addAll(menuFile,menuScenarioFiles,quickMenu);

        Separator verticalSeparator = new Separator();
        verticalSeparator.setOrientation(Orientation.VERTICAL);
        getChildren().addAll(vBoxLeftPannel,verticalSeparator,vBoxRightPannel);

        //Ajout des évenements
        List<MenuItem>fileListItems = menuFile.getItems();
        List<MenuItem>scenarioFilesListItems = menuScenarioFiles.getItems();
        List<MenuItem>quickMenuContent = quickMenu.getItems();
        fileListItems.get(0).setOnAction(ControlerManager.getInstance());
        fileListItems.get(1).setOnAction(event -> Platform.exit());
        for (MenuItem scenarioFilesListItem : scenarioFilesListItems) {
            scenarioFilesListItem.setOnAction(ControlerManager.getInstance());
        }
        for (MenuItem menuItem : quickMenuContent) {
            menuItem.setOnAction(ControlerManager.getInstance());
        }
        backButton.setOnAction(ControlerManager.getInstance());
    }

    protected static HBoxRootSimulator getInstance() {

        if (instance == null) {

            instance = new HBoxRootSimulator();
        }

        return instance;
    }
}
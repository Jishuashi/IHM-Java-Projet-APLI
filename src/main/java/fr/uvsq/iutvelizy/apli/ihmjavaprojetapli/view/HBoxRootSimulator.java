package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceMenu;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfacePokemon;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class HBoxRootSimulator extends HBox implements InterfaceMenu, InterfacePokemon {
    private static HBoxRootSimulator instance;
        //la VBox contenant les chemins du fichier
        static VBox vBoxFileContent = new VBox();
        //La VBox contenant les resultat
        static VBox vBoxResultPath = new VBox();
        //Le TextField du meilleur résultat
        static TextArea bestResultArea = new TextArea();

    private HBoxRootSimulator() throws IOException {
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
        VBox.setMargin(hBoxBackButton, new Insets(50, 0, 0, 20));

        //Ajout des menuItems des menu
        for (String fileMenuContent : FILE_MENU_CONTENT) {
            if(fileMenuContent.equals("Ouvrir") || fileMenuContent.equals("Quitter")) {
                MenuItem menuItem = new MenuItem("_" + fileMenuContent);
                menuItem.setUserData(fileMenuContent);
                menuFile.getItems().add(menuItem);
            }
        }

        String path = "src/main/resources/fr/uvsq/iutvelizy/apli/scenario";
        File [] scenarioFiles = new File(path).listFiles();

        for (File file : scenarioFiles){
            String lName = file.getName();
            lName = lName.substring(1 , lName.indexOf("."));
            lName = lName.replaceFirst("_", " ");
            lName = lName.replaceFirst("_", ".");

            RadioMenuItem menuItem = new RadioMenuItem("_" + "S" + lName);
            menuItem.setUserData(file);
            menuItem.setSelected(file.getName().equals("scenario_0.txt"));
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
        vBoxFileContent.setId("ScrollContent");

        scrollPaneCustomPath.setContent(vBoxFileContent);

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
        scrollPaneResult.setPrefViewportWidth(1000);
        scrollPaneResult.setPrefViewportHeight(320);

        vBoxResultPath.setId("ScrollContent");

        scrollPaneResult.setContent(vBoxResultPath);

        Label scrollPaneRightLabel = new Label("Resultat :");
        Label scrolPaneRightLabelBest = new Label("Meilleur résultat :");
        Label scrolPaneRightLabelOthers = new Label("Autre résultats :");
        scrollPaneRightLabel.setId("ScrollPaneRightLabel");
        scrolPaneRightLabelBest.setId("ScrollPaneRightLabel");
        scrolPaneRightLabelOthers.setId("ScrollPaneRightLabel");

        VBox vBoxResultPathLabel = new VBox();
        vBoxResultPathLabel.setId("ScrollContentAndLabel");
        vBoxResultPathLabel.getChildren().addAll(scrollPaneRightLabel,scrolPaneRightLabelBest, bestResultArea, scrolPaneRightLabelOthers,scrollPaneResult);

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

    /**
     * remplie les deux VBox du contenue du fichier et du resultat
     * @param fileContent L'array contenant les lignes du fichier
     * @param resultPaths L'array contenant le resultat des chemins du fichier
     * @param pBestPath Le string qui contient le chemin le plus court
     */
    public static void fillFileResult(ArrayList<String> fileContent, ArrayList<String> resultPaths, String pBestPath){
        vBoxFileContent.getChildren().clear();
        vBoxResultPath.getChildren().clear();
        bestResultArea.clear();
        bestResultArea.setEditable(false);
        bestResultArea.setMaxHeight(20);

        for (String fileContentString : fileContent) {
            vBoxFileContent.getChildren().add(new Label(fileContentString));
        }

        for (String resultPathString : resultPaths) {
            vBoxResultPath.getChildren().add(new Label(resultPathString));
        }

        bestResultArea.setText(pBestPath);
    }

    protected static HBoxRootSimulator getInstance() throws IOException{

        if (instance == null) {

            instance = new HBoxRootSimulator();
        }

        return instance;
    }
}
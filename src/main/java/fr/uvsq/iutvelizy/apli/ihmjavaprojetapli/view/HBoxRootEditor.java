package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceCity;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceMenu;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class HBoxRootEditor extends HBox implements InterfaceMenu, InterfaceCity {
    private static HBoxRootEditor instance;
    private static List<ComboBox> comboBoxListStart = new ArrayList<ComboBox>();
    private static List<ComboBox> comboBoxListEnd = new ArrayList<ComboBox>();

    private HBoxRootEditor() {
        super();
        //Ajout des composants des menus
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("_" + MENU_LABELS[0]);
        Menu quickMenu = new Menu("_" + MENU_LABELS[2]);
        ToggleGroup quickMenuToggleGroup = new ToggleGroup();

        Button backButton = new Button( "_" + "Retour");
        backButton.setPrefSize(80,50);
        HBox hBoxBackButton = new HBox(backButton);
        VBox.setMargin(hBoxBackButton, new Insets(50, 0, 0, 0));

        //Ajout des menuItems des menu
        for (String fileMenuContent : FILE_MENU_CONTENT) {
            MenuItem menuItem = new MenuItem("_" + fileMenuContent);
            menuItem.setUserData(fileMenuContent);
            file.getItems().add(menuItem);
        }

        for (String quickMenuContent : QUICK_MENU_CONTENT) {
            RadioMenuItem menuItem = new RadioMenuItem("_" + quickMenuContent);
            menuItem.setUserData(quickMenuContent);
            menuItem.setSelected(quickMenuContent.equals(QUICK_MENU_CONTENT[2]));
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

        //Ajout du premier chemin et des bouttons dans la Scrollpane
        //HBox customPath = new HBox(new Label("1: "), new Label(" Nom de la ville de départ : "), comboStartingCity, new Label(" Nom de la ville d'arrivée : "), comboEndCity);
        Button addButton = new Button("_" + "Ajouter");
        Button removeButton = new Button("_" + "Supprimer");
        Button resetButton = new Button("_" + "Réinitialiser");

        HBox buttonsBox = new HBox(addButton,removeButton,resetButton);
        VBox.setMargin(buttonsBox, new Insets(10,0,0,0));
        vBoxCustomPath.getChildren().addAll(buttonsBox);
        addNewPath(vBoxCustomPath);


        Label scrollPaneLeftLabel = new Label("Apercu des chemins :");
        scrollPaneLeftLabel.setId("ScrollPaneLeftLabel");
        vBoxCustomPathLabel.getChildren().addAll(scrollPaneLeftLabel,scrollPaneCustomPath);

        vBoxLeftPannel.getChildren().addAll(menuBar,vBoxCustomPathLabel,hBoxBackButton);


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

        Label scrollPaneRightLabel = new Label("Résultat :");
        scrollPaneRightLabel.setId("ScrollPaneRightLabel");

        VBox vBoxResultPathLabel = new VBox();
        vBoxResultPathLabel.setId("ScrollContentAndLabel");
        VBox.setMargin(vBoxResultPathLabel, new Insets(25, 0, 0, 0));
        vBoxResultPathLabel.getChildren().addAll(scrollPaneRightLabel,scrollPaneResult);

        vBoxRightPannel.getChildren().addAll(vBoxResultPathLabel);

        //Ajout des Evenements aux boutons
        List<MenuItem>fileListItems = file.getItems();
        List<MenuItem>quickMenuContent = quickMenu.getItems();
        fileListItems.get(0).setOnAction(event -> System.out.println("open Event"));
        fileListItems.get(1).setOnAction(event -> System.out.println("save Event"));
        fileListItems.get(2).setOnAction(event -> System.out.println("export Event"));
        fileListItems.get(3).setOnAction(event -> Platform.exit());

        quickMenuContent.get(0).setOnAction(event -> System.out.println("main menu view Event"));
        quickMenuContent.get(1).setOnAction(event -> System.out.println("scenario view Event"));
        quickMenuContent.get(2).setOnAction(event -> System.out.println("custom scenario view Event"));

        addButton.setOnAction(event -> addNewPath(vBoxCustomPath));
        removeButton.setOnAction(event -> removeLastPath(vBoxCustomPath));
        resetButton.setOnAction(event -> resetPaths(vBoxCustomPath));

        backButton.setOnAction(event -> System.out.println("back Event"));

        menuBar.getMenus().addAll(file,quickMenu);

        Separator verticalSeparator = new Separator();
        verticalSeparator.setOrientation(Orientation.VERTICAL);
        getChildren().addAll(vBoxLeftPannel,verticalSeparator,vBoxRightPannel);
    }

    protected static HBoxRootEditor getInstance() {

        if(instance == null){

            instance = new HBoxRootEditor();
        }

        return instance;
    }

    private static ComboBox<String> fillComboBox(String[] strings){
        ComboBox<String> comboBox = new ComboBox<>();
        for (String comboString : CITY_NAME) {
            comboBox.getItems().addAll(comboString);
        }
        return comboBox;
    }

    private static void addNewPath(VBox addTo){
        ComboBox <String> comboStartingCity;
        comboStartingCity = fillComboBox(CITY_NAME);
        ComboBox <String> comboEndCity;
        comboEndCity = fillComboBox(CITY_NAME);
        comboBoxListStart.add(comboStartingCity);
        comboBoxListEnd.add(comboEndCity);
        HBox customPath = new HBox(new Label(comboBoxListStart.size() + ": "), new Label(" Nom de la ville de départ : "), comboStartingCity, new Label(" Nom de la ville d'arrivée : "), comboEndCity);
        addTo.getChildren().add(comboBoxListStart.size()-1,customPath);
    }

    private static void removeLastPath(VBox removeTo){
        removeTo.getChildren().remove(comboBoxListStart.size()-1);
        comboBoxListStart.remove(comboBoxListStart.size()-1);
        comboBoxListEnd.remove(comboBoxListEnd.size()-1);

    }

    private static void resetPaths(VBox resetTo){
        for (int i = comboBoxListStart.size()-1; i >= 0; i--) {
            removeLastPath(resetTo);
        }
        addNewPath(resetTo);
    }
}

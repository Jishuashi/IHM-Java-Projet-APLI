package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceCity;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceMenu;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.Mnemonic;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class HBoxRootEditor extends HBox implements InterfaceMenu, InterfaceCity {
    private static HBoxRootEditor instance;

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

        //Ajout du premier chemin et du boutton Add dans la Scrollpane
        ComboBox <String> comboStartingCity1;
        comboStartingCity1 = fillComboBox(CITY_NAME);
        ComboBox <String> comboEndCity1;
        comboEndCity1 = fillComboBox(CITY_NAME);
        HBox customPath = new HBox(new Label("1: "), new Label(" Nom de la ville de départ : "), comboStartingCity1, new Label(" Nom de la ville d'arrivée : "), comboEndCity1);
        Button addButton = new Button("_" + "Ajouter");

        vBoxCustomPath.getChildren().addAll(customPath,addButton);

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

        addButton.setOnAction(event -> System.out.println("add Path Event"));

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

    private ComboBox<String> fillComboBox(String [] strings){
        ComboBox<String> comboBox = new ComboBox<>();
        for (String comboString : CITY_NAME) {
            comboBox.getItems().addAll(comboString);
        }
        return comboBox;
    }
}

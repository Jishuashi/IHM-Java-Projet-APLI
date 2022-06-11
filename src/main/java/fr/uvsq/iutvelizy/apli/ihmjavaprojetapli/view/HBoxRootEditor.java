package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfacePokemon;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.InterfaceMenu;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public final class HBoxRootEditor extends HBox implements InterfaceMenu, InterfacePokemon {
    private static HBoxRootEditor instance;
    private static final List<ComboBox> comboBoxListStart = new ArrayList<ComboBox>();
    private static final List<ComboBox> comboBoxListEnd = new ArrayList<ComboBox>();

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

        //Ajout des bouttons dans la Scrollpane
        //HBox customPath = new HBox(new Label("1: "), new Label(" Nom de la ville de départ : "), comboStartingCity, new Label(" Nom de la ville d'arrivée : "), comboEndCity);
        Button addButton = new Button("_" + "Ajouter");
        Button removeButton = new Button("_" + "Supprimer");
        Button resetButton = new Button("_" + "Réinitialiser");

        HBox buttonsBox = new HBox(addButton,removeButton,resetButton);
        VBox.setMargin(buttonsBox, new Insets(10,0,0,0));
        vBoxCustomPath.getChildren().addAll(buttonsBox);


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

        Label scrollPaneRightLabel = new Label("Aperçu du fichier :");
        scrollPaneRightLabel.setId("ScrollPaneRightLabel");

        VBox vBoxResultPathLabel = new VBox();
        vBoxResultPathLabel.setId("ScrollContentAndLabel");
        VBox.setMargin(vBoxResultPathLabel, new Insets(25, 0, 0, 0));
        vBoxResultPathLabel.getChildren().addAll(scrollPaneRightLabel,scrollPaneResult);

        vBoxRightPannel.getChildren().addAll(vBoxResultPathLabel);

        //Ajout du premier chemin
        addNewPath(vBoxCustomPath,vBoxResultPath);

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

        addButton.setOnAction(event -> addNewPath(vBoxCustomPath,vBoxResultPath));
        removeButton.setOnAction(event -> removeLastPath(vBoxCustomPath,vBoxResultPath));
        resetButton.setOnAction(event -> resetPaths(vBoxCustomPath,vBoxResultPath));

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

    private static ComboBox<String> fillComboBox(String [] strings){
        ComboBox<String> comboBox = new ComboBox<>();
        for (String comboString : POKEMON_NAME) {
            comboBox.getItems().addAll(comboString);
        }
        return comboBox;
    }

    private static void addNewPath(VBox addTo, VBox updateAt){
        ComboBox <String> comboStartingCity;
        comboStartingCity = fillComboBox(POKEMON_NAME);
        comboStartingCity.getSelectionModel().selectFirst();
        comboStartingCity.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateResult(updateAt);
            }
        });

        ComboBox <String> comboEndCity;
        comboEndCity = fillComboBox(POKEMON_NAME);
        comboEndCity.getSelectionModel().selectLast();
        comboEndCity.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                updateResult(updateAt);
            }
        });

        comboBoxListStart.add(comboStartingCity);
        comboBoxListEnd.add(comboEndCity);

        HBox customPath = new HBox(new Label(comboBoxListStart.size() + ": "), new Label(" Nom de la ville de départ : "), comboStartingCity, new Label(" Nom de la ville d'arrivée : "), comboEndCity);
        addTo.getChildren().add(comboBoxListStart.size()-1,customPath);
        updateResult(updateAt);
    }

    private static void removeLastPath(VBox removeTo, VBox updateAt){
        removeTo.getChildren().remove(comboBoxListStart.size()-1);
        comboBoxListStart.remove(comboBoxListStart.size()-1);
        comboBoxListEnd.remove(comboBoxListEnd.size()-1);
        updateResult(updateAt);
    }

    private static void resetPaths(VBox resetTo, VBox updateAt){
        for (int i = comboBoxListStart.size()-1; i >= 0; i--) {
            removeLastPath(resetTo,updateAt);
        }
        addNewPath(resetTo,updateAt);
    }

    /*renvoie les données que l'utilisateur à sélectionner
    * dans la combo box à l'index donné (index < à 0 pour
    * retourner tout les chemins)*/
    public static List<String> pathsToString(int index){
        List<String> result = new ArrayList<String>();

        if(index < 0){
            for (int i = 0; i < comboBoxListStart.size(); i++) {
                result.add(comboBoxListStart.get(i).getSelectionModel().getSelectedItem() + " -> " + comboBoxListEnd.get(i).getSelectionModel().getSelectedItem());
            }
            return result;
        }
        result.add(comboBoxListStart.get(index).getSelectionModel().getSelectedItem() + " -> " + comboBoxListEnd.get(index).getSelectionModel().getSelectedItem());
        return result;
    }

    public static void updateResult(VBox resultBox){
        List<String> pathToString = pathsToString(-1);
        resultBox.getChildren().clear();
        for (String pathString : pathToString) {
            resultBox.getChildren().add(new Label(pathString));
        }
    }
}

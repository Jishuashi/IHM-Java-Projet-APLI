package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

public final class ViewManager {
    private static ViewManager instance;
    public int heigh = 720;
    public int width = 1280;
    public String title = "APLI - Menu";
    public String cssName = "simulator.css";


    private ViewManager(){

    }

    public static ViewManager getInstance(){
        if(instance == null){
            instance = new ViewManager();
        }

        return instance;
    }

    public HBoxRootMenu getRootMenu() {
        return HBoxRootMenu.getInstance();
    }

    public HBoxRootEditor getRootEditor() {
        return HBoxRootEditor.getInstance();
    }

    public HBoxRootSimulator getRootSumulator() {
        return HBoxRootSimulator.getInstance();
    }
}

package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

public final class ViewManager {
    private static ViewManager instance;
    public int heigh = 720;
    public int width = 1280;
    public String title = "APLI";


    private ViewManager(){

    }

    public static ViewManager getInstance(){
        if(instance == null){
            instance = new ViewManager();
        }

        return instance;
    }

    public HBoxRootMenu getRoot() {
        return HBoxRootMenu.getInstance();
    }
}

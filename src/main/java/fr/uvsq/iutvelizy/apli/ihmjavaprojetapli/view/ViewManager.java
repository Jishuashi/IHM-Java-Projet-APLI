package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.view;

public final class ViewManager {
    private static ViewManager instance;

    private ViewManager(){

    }

    public static ViewManager getInstance(){
        if(instance == null){
            instance = new ViewManager();
        }

        return instance;
    }
}

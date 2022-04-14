package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model;

public final class ModelManager {
    private static ModelManager instance;

    private ModelManager(){

    }

    public static ModelManager getInstance(){
        if(instance == null){
            instance = new ModelManager();
        }

        return instance;
    }

}

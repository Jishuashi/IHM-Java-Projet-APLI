package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler;

public final class ControlerManager {
    private static ControlerManager instance;

    private ControlerManager(){

    }

    public static ControlerManager getInstance(){
        if(instance == null){
            instance = new ControlerManager();
        }

        return instance;
    }


}

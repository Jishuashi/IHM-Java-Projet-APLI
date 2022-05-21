package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model;

import java.util.ArrayList;

public class Scenario {
    private ArrayList<String> buyerList;
    private  ArrayList<String> sellerList;
    private  ArrayList<String> listCity;


public Scenario(ArrayList<String> pListSeller, ArrayList<String> pListBuyer,ArrayList<String> pListCity){
            buyerList = pListBuyer;
            sellerList = pListSeller;
            listCity = pListCity;
    }


    public ArrayList<String> getBuyerList(){
        return buyerList;
    }

    public ArrayList<String> getSellerList(){
        return sellerList;
    }

    public ArrayList<String> getListCity(){
        return listCity;
    }
}

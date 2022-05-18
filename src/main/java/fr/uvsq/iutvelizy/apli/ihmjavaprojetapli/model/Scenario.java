package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model;

import java.util.ArrayList;

public class Scenario {
    private ArrayList<String> buyerList;
    private  ArrayList<String> sellerList;

    public Scenario(ArrayList<String> pListSeller, ArrayList<String> pListBuyer){
            buyerList = pListBuyer;
            sellerList = pListSeller;
    }


    public ArrayList<String> getBuyerList(){
        return buyerList;
    }

    public ArrayList<String> getSellerList(){
        return sellerList;
    }
}

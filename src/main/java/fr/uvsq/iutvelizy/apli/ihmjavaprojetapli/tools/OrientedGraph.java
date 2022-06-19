package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.tools;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model.Scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class OrientedGraph {
    private int order;
    private int size;
    private int minDegree;
    private int maxDegree;


    private HashMap<String, ArrayList<String>> outNeighbour;
    private ArrayList<Integer> inDegrees;
    private ArrayList<String> nodes;


    /**
     * Construit un Graphe Orienté
     * @param pOutNeighbour Liste des voisins sortant
     * @param pOutDegree Liste des degrées Sortant
     */
    public OrientedGraph(HashMap<String, ArrayList<String>>  pOutNeighbour, ArrayList<Integer> pInDegrees,ArrayList<String> pNodes){
        nodes = pNodes;
        order = nodes.size();
        inDegrees = pInDegrees;
        outNeighbour = pOutNeighbour;


        int lMaxDeg = 0;
        int lMinDeg = 99999;

        for (int i = 0; i < inDegrees.size(); i++) {
            size += pOutNeighbour.get(i).size();

            if (lMaxDeg < pOutNeighbour.get(i).size()){
                lMaxDeg =pOutNeighbour.get(i).size();
            }


            if(lMinDeg > pOutNeighbour.get(i).size()){
                lMinDeg = pOutNeighbour.get(i).size();
            }
        }

        minDegree = lMinDeg;
        maxDegree = lMaxDeg;
    }

    public String toString(){
            return ("Voici le Garph \n" + "Ordre :  " + order + "\nTaille : " + size + "\nDegrees Min : " + minDegree +"\nDegrees Max : " + maxDegree + "\nVoisins Sortant : " + outNeighbour  + "\nDegrees entrant : " + inDegrees + "\nNodes : " + nodes);
    }
    public static OrientedGraph createGraphOfScenario(Scenario pScenario){
        ArrayList<String> lBuyerList = pScenario.getBuyerList();
        ArrayList<String> lSellerList = pScenario.getSellerList();
        ArrayList<String> lNodes =new ArrayList<>();
        HashMap<String, ArrayList<String>>lOutNeighbour = new HashMap<>();
        ArrayList<Integer> lOutDegree = new ArrayList<>();
        OrientedGraph lGraphReturn;

        for (int i = 0; i < lSellerList.size(); i++){
            if(lOutNeighbour.get(lSellerList.get(i)) == null){
                ArrayList<String> lTempList = new ArrayList<>();
                lTempList.add(lBuyerList.get(i));
                lOutNeighbour.put(lSellerList.get(i), lTempList);
            }
            else {
                lOutNeighbour.get(lSellerList.get(i)).add(lBuyerList.get(i));
            }

            if(!lNodes.contains(lSellerList.get(i))){
                lNodes.add(lSellerList.get(i));
            }
            if (!lNodes.contains(lBuyerList.get(i))) {
                lNodes.add(lBuyerList.get(i));
            }
        }

        for (int j = 0; j < lNodes.size(); j++) {
            if(lOutNeighbour.get(lNodes.get(j)).contains(lNodes));
        }


        lGraphReturn = new OrientedGraph(lOutNeighbour, lOutDegree, lNodes);
        return lGraphReturn;
    }
}

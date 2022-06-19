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


    private  ArrayList<ArrayList<Integer>> outNeighbour;
    private ArrayList<Integer> inDegrees;
    private ArrayList<String> nodes;


    /**
     * Construit un Graphe Orienté
     * @param pOutNeighbour Liste des voisins sortant
     * @param pInDegrees Liste des degrées Entrant
     */
    public OrientedGraph(ArrayList<ArrayList<Integer>>  pOutNeighbour, ArrayList<Integer> pInDegrees, ArrayList<String> pNodes){
        nodes = pNodes;
        order = nodes.size();
        inDegrees = pInDegrees;
        outNeighbour = pOutNeighbour;


        int lMaxDeg = 0;
        int lMinDeg = 99999;

        for (int i = 0; i < inDegrees.size(); i++) {
            size += inDegrees.get(i);

            if (lMaxDeg < inDegrees.get(i)){
                lMaxDeg =inDegrees.get(i);
            }


            if(lMinDeg > inDegrees.get(i)){
                lMinDeg = inDegrees.get(i);
            }
        }

        minDegree = lMinDeg;
        maxDegree = lMaxDeg;
    }


    public String toString(){
            return ("Voici le Garph \n" + "Ordre :  " + order + "\nTaille : " + size + "\nDegrees Min : " + minDegree +"\nDegrees Max : " + maxDegree + "\nVoisins Sortant : " + outNeighbour  + "\nDegrees entrant : " + inDegrees + "\nNodes : " + nodes);
    }
    public static OrientedGraph createGraphOfScenario(Scenario pScenario){
        int lIndex = 0;

        ArrayList<String> lBuyerList = pScenario.getBuyerList();
        ArrayList<String> lSellerList = pScenario.getSellerList();
        ArrayList<String> lNodes = new ArrayList<>();
        ArrayList<ArrayList<Integer>>lOutNeighbour = new ArrayList<>();
        ArrayList<Integer> lInDegrees = new ArrayList<>();
        OrientedGraph lGraphReturn;

        for (int i = 0; i < lSellerList.size(); i++){
            if(!lNodes.contains(lSellerList.get(i))){
                lNodes.add(lSellerList.get(i));
            }
            if (!lNodes.contains(lBuyerList.get(i))) {
                lNodes.add(lBuyerList.get(i));
            }
        }

        for (int l = 0; l < lNodes.size(); l++) {
            lOutNeighbour.add(new ArrayList<>());

            int lCount = 0;

            for (int m = 0; m < lBuyerList.size(); m++) {
                if(lNodes.get(l).equals(lBuyerList.get(m))){
                    lCount ++;
                }
            }

            lInDegrees.add(lCount);
        }


        for (int j = 0; j < lSellerList.size(); j++) {
            lOutNeighbour.get(lNodes.indexOf(lSellerList.get(j))).add(lNodes.indexOf(lBuyerList.get(j)));
        }

        lGraphReturn = new OrientedGraph(lOutNeighbour, lInDegrees, lNodes);
        return lGraphReturn;
    }

    /**
     * Retourbe une ArrayList des Degrées entrant
     * @return
     */
    public ArrayList<Integer> getInDegrees(){
        return inDegrees;
    }

    /**
     * Retourne une ArrayList de ArrayList de String des voisins sortant
     * @return une ArrayList de ArrayList de String des voisins sortant
     */
    public  ArrayList<ArrayList<Integer>> getOutNeighbour(){
        return outNeighbour;
    }

    /**
     * Retourne le degré min du Graph
     * @return le degré min du Graph
     */
    public int getMinDegree(){
        return minDegree;
    }

    /**
     * Retourne le degré max du Graph
     * @return le degré max du Graph
     */
    public int getMaxDegree(){
        return maxDegree;
    }

    /**
     * Retourne l'ordre du Graph
     * @return l'ordre du Graph
     */
    public int order(){
        return order;
    }

    /**
     * Retourne la taille du Graph
     * @return la taille du Graphx
     */
    public int size(){
        return size;
    }

    /**
     * Retourne la liste des Sommets
     * @return la liste des Sommets
     */
    public ArrayList<String> getNodes(){
        return nodes;
    }
}

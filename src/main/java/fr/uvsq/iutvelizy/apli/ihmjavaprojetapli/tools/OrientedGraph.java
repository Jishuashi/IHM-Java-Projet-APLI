package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.tools;

import java.util.HashMap;
import java.util.TreeSet;

public class OrientedGraph {
    private int order;
    private int size;
    private int minDegree;
    private int maxDegree;

    private String[ ][ ] outNeighbour;
    private HashMap<String, Integer> inDegree;
    private HashMap<String, Integer> outDegree;


    /**
     * Construit un Graphe Orienté
     * @param pOutNeighbour Liste des voisins sortant
     * @param pInDegree Liste des degrées entrant
     * @param pOutDegree Liste des degrées Sortant
     */
    public OrientedGraph(String[ ][ ]  pOutNeighbour, HashMap<String, Integer> pInDegree, HashMap<String, Integer> pOutDegree){
        order = pOutDegree.size();
        outDegree = pOutDegree;
        inDegree = pInDegree;
        outNeighbour = pOutNeighbour;


        int lMaxDeg = 0;
        int lMinDeg = 99999;

        for (int i = 0; i < pOutDegree.size(); i++) {
            size += pOutNeighbour[i].length;

            if (lMaxDeg < pOutNeighbour[i].length){
                lMaxDeg = pOutNeighbour[i].length;
            }


            if(lMinDeg > pOutNeighbour[i].length){
                lMinDeg = pOutNeighbour[i].length;
            }
        }

        minDegree = lMinDeg;
        maxDegree = lMaxDeg;
    }

    public String toString(){
            return ("Voici le Garph \n" + "Ordre :  " + order + "\nTaille : " + size + "\nDegrees Min : " + minDegree +"\nDegrees Max : " + maxDegree + "\nVoisins Sortant : " + outNeighbour + "\nDegrees entrant : " + inDegree + "\nDegrees sortant : " + outDegree);
    }


}

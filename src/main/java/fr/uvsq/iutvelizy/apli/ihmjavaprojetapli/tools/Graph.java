package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.tools;

import java.util.ArrayList;
import java.util.TreeSet;

public class Graph {
    private int order;
    private int size;
    private int minDegree;
    private int maxDegree;

    public ArrayList<ArrayList<Integer>> edges;
    public ArrayList<ArrayList<Integer>>adjacences;
    public String [ ] nodes;

    /**
     * Construit un Graph
     * @param pNodes Liste des sommets du Graph
     * @param pEdges Liste des arrêtes du Graph
     * @param pAdjacences Liste d'ajacences du Graph
     * */
    public Graph(String [ ]  pNodes, ArrayList<ArrayList<Integer>> pEdges, ArrayList<ArrayList<Integer>> pAdjacences) {
        edges = pEdges;
        adjacences = pAdjacences;
        nodes = pNodes;
        order = nodes.length;
        size = edges.size();

        int lDegMax = 0;
        int lDegMin = 9999;

        for (int i = 0; i < edges.size(); i++) {

            if (getDegreeOfNode(i)> lDegMax){
                lDegMax = getDegreeOfNode(i);
            }
            if (getDegreeOfNode(i) < lDegMin){
                lDegMin =  getDegreeOfNode(i);
            }
        }

        maxDegree = lDegMax;
        minDegree = lDegMin;
    }


    /**
     * Construit un Graph Complet à partir d'un nombre de sommet
     * @param pOrder Nombres de sommets
     * @return un Graph Complet
     */
    public static Graph buildCompleteGraph(int pOrder){
        String [ ] lNodes = new String[pOrder];
        ArrayList<ArrayList<Integer>> lAdjacencesList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lEdgesList= new ArrayList<>();

        for (int i = 0; i < pOrder; i++) {
            lAdjacencesList.add(new ArrayList<Integer>());

            for (int j = 0; j < pOrder; j++) {
                if(i != j){
                    lAdjacencesList.get(i).add(j);
                }
            }
        }

        for (int m = 0; m < pOrder; m++) {
            for (int n = 0; n < pOrder; n++) {
                if(m != n){
                    ArrayList<Integer> lTemptree = new ArrayList<>();
                    lTemptree.add(m);
                    lTemptree.add(n);

                    if(!lEdgesList.contains(lTemptree))
                    lEdgesList.add(lTemptree);
                }
            }
        }

        return new Graph(lNodes, lEdgesList, lAdjacencesList);
    }

    public String toString(){
        return ("Voici le Garph \n" + "Ordre :  " + order + "\nTaille : " + size + "\nDegrées Min : " + minDegree +"\nDegrées Max:" + maxDegree + "\nArrêtes : " + edges + "\nAdjacences : " + adjacences);
    }

    public int getMinDegree(){
        return minDegree;
    }

    public int getMaxDegree(){
        return maxDegree;
    }

    public int order(){
        return order;
    }

    public int size(){
        return size;
    }

    /**
     * Calcul le dregé d'un sommet du Graph
     * @param pNode Sommet à tester
     * @return (int) le dregé d'un sommet du Graph
     */
    public int getDegreeOfNode(Integer pNode){
        int lDegree = 0;

        for (int i = 0; i < edges.size(); i++) {
            if(edges.get(i).get(0) == pNode){
                lDegree ++;
            }
        }
        return lDegree;
    }
}

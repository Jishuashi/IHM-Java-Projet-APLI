package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Graph {
    private int order;
    private int size;
    private int minDegree;
    private int maxDegree;

    private Boolean isWeight = false;

    private ArrayList<ArrayList<Integer>> edges;
    private ArrayList<ArrayList<Integer>>adjacences;
    private ArrayList<String> nodes;
    private ArrayList<Integer> weightList;

    /**
     * Construit un Graph
     * @param pNodes Liste des sommets du Graph
     * @param pEdges Liste des arrêtes du Graph
     * @param pAdjacences Liste d'ajacences du Graph
     * */
    public Graph(ArrayList<String>  pNodes, ArrayList<ArrayList<Integer>> pEdges, ArrayList<ArrayList<Integer>> pAdjacences) {
        edges = pEdges;
        adjacences = pAdjacences;
        nodes = pNodes;
        order = nodes.size();
        size = edges.size() / 2 ;
        int lDegMax = 0;
        int lDegMin = 9999;

        for (int i = 0; i < nodes.size(); i++) {

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
        ArrayList<String> lNodes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lAdjacencesList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lEdgesList= new ArrayList<>();


        for (int l = 0; l < pOrder; l++) {
            lNodes.add(l + "");
        }



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


    /**
     * Affiche les caractéristiques du Graph
     * @return (String) les caractéristiques du Graph
     */
    public String toString(){
        if(isWeight){
            return ("Voici le Garph \n" + "Ordre :  " + order + "\nTaille : " + size + "\nDegrees Min : " + minDegree +"\nDegrees Max : " + maxDegree + "\nArretes : " + edges + "\nAdjacences : " + adjacences + "\nPonderation : " + isWeight);

        }
        else {
            return ("Voici le Garph \n" + "Ordre :  " + order + "\nTaille : " + size + "\nDegrees Min : " + minDegree +"\nDegrees Max : " + maxDegree + "\nArretes : " + edges + "\nAdjacences : " + adjacences);

        }
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
     * @return la taille du Graph
     */
    public int size(){
        return size;
    }

    /**
     * Retourne la liste des Sommets
     * @return la liste des Sommets
     */
    public ArrayList<String> nodes(){
        return nodes;
    }

    /**
     * Change la liste des Sommets
     * @param pNodes list des Sommets
     */
    public void setNodes(ArrayList<String> pNodes) {
        nodes = pNodes;
        order = nodes.size();
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

    /**
     * Podère les arrêtes du graphs selon une liste de pondération
     * @param pWeight liste de pondération
     */
    public void setWeightOfEdges(HashMap<String, HashMap<String, Integer>> pWeight){
        isWeight = true;
        weightList = new ArrayList<>();

        HashMap<String, HashMap<String, Integer>> lWeight = pWeight;

        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < order; j++) {
                for (int k = 0; k < order; k++) {
                    weightList.add(lWeight.get(nodes.get(j)).get(nodes.get(k)));
                }
            }
        }
    }

    public int getShortessPath(int pNode){
        int lNode = pNode;


        return 0;
    }
}

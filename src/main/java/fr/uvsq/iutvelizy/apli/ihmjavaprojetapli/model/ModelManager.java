package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.controler.ControlerManager;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.tools.OrientedGraph;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.SynchronousQueue;


public final class ModelManager {
    private static ModelManager instance;

    private ArrayList<String> listPath;
    public static HashMap<String, String> memberList;
    public static HashMap<String, HashMap<String, Integer>> distanceList;
    public  ArrayList<String> cityList;
    public static ArrayList<ArrayList<Integer>> allPath;
    public static String bestPath;
    public Scenario currentScenario;
    public  File scenarioFile = new File("src/main/resources/fr/uvsq/iutvelizy/apli" + File.separator +"scenario_0.txt");;


    /**
     * Initialise le Modèle
     * @throws IOException
     */
    private ModelManager() throws IOException{
        String lPath = "src/main/resources/fr/uvsq/iutvelizy/apli";
        File lMemberFile = new File(lPath + File.separator +"membres_APLI.txt");
        File lCityFile = new File(lPath + File.separator +"ville.txt");
        File lDistanceFile = new File(lPath + File.separator +"distances.txt");

        initDistance(lDistanceFile, lCityFile);
        initMember(lMemberFile);

        currentScenario = initScenario(scenarioFile);
        listPath = calcPath(currentScenario);
    }

    public void setScenarioFile(File pFile){
        scenarioFile = pFile;
    }

    public void updateModel() throws IOException{
        currentScenario = initScenario(scenarioFile);
        listPath = calcPath(currentScenario);
    }

    /**
     * Get the instance of ModelManager
     * @return instance
     * @throws IOException
     */
    public static ModelManager getInstance() throws IOException{
        if(instance == null){
            instance = new ModelManager();
        }

        return instance;
    }


    /**
     * Stock la liste des Membres dans un Tableau
     * @param pFile Prend un fichier txt en paramètre
     * @throws IOException
     */
    public void initMember(File pFile) throws IOException{
        BufferedReader bufferEnter  = new BufferedReader(new FileReader(pFile));
        String line;

        memberList = new HashMap<>();

        StringTokenizer tokenizer;

        do{
            line = bufferEnter.readLine();

            if(line != null){
                tokenizer = new StringTokenizer(line, " ");
                String member = tokenizer.nextToken();
                String city = tokenizer.nextToken();
                memberList.put(member, city);
            }
        }
        while (line != null);
    }

    /**
     * Stock la liste des Membres dans un Tableau
     * @param pFileCity Prend un fichier txt en paramètre des villes
     * @param pFileDistance Prend un fichier txt en paramètre des distances entres les villes
     * @throws IOException
     */
    public void initDistance(File pFileDistance, File pFileCity) throws IOException{
        BufferedReader lBufferEnterDistance  = new BufferedReader(new FileReader(pFileDistance));
        BufferedReader lBufferEnterCity = new BufferedReader(new FileReader(pFileCity));
        String lLineDistance;
        String lLineCity = lBufferEnterCity.readLine();

        ArrayList<String> lCityList = new ArrayList<>();

        StringTokenizer lTokenizerCity;
        StringTokenizer lTokenizerDistance;

        distanceList = new HashMap<>();
        cityList = new ArrayList<>();

        int i = 0;

        do{
            lLineDistance = lBufferEnterDistance.readLine();
            HashMap<String, Integer> lTempMap =  new HashMap<>();

            if(lLineDistance != null){
                i = 0;
                lTokenizerDistance = new StringTokenizer(lLineDistance, " ");
                lTokenizerCity = new StringTokenizer(lLineCity, " ");

                String lDistanceCity = lTokenizerDistance.nextToken();

                int lCountToken = lTokenizerCity.countTokens();

                while (lCountToken > i){
                    String lCityToDistance = lTokenizerCity.nextToken();
                    String lDistance = lTokenizerDistance.nextToken();

                    lTempMap.put(lCityToDistance, Integer.parseInt(lDistance));
                    distanceList.put(lDistanceCity, lTempMap);
                    i++;
                }
            }
        }
        while (lLineDistance != null);

        lTokenizerCity = new StringTokenizer(lLineCity, " ");
        int pNbCity = lTokenizerCity.countTokens();

        for (int j = 0; j < pNbCity; j++) {
            cityList.add(lTokenizerCity.nextToken());
        }


    }

    /**
     * Stock les differrent sénario
     * @param pFile prend en paramètre un fichier de scénario
     * @throws IOException
     */
    private Scenario initScenario(File pFile) throws IOException {
        ArrayList<String> lBuyerList;
        ArrayList<String> lSellerList;
        ArrayList<String> lCityList = new ArrayList<>();
        BufferedReader lBufferEnter  = new BufferedReader(new FileReader(pFile));
        String lLine;
        StringTokenizer lTokenizer;
        String lSeller = "";
        String lBuyer = "";

        lBuyerList = new ArrayList<>();
        lSellerList = new ArrayList<>();

        do{
            lLine = lBufferEnter.readLine();

            if(lLine != null){
                lTokenizer = new StringTokenizer(lLine, " ->");
                lSeller = lTokenizer.nextToken();
                lBuyer = lTokenizer.nextToken();

                String lSellerCity = memberList.get(lSeller);
                String lBuyerCity = memberList.get(lBuyer);

                if(!lCityList.contains(lSellerCity)) {
                    lCityList.add(lSellerCity);
                }
                if (!lCityList.contains(lBuyerCity)) {
                    lCityList.add(lBuyerCity);
                }

                lSellerList.add(lSeller);
                lBuyerList.add(lBuyer);
            }
        }
        while (lLine != null);
        return new Scenario(lSellerList, lBuyerList, lCityList);
    }


    /**
     * Calcul les chemins possibles
     * @param pScenario Prent un scenario en parametre
     * @return String du chemin empreinté
     */
    public ArrayList<String> calcPath(Scenario pScenario){
        Scenario lScenario = pScenario;
        ArrayList<String> lListPath;

        OrientedGraph lGraphScenario = OrientedGraph.createGraphOfScenario(lScenario);

        int order = lGraphScenario.order();

        allPath = new ArrayList<>();

        boolean[] isDiscover = new boolean[order];
        Stack<Integer> lPath = new Stack<>();
        System.out.println("\n" + lGraphScenario.getNodes());

        findAllPath(lGraphScenario, lPath, isDiscover, order);
        lListPath = new ArrayList<>();

        int pIndex = allPath.size();
        int lDistance = 0;
        String lPrevCity= "";

        int lDistanceMin = 99999999;

        if(pIndex > 30){
            pIndex = 30;
        }

        String lBestPath = "";

        for (int i = 0; i < pIndex; i++) {
            String lStr = "Velizy, ";
            lDistance = 0;

            for (int j = 0; j < allPath.get(i).size(); j++) {
                if (j == 0) {
                    lDistance += getDistance("Velizy", memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j))));
                } else {
                    lDistance += getDistance(lPrevCity, memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j))));
                }

                lStr += memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j))) + ", ";
                lPrevCity = memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j)));
            }


            lDistance += getDistance(lPrevCity, "Velizy");
            lStr += "Velizy // La Distance est de " + lDistance;
            if (lDistance < lDistanceMin) {
                lBestPath = lStr;
                lDistanceMin = lDistance;
            }
        }

        bestPath = lBestPath;
        int nb = 1;

        for (int i = 0; i < pIndex; i++) {
            String lStr = "Velizy, ";
            lDistance = 0;

            for (int j = 0; j < allPath.get(i).size(); j++) {
                if (j == 0) {
                    lDistance += getDistance("Velizy", memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j))));
                } else {
                    lDistance += getDistance(lPrevCity, memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j))));
                }

                lStr += memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j))) + ", ";
                lPrevCity = memberList.get(lGraphScenario.getNodes().get(allPath.get(i).get(j)));
            }

            lDistance += getDistance(lPrevCity, "Velizy");
            lStr += "Velizy // La Distance est de " + lDistance;

            if(!lStr.equals(bestPath)){
                lListPath.add(nb + ") " + lStr);
                nb++;
            }
        }
        return lListPath;
    }

    /**
     * Recupere tous les chemin en récursif du graph Orienté donnés
     * @param graph Le Graph
     * @param path le stack pour stcoker le chemin en récursif
     * @param isDiscover Tableau de Booleen vide
     * @param pOrder Ordre du graph
     */
    private static void findAllPath(OrientedGraph graph, Stack<Integer> path, boolean[] isDiscover, int pOrder) {
        ArrayList<String> lArrayPath = new ArrayList<>();
        // Fait pour tous les noeuds
        for (int v = 0; v < pOrder; v++) {
          //Fait que si les degres entrant sont égals à 0 et que
            if (graph.getInDegrees().get(v) == 0 && !isDiscover[v]) {
                //Pour chaque Noeud
                for (int u: graph.getOutNeighbour().get(v)) {
                    graph.getInDegrees().set(u, graph.getInDegrees().get(u) - 1);
                }
                // Ajoute la Node au path
                path.add(v);
                isDiscover[v] = true;

                // Appelle la fonction récursive
                findAllPath(graph, path, isDiscover, pOrder);
                // Reset les info de degrées pour la node actuelles
                for (int u: graph.getOutNeighbour().get(v)) {
                    graph.getInDegrees().set(u, graph.getInDegrees().get(u) + 1);
                }
                // Enlève le noeud
                path.pop();
                isDiscover[v] = false;
            }
        }

        if (path.size() == pOrder) {
            ArrayList<Integer> lTempList = new ArrayList<>();

            for (int m = 0; m < path.size(); m++) {
                lTempList.add(path.get(m));
            }

            allPath.add(lTempList);
        }
    }

    /**
     * Retourne la distance entre deux ville
     * @param pCityStart Ville de départ
     * @param pCityEnd Ville D'arrivé
     * @returnla distance entre deux ville
     */
    public int getDistance(String pCityStart, String pCityEnd){
        return distanceList.get(pCityStart).get(pCityEnd);
    }

    /**
     * Retourne une ArrayList des Transaction
     * @return une ArrayList des Transaction
     */
    public ArrayList<String> getContentScenario(){
        ArrayList<String> lListScenario = new ArrayList<>();
        String lStr = "";

        for (int i = 0; i < currentScenario.getBuyerList().size(); i++) {
            lStr = currentScenario.getSellerList().get(i) + "->" + currentScenario.getBuyerList().get(i);
            lListScenario.add(lStr);
        }

        return lListScenario;
    }

    public ArrayList<String> getPath(){
        return listPath;
    }

    public String getBestPath(){
        return bestPath;
    }


    public void createFile(ArrayList<String> pLines) throws IOException {
       FileChooser fileChooser = new FileChooser();
       File lFilePath = new File("file/");
       fileChooser.setInitialDirectory(lFilePath);
       fileChooser.setTitle("Enregister le scénario personalisé");
       fileChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Fichier texte", "*.txt")
       );

       File newScenario = fileChooser.showSaveDialog(null);

       newScenario.createNewFile();
       Path lPath = Paths.get(newScenario.getPath());
       Files.write(lPath, pLines, Charset.forName("UTF-8"));
    }

    public void openFile() throws IOException {
        File lFilePath = new File("file/");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisisez le scénario personalisé");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichier texte", "*.txt")
        );
        fileChooser.setInitialDirectory(lFilePath);
        File scenario = fileChooser.showOpenDialog(null);

        if (scenario != null) {
            scenarioFile = scenario;
            updateModel();
            ControlerManager.getInstance().updateSimulator();
        }
    }
}

package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model;

import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.tools.Graph;
import fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.tools.OrientedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public final class ModelManager {
    private static ModelManager instance;
    public  HashMap<String, String> memberList;
    public  HashMap<String, HashMap<String, Integer>> distanceList;
    public  ArrayList<Scenario> scenarioList = new ArrayList<>();
    public  ArrayList<String> cityList;

    /**
     * Initialise le Modèle
     * @throws IOException
     */
    private ModelManager() throws IOException{
        String lPath = "src/main/resources/fr/uvsq/iutvelizy/apli";
        File lMemberFile = new File(lPath + File.separator +"membres_APLI.txt");
        File lCityFile = new File(lPath + File.separator +"ville.txt");
        File lDistanceFile = new File(lPath + File.separator +"distances.txt");
        File lScenario0 = new File(lPath + File.separator +"scenario_0.txt");
        File lScenario1_1 = new File(lPath + File.separator +"scenario_1_1.txt");
        File lScenario1_2 = new File(lPath + File.separator +"scenario_1_2.txt");
        File lScenario2_1 = new File(lPath + File.separator +"scenario_2_1.txt");
        File lScenario2_2 = new File(lPath + File.separator +"scenario_2_2.txt");

        initDistance(lDistanceFile, lCityFile);
        initMember(lMemberFile);
        initScenario(lScenario0);
        initScenario(lScenario1_1);
        initScenario(lScenario1_2);
        initScenario(lScenario2_1);
        initScenario(lScenario2_2);


        calcPath(scenarioList.get(0));
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
                tokenizer = new StringTokenizer(line, " ->");
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
    private void initScenario(File pFile) throws IOException {
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
        scenarioList.add(new Scenario(lSellerList, lBuyerList, lCityList));
    }


    /**
     * Calcul les chemins possibles
     * @param pScenario Prent un scenario en parametre
     * @return String du chemin empreinté
     */
    public ArrayList<String> calcPath(Scenario pScenario){
        Scenario lScenario = pScenario;
        ArrayList<String> lListPath = new ArrayList<>();
        String lPath = "";

        OrientedGraph lGraphScenario = OrientedGraph.createGraphOfScenario(lScenario);
        System.out.println(lGraphScenario.toString());



        return lListPath;
    }

    /**
     * Retourne une HashMap double entrée de distance entre les ville pour une liste de ville donnée
     * @param plistCity liste de ville donnée
     * @return une HashMap double entrée de distance entre les ville
     */
    public HashMap<String, HashMap<String, Integer>> getDistanceFromList(ArrayList<String> plistCity) {
        HashMap<String, HashMap<String, Integer>> lDistanceList = new HashMap<>();
        ArrayList<String> lListcity = plistCity;

        for (int i = 0; i < lListcity.size(); i++) {
            HashMap<String, Integer> lDistanceCity = new HashMap<>();
            for (int j = 0; j < lListcity.size(); j++) {
                if(i !=j){
                   String lCity1 = lListcity.get(i);
                   String lCity2 = lListcity.get(j);

                    lDistanceCity.put(lCity2 , distanceList.get(lCity1).get(lCity2));
                    lDistanceList.put(lCity1, lDistanceCity);
                }
            }
        }

        return lDistanceList;
    }
}

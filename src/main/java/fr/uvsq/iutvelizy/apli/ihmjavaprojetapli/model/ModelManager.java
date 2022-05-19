package fr.uvsq.iutvelizy.apli.ihmjavaprojetapli.model;

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
    public  ArrayList<Scenario> scenarioList;
    public  int nbScenario = 5;


    /**
     * Initialise le Modèle
     * @throws IOException
     */
    private ModelManager() throws IOException{
        String path = "src/main/resources/fr/uvsq/iutvelizy/apli";
        File memberFile = new File(path + File.separator +"membres_APLI.txt");
        File cityFile = new File(path + File.separator +"ville.txt");
        File distanceFile = new File(path + File.separator +"distances.txt");
        File scenario0 = new File(path + File.separator +"scenario_0.txt");
        File scenario1_1 = new File(path + File.separator +"scenario_1_1.txt");
        File scenario1_2 = new File(path + File.separator +"scenario_1_2.txt");
        File scenario2_1 = new File(path + File.separator +"scenario_2_1.txt");
        File scenario2_2 = new File(path + File.separator +"scenario_2_2.txt");

        initDistance(distanceFile, cityFile);
        initMember(memberFile);
        initScenario(scenario0);
        initScenario(scenario1_1);
        initScenario(scenario1_2);
        initScenario(scenario2_1);
        initScenario(scenario2_2);


        for(int i = 0; i < scenarioList.size(); i++){
            System.out.println(scenarioList.get(i).getBuyerList());
        }
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
    }

    /**
     * Stock les differrent sénario
     * @param pFile prend en paramètre un fichier de scénario
     * @throws IOException
     */
    private void initScenario(File pFile) throws IOException {
        ArrayList<String> lBuyerList;
        ArrayList<String> lSellerList;
        BufferedReader lBufferEnter  = new BufferedReader(new FileReader(pFile));
        String lLine;
        StringTokenizer lTokenizer;
        String lSeller = "";
        String lBuyer = "";

        scenarioList = new ArrayList<>();
        lBuyerList = new ArrayList<>();
        lSellerList = new ArrayList<>();

        do{
            lLine = lBufferEnter.readLine();

            if(lLine != null){
                lTokenizer = new StringTokenizer(lLine, " ->");
                lSeller = lTokenizer.nextToken();
                lBuyer = lTokenizer.nextToken();
            }

            lSellerList.add(lSeller);
            lBuyerList.add(lBuyer);
        }
        while (lLine != null);

        scenarioList.add(new Scenario(lSellerList, lBuyerList));
    }


    /**
     * Calcul les chemins possibles
     * @param pScenario Prent un scenario en parametre
     * @return String du chemin empreinté
     */
    public static String   calcPath(Scenario pScenario){
        Scenario lScenario = pScenario;



        return "Yo";
    }



}

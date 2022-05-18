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
        BufferedReader bufferEnterDistance  = new BufferedReader(new FileReader(pFileDistance));
        BufferedReader bufferEnterCity = new BufferedReader(new FileReader(pFileCity));
        String lineDistance;
        String lineCity = bufferEnterCity.readLine();

        ArrayList<String> cityList = new ArrayList<>();

        StringTokenizer tokenizerCity;
        StringTokenizer tokenizerDistance;

        distanceList = new HashMap<>();

        int i = 0;

        do{
            lineDistance = bufferEnterDistance.readLine();
            HashMap<String, Integer> lTempMap =  new HashMap<>();

            if(lineDistance != null){
                i = 0;
                tokenizerDistance = new StringTokenizer(lineDistance, " ");
                tokenizerCity = new StringTokenizer(lineCity, " ");

                String distanceCity = tokenizerDistance.nextToken();

                int lCountToken = tokenizerCity.countTokens();

                while (lCountToken > i){
                    String lCityToDistance = tokenizerCity.nextToken();
                    String lDistance = tokenizerDistance.nextToken();

                    lTempMap.put(lCityToDistance, Integer.parseInt(lDistance));
                    distanceList.put(distanceCity, lTempMap);
                    i++;
                }
            }
        }
        while (lineDistance != null);
    }

    /**
     * Stock les differrent sénario
     * @param pFile prend en paramètre un fichier de scénario
     * @throws IOException
     */
    private void initScenario(File pFile) throws IOException {
        ArrayList<String> lBuyerList;
        ArrayList<String> lSellerList;
        scenarioList = new ArrayList<>();
        BufferedReader bufferEnter  = new BufferedReader(new FileReader(pFile));
        String line;

        StringTokenizer tokenizer;
        String seller = "";
        String buyer = "";

        lBuyerList = new ArrayList<>();
        lSellerList = new ArrayList<>();

        do{
            line = bufferEnter.readLine();

            if(line != null){
                tokenizer = new StringTokenizer(line, " ->");
                seller = tokenizer.nextToken();
                buyer = tokenizer.nextToken();
            }

            lSellerList.add(seller);
            lBuyerList.add(buyer);
        }
        while (line != null);

        System.out.println(lSellerList);
        System.out.println(lBuyerList);

        scenarioList.add(new Scenario(lSellerList, lBuyerList));
    }


    /**
     *
     * @param scenario
     * @return
     */
    public static String calcPath(Scenario scenario){
        return "";
    }



}

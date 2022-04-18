package main;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;




import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DataLoader {

//    // TODO while we can just use the csv hosted on bucknell's server, we could also
//    //  explore getting the data from the local resources folder
//    public void getCSVFromLocal() throws URISyntaxException, FileNotFoundException {
//
//        String dir = System.getProperty("user.dir");
//        System.out.println(dir);
//        dir += "/src/main/resouces/Pokemon.csv";
//
//        // Read in raw data from csv file
//        URL resource = getClass().getClassLoader().getResource("Pokemon.csv");
//        File file = new File(resource.toURI());
//
//        File file1 = new File(dir);
//
//        Scanner scnr = new Scanner(file1);
//
//
//
//        ArrayList<String> data = new ArrayList<>();
//        while(scnr.hasNextLine()){
//            System.out.println(scnr.nextLine());
//        }
//
//
//
//        // get id
//
//        // get name
//
//        // get moves
//
//        // get HP,Atk,Def,Spa,SpD,Spe
//    }




//    /**
//     * TODO: this only works with the old CSV, but our Pokemon class has now been updated.
//     *   This is now depreciated...
//     * @return
//     * @throws IOException
//     */
//    public ArrayList<Pokemon> getCSVFromOnline() throws IOException {
//        // get from online
//        URL url = new URL("http://eg.bucknell.edu/~wmw015/code/Pokemon.csv");
//        Scanner scnr = new Scanner(url.openStream());
//
//        // skip first header line
//        scnr.nextLine();
//
//        Scanner CSVScanner;
//
//        // list of Pokemon to be created from CSV
//        ArrayList<Pokemon> allPokemon = new ArrayList<>();
//
//        // loop over poke
//        while(scnr.hasNextLine()){
//            CSVScanner = new Scanner(scnr.nextLine());
//            CSVScanner.useDelimiter(",");
//
//
//            // for each poke
//            String id = CSVScanner.next();
//            String name = CSVScanner.next();
//            String move1 = CSVScanner.next();
//            String move2 = CSVScanner.next();
//            String move3 = CSVScanner.next();
//            String move4 = CSVScanner.next();
//
//            String HP_S = CSVScanner.next();
//            double HP = Double.parseDouble(HP_S);
//
//            String Atk_S = CSVScanner.next();
//            double Atk = Double.parseDouble(Atk_S);
//
//            String Def_S = CSVScanner.next();
//            double Def = Double.parseDouble(Def_S);
//
//            String SpA_S = CSVScanner.next();
//            double SpA = Double.parseDouble(SpA_S);
//
//            String SpD_S = CSVScanner.next();
//            double SpD = Double.parseDouble(SpD_S);
//
//            String Spe_S = CSVScanner.next();
//            double Spe = Double.parseDouble(Spe_S);
//
//
//            // TODO image url/uri string
//            String image = "CHANGEME";
//
//            ArrayList<Double> stats = new ArrayList<>();
//            stats.add(HP);
//            stats.add(Atk);
//            stats.add(Def);
//            stats.add(SpA);
//            stats.add(SpD);
//            stats.add(Spe);
//
//            ArrayList<String> moves = new ArrayList<>();
//            moves.add(move1);
//            moves.add(move2);
//            moves.add(move3);
//            moves.add(move4);
//
//            allPokemon.add(new Pokemon(id, name, image, stats, moves));
//
//        }
//        return allPokemon;
//    }


    public ArrayList<Pokemon> getTESTSETPokemon() throws IOException {
        // get from online, skip header line
        URL url = new URL("http://eg.bucknell.edu/~wmw015/code/TESTSET-Pokemon.csv");
        Scanner scnr = new Scanner(url.openStream());
        scnr.nextLine();

        Scanner CSVScanner;
        ArrayList<Pokemon> allPoke = new ArrayList<>();

        while(scnr.hasNextLine()){
            CSVScanner = new Scanner(scnr.nextLine());
            CSVScanner.useDelimiter(",");

            // get attributes
            // for each poke
            String id = CSVScanner.next();
            String name = CSVScanner.next();
            String image = CSVScanner.next();
            String HP_S = CSVScanner.next();
            double HP = Double.parseDouble(HP_S);

            String Atk_S = CSVScanner.next();
            double Atk = Double.parseDouble(Atk_S);

            String Def_S = CSVScanner.next();
            double Def = Double.parseDouble(Def_S);

            String SpA_S = CSVScanner.next();
            double SpA = Double.parseDouble(SpA_S);

            String SpD_S = CSVScanner.next();
            double SpD = Double.parseDouble(SpD_S);

            String Spe_S = CSVScanner.next();
            double Spe = Double.parseDouble(Spe_S);

            String move1 = CSVScanner.next();
            String move2 = CSVScanner.next();
            String move3 = CSVScanner.next();
            String move4 = CSVScanner.next();

            String Status = CSVScanner.next(); // this is ignored
            String type1 = CSVScanner.next();
            String type2 = CSVScanner.next();
            String item = CSVScanner.next();

            ArrayList<Double> stats = new ArrayList<>();
            stats.add(HP);
            stats.add(Atk);
            stats.add(Def);
            stats.add(SpA);
            stats.add(SpD);
            stats.add(Spe);

            ArrayList<String> moves = new ArrayList<>();
            moves.add(move1);
            moves.add(move2);
            moves.add(move3);
            moves.add(move4);

            ArrayList<String> types = new ArrayList<>();
            types.add(type1);
            types.add(type2);



            //(String id, String name, String image, ArrayList<Double> stats, ArrayList<String> moves, ArrayList<String> types, ArrayList<String> items){
            allPoke.add(new Pokemon(id, name, image, stats, moves, types, item));
        }

        return allPoke;
    }

    public HashMap<String,Move> getTESTSETMoves() throws IOException {
        HashMap<String,Move> allMoves = new HashMap<>();

        // get from online, skip header line
        URL url = new URL("http://eg.bucknell.edu/~wmw015/code/TESTSET-Moves.csv");
        Scanner scnr = new Scanner(url.openStream());
        scnr.nextLine();

        Scanner moveScanner;
        while(scnr.hasNextLine()){
            moveScanner = new Scanner(scnr.nextLine());
            moveScanner.useDelimiter(",");
            String name = moveScanner.next();
            String movestats = moveScanner.nextLine().substring(1);
            allMoves.put(name, new Move(name,movestats));
        }

        return allMoves;

    }
//
//    public ArrayList<String> getTESTSETItems() throws IOException{
//        // get from online, skip header line
//        URL url = new URL("http://eg.bucknell.edu/~wmw015/code/TESTSET-Items.csv");
//        Scanner scnr = new Scanner(url.openStream());
//        scnr.nextLine();
//    }
}

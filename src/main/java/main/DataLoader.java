package main;


import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataLoader {

    public ArrayList<Pokemon> getTESTSETPokemon() throws IOException {
        InputStream is = getClass().getResourceAsStream("/data/TESTSET-Pokemon.csv");
        Scanner scnr = new Scanner(is);
        // skip header line
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
            String bot_image = CSVScanner.next();
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
            String player_image = CSVScanner.next();

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

            allPoke.add(new Pokemon(id, name, bot_image, stats, moves, types, item,player_image));
        }

        return allPoke;
    }

    public HashMap<String,Move> getTESTSETMoves() throws IOException {
        HashMap<String,Move> allMoves = new HashMap<>();

        InputStream is = getClass().getResourceAsStream("/data/TESTSET-Moves.csv");
        Scanner scnr = new Scanner(is);
        // skip header line
        scnr.nextLine();

        Scanner moveScanner;
        while(scnr.hasNextLine()){
            moveScanner = new Scanner(scnr.nextLine());
            moveScanner.useDelimiter(",");
            String name = moveScanner.next();
            String movestats = moveScanner.nextLine().substring(1);
            allMoves.put(name, new Move(name,movestats));
        }

        //add switch move
        allMoves.put("Switch", new Move("Switch","0,0,0,0,0,0"));


        return allMoves;

    }

}

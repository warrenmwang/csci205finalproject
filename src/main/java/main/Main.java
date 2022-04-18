
package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        // test battle micro choice making
        testBattleMicro();


    }

    public static void testBattleMicro() throws IOException{
        // BattleMicro creates the PokemonInventory which creates the DataLoader
        BattleMicro bm = new BattleMicro();
        // after user chooses 3 out of 6 Pokemon, print out the user's team to see if
        // picking worked
        ArrayList<Pokemon> userTeam = bm.getUserTeam();
        ArrayList<Pokemon> botTeam = bm.getBotTeam();
        System.out.println("Printing Players' team: ");
        for(Pokemon p : userTeam){
            System.out.println(p);
        }
        System.out.println("Printing Bot's team:");
        for(Pokemon p : botTeam){
            System.out.println(p);
        }
    }

    public static void test2() throws IOException{
        //test attack
//        DataLoader data1 = new DataLoader();
//        PokemonInventory pokemonInventory= new PokemonInventory();
//        HashMap<String, Pokemon> pokemap = pokemonInventory.getAllPokemon();
//        HashMap<String,Move> movemap = null; // we won't use this
//        try {
//            movemap = data1.getTESTSETMoves();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ArrayList<Pokemon> Player1poke = new ArrayList<>();
//        Player1poke.add(pokemap.get("1"));
//        Player1poke.add(pokemap.get("2"));
//        Player player1 = new Player(Player1poke);
//
//        ArrayList<Pokemon> Player2poke = new ArrayList<>();
//        Player2poke.add(pokemap.get("3"));
//        Player2poke.add(pokemap.get("4"));
//        Player player2 = new Player(Player2poke);
//
//        /**
//         * Change this index to choose different move
//         */
//        String moveName = player1.getCurrPokemon().getMove(0);
//        Move move = movemap.get(moveName);
//        Move overHeat = movemap.get("overheat");
//
//        System.out.println("attacker:");
//        System.out.println(player1.getCurrPokemon());
//        System.out.println("defender:");
//        System.out.println(player2.getCurrPokemon());
//
//        System.out.println("\n\n");
//        System.out.println("move chosen: " + moveName);
//        System.out.println(movemap.get(moveName));
//
//        BattleMicro battleMicro = new BattleMicro();
//        battleMicro.Attack(player1,move,player2);
//
//        System.out.println("\n \n \n");
//        System.out.println("attacker:");
//        System.out.println(player1.getCurrPokemon());
//        System.out.println("defender");
//        System.out.println(player2.getCurrPokemon());
    }

//    // test the type mult matrix
//    public static void testTypeMatchup(){
//        MovesInventory m = null;
//        try {
//            m = new MovesInventory();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        System.out.println(m.damageParam("DRAGON", "DRAGON"));
//    }

//    public static void test1() {
//        // create a pokemon
//        ArrayList<Double> stats = new ArrayList<>();
//        stats.add(301);
//        stats.add(372);
//        stats.add(158);
//        stats.add(257);
//        stats.add(176);
//        stats.add(259);
//        ArrayList<String> moves = new ArrayList<>();
//        moves.add("Flamethrower");
//        moves.add("Earthquake");
//        moves.add("SkyUppercut");
//        moves.add("Thunder Punch");
//        Pokemon poke1 = new Pokemon(0, "Blaziken", "pass", stats, moves);
//        System.out.println("Printing one pokemon:\n" + poke1);
//
//        // create a team (same pokemon but with different id's and names)
//        ArrayList<Pokemon> team = new ArrayList<>();
//        Pokemon poke2 = new Pokemon(1, "Blaziken's Twin Brother", "pass", stats, moves);
//        Pokemon poke3 = new Pokemon(2, "Blaziken's Other Twin Brother", "pass", stats, moves);
//        team.add(poke1);
//        team.add(poke2);
//        team.add(poke3);
//
//        // create a player
//        Player player = new Player(team, 0);
//        System.out.println("Expect Blaziken: ");
//        System.out.println(player.getCurrPokemon()); // expect id = 0
//        // update player's current pokemon
//        player.switchCurrPokemon(1);
//        System.out.println("Expect Blaziken's Twin Brother: ");
//        System.out.println(player.getCurrPokemon()); // expect id = 1
//        player.switchCurrPokemon(2);
//        System.out.println("Expect Blaziken's Other Twin Brother: ");
//        System.out.println(player.getCurrPokemon()); // expect id = 2
//
//        PokemonInventory pokeInv = new PokemonInventory();
//        HashMap<String, Pokemon> test = pokeInv.getAllPokemon();
//        for(String name : pokeInv.getAllPokemon().keySet()){
//            System.out.println(test.get(name));
//        }
//        System.out.printf("tot pokemon: %d\n", test.size());
//
//        testTypeMatchup();
//
//        DataLoader data = new DataLoader();
//        try {
//            HashMap<String, Move> a = data.getTESTSETMoves();
//            for (Map.Entry<String, Move> entry : a.entrySet()) {
//                System.out.println(entry.getKey()+" : "+entry.getValue());
//            }
//        }catch(Exception e){
//            System.out.println("oops");
//        }
//    }
}


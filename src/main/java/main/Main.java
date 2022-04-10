
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        // create a pokemon
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

        PokemonInventory pokeInv = new PokemonInventory();
        HashMap<String, Pokemon> test = pokeInv.getAllPokemon();
        for(String name : pokeInv.getAllPokemon().keySet()){
            System.out.println(test.get(name));
        }
        System.out.printf("tot pokemon: %d\n", test.size());
    }
}

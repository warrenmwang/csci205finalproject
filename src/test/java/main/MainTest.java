package main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// technically nothing goes on in the Main class except for the game loop called in the battlemacro object

// but honestly you could just run arbitrary tests here
class MainTest {

    @Test
    void exampleTest() {
        assertEquals(1,1);
    }

    @Test
    void statusTest(){
//        ArrayList<Double> stats = new ArrayList<>();
//        stats.add(300.0);
//        stats.add(300.0);
//        stats.add(300.0);
//        stats.add(300.0);
//        stats.add(300.0);
//        Pokemon poke1 = new Pokemon("a", "test", "test", stats, )
        try {
            PokemonInventory pokeInv = new PokemonInventory();
            assertEquals("42", pokeInv.getPokemon("42").getID());
            ArrayList<Pokemon> team1 = new ArrayList<>();
            team1.add(pokeInv.getPokemon("42")); // toxic
            team1.add(pokeInv.getPokemon("64")); // burn
            team1.add(pokeInv.getPokemon("10")); // u turn
            Player player1 = new Player(team1);

            ArrayList<Pokemon> team2 = new ArrayList<>();
            team2.add(pokeInv.getPokemon("42")); // toxic
            team2.add(pokeInv.getPokemon("64")); // burn
            team2.add(pokeInv.getPokemon("10")); // u turn
            Player player2 = new Player(team2);

            BattleMicro bm = new BattleMicro();



            System.out.println(player2.getCurrPokemon());
            bm.Attack(player1, new Move("toxic", "0,0,0,0,0"), player2); // toxic
            bm.endTurnEffect(player1,player2); //
            bm.endTurnEffect(player1,player2);
            System.out.println(player2.getCurrPokemon());

        }catch(Exception e){

        }
    }

}
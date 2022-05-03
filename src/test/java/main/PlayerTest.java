package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private BattleMicro battleMicro;
    private Player player;



    @BeforeEach
    void setup(){
        try{
            battleMicro = new BattleMicro();
            ArrayList<Pokemon> playerPokes = new ArrayList<>();
            ArrayList<Pokemon> randomPokes = battleMicro.generateRandomTeam();
            playerPokes.add(randomPokes.get(0));
            playerPokes.add(randomPokes.get(1));
            playerPokes.add(randomPokes.get(2));

            player = new Player(playerPokes);

            


        } catch (Exception e){}
    }

    @Test
    void getCurrPokemon() {
        assertEquals(player.getPokemonTeam().get(0),player.getCurrPokemon());
    }

    @Test
    void switchCurrPokemon() {
        ArrayList<Pokemon> originPokeList = new ArrayList<>(player.getPokemonTeam());
        player.switchCurrPokemon(1);
        assertEquals(originPokeList.get(1),player.getCurrPokemon());
    }


    @Test
    void getPokemonTeam() {
        assertEquals(3,player.getPokemonTeam().size());
    }

    @Test
    void getNumberOfPokemon() {
        assertEquals(player.getPokemonTeam().size(),player.getNumberOfPokemon());
    }

    @Test
    void setForfeitStatus() {
        player.setForfeitStatus(true);
        assertEquals(true,player.getForfeitStatus());
    }



    @Test
    void getForfeitStatus() {
        assertEquals(false,player.getForfeitStatus());
    }
}
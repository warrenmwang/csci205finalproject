package main;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    @Test
    void getTESTSETPokemon() {

        try {
            // get arraylist of test pokemon
            DataLoader dataLoader = new DataLoader();
            ArrayList<Pokemon> pokemon = dataLoader.getTESTSETPokemon();
            // check that we have all 64 pokemon
            assertEquals(64, pokemon.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getTESTSETMoves() {
        try{
            DataLoader dataLoader = new DataLoader();
            HashMap<String,Move> moves = dataLoader.getTESTSETMoves();
            assertEquals(67,moves.size()); // 66 from csv, +1 for switch move

        }catch (Exception e){}
    }
}
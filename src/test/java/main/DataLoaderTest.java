package main;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    @Test
    void getTESTSETPokemon() {

        try {
            // get arraylist of test pokemon
            DataLoader dataLoader = new DataLoader();
            ArrayList<Pokemon> pokemon = dataLoader.getTESTSETPokemon();
            // check that we have all 26 pokemon
            assertEquals(64, pokemon.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getTESTSETMoves() {
    }
}
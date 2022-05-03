package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PokemonInventoryTest {

    private PokemonInventory pokemonInventory;

    @BeforeEach
    void setUp(){
        try {
            pokemonInventory = new PokemonInventory();
        } catch (Exception e) {
        }
    }

    @Test
    void getAllPokemon() {
        assertEquals(64,pokemonInventory.getAllPokemon().size());
        assertNotEquals(null, pokemonInventory.getAllPokemon());
    }

    @Test
    void getNumPokemon() {
        assertEquals(64, pokemonInventory.getNumPokemon());
    }

    @Test
    void getPokemon() {
        assertEquals("Blaziken", pokemonInventory.getPokemon("1").getName());
        assertEquals(301, pokemonInventory.getPokemon("1").getHP());

    }

}
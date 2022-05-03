package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovesInventoryTest {

    private MovesInventory movesInventory;

    @BeforeEach
    void setUp() {
        try {
            movesInventory = new MovesInventory();
        } catch(Exception e){}
    }

    @Test
    void initializeDamageParamTest() {
        // check that nested hashmap of movesinventory is not empty after the movesInventory is constructed
        assertNotEquals(0, movesInventory.getAllMultipliers().size());
    }

    /**
     * Test our damageParam values are inputted in correctly
     * (Doubled Checked Mult Matrix values by hand, should be good)
     */
    @Test
    void damageParamTest() {

        // check that damageParam is returning the right values (manually check some)
        assertEquals(0.0, movesInventory.damageParam("GROUND", "FLYING"));
        assertEquals(2.0, movesInventory.damageParam("ROCK", "ICE"));
        assertEquals(0.0, movesInventory.damageParam("GHOST", "NORMAL"));
        assertEquals(1.0, movesInventory.damageParam("GHOST", "NULL"));



    }
}
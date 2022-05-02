package main;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleMicroTest {


    @Test
    void checkTurn() {
    }

    @Test
    void attack() {
    }

    /**
     * Test to see if we can generate 6 Random Pokemon Consistently
     */
    @Test
    void generateRandomTeam() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // generate the 2 teams of 6 Pokemon randomly
        BattleMicro bm = new BattleMicro();
        bm.generateInitialPlayerRandomTeam();
        bm.generateInitialBotRandomTeam();

        // check both of the teams have 6 pokemon
        assertEquals(6, bm.getUserTeam().size());
        assertEquals(6, bm.getBotTeam().size());

        // check that there are NO null elements in both teams
        for(int i=0;i<6;i++){
            assertNotEquals(null, bm.getUserTeam().get(i));
            assertNotEquals(null, bm.getBotTeam().get(i));
        }
    }

    /**
     * Test to see if player can correctly select 3 Pokemon from the 6 Pokemon initially given to them
     */
    @Test
    void initPlayer() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        InputStream sysInOrig = System.in; // save System.in to be restored later

        // initialize the BattleMicro (initializes the PokemonInventory)
        // this generates the 6 random pokemon initially in the userTeam
        BattleMicro bm = new BattleMicro();
        bm.generateInitialPlayerRandomTeam();

        // now get the list of id's of the 6 random pokemon chosen for the player team
        ArrayList<Pokemon> userTeam = bm.getUserTeam();
        ArrayList<String> Ids_6 = new ArrayList<>();
        for(Pokemon p : userTeam){
            String id = p.getID();
            Ids_6.add(id);
        }

        // choose the first 3 ids for our testing
        // put the three ids into our string
        String newLine = System.getProperty("line.separator");
        String INPUT = "" + Ids_6.get(0) + newLine + Ids_6.get(1) + newLine + Ids_6.get(2) + newLine;

        // put our Simulate input string into a ByteArrayInputStream
        // then set System.in to be this ByteArrayInputStream that holds our simulated input
        ByteArrayInputStream in = new ByteArrayInputStream(INPUT.getBytes());
        System.setIn(in);

        // Call the function that prompts the user to choose 3 Pokemon
        bm.initPlayer();

        // Print out our teams to see picking worked
        userTeam = bm.getUserTeam();

        System.out.println("Printing Players' team: ");
        for(Pokemon p : userTeam){
            System.out.println(p);
        }

        // also assert that userTeam has only 3 Pokemon
        assertEquals(3, userTeam.size());

        System.setIn(sysInOrig); // restore System.in to original
    }

    /**
     * Test choosing 3 pokemon for the bot team
     */
    @Test
    void initBot() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // generate 6 random pokemon and check it is randomly generated with 6 pokemon first
        BattleMicro bm = new BattleMicro();
        bm.generateInitialBotRandomTeam();
        assertEquals(6, bm.getBotTeam().size());

        // randomly select 3 from the 6 (double random selection) to get final bot pokemon team
        bm.initBot();
        assertEquals(3, bm.getBotTeam().size());
    }
}
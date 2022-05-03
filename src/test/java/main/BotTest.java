package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    private Bot bot;
    private BattleMicro battleMicro;

    @BeforeEach
    void setup(){
        try {
            battleMicro = new BattleMicro();
            ArrayList<Pokemon> botPokes = new ArrayList<>();
            ArrayList<Pokemon> randomPokes = battleMicro.generateRandomTeam();
            botPokes.add(randomPokes.get(0));
            botPokes.add(randomPokes.get(1));
            botPokes.add(randomPokes.get(2));
            bot = new Bot(botPokes);
        }catch(Exception e){}
    }

    @Test
    void botchooseMove(){
        Move result = bot.botChooseMove(bot.getCurrPokemon());
        assertNotEquals(null, result);

    }


    @Test
    void getDifficultyTest() {
        assertEquals(1,bot.getDifficulty());
    }

    @Test
    void setDifficultyTest() {
        bot.setDifficulty(2);
        assertEquals(2,bot.getDifficulty());
    }

    @Test
    void isOneshotTest() {
        Pokemon newPoke = bot.getPokemonTeam().get(1);
        newPoke.setHp(999999.0);
        int result = bot.isOneshot(newPoke); // no poke should be able to oneshot this newPoke
        assertEquals(5, result);
    }
}
package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleMacroTest {
    private BattleMacro battleMacro;

    @BeforeEach
    void setUp(){
        try {
            battleMacro = new BattleMacro();
        }catch (Exception e){}
    }


    @Test
    void addWin() {
        battleMacro.addLoss();
        assertEquals(0,battleMacro.getWinRate());
        battleMacro.addWin();
        assertEquals(0.5,battleMacro.getWinRate());
    }

    @Test
    void addLoss(){
        battleMacro.addWin();
        assertEquals(1,battleMacro.getWinRate());
        battleMacro.addLoss();
        assertEquals(0.5,battleMacro.getWinRate());
    }
}
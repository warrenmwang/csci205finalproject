package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    private Pokemon testPoke;
    private ArrayList<String> moveList;
    private ArrayList<String> types;

    @BeforeEach
    void setUp(){
        ArrayList<Double> stats = new ArrayList<>();
        stats.add(1.0); //Hp
        stats.add(2.0); //Atk
        stats.add(3.0); //Def
        stats.add(4.0); //SpAtk
        stats.add(5.0); //SpDef
        stats.add(6.0); //Speed

        moveList = new ArrayList<>();
        moveList.add("Move1");
        moveList.add("Move2");
        moveList.add("Move3");
        moveList.add("Move4");

        types = new ArrayList<>();
        types.add("Type1");
        types.add("Type2");

        testPoke = new Pokemon("1","name","botUrl",stats,moveList,types,"item","playerUrl");
    }

    @Test
    void getIsAlive() {
        assertEquals(true,testPoke.getIsAlive());
    }

    @Test
    void getName() {
        assertEquals("name",testPoke.getName());
    }

    @Test
    void getID() {
        assertEquals("1",testPoke.getID());
    }

    @Test
    void getStatusEffect() {
        assertEquals(PokemonStatusEffect.NO_EFFECT,testPoke.getStatusEffect());
    }

    @Test
    void getHP() {
        assertEquals(1.0,testPoke.getHP());
    }

    @Test
    void getAtk() {
        assertEquals(2.0,testPoke.getAtk());
    }

    @Test
    void getDef() {
        assertEquals(3.0,testPoke.getDef());
    }

    @Test
    void getSpAtk() {
        assertEquals(4.0,testPoke.getSpAtk());
    }

    @Test
    void getSpDef() {
        assertEquals(5.0,testPoke.getSpDef());
    }

    @Test
    void getSpe() {
        assertEquals(6.0,testPoke.getSpe());
    }

    @Test
    void getMoves() {
        assertEquals(moveList,testPoke.getMoves());
    }

    @Test
    void getTurnsActive() {
        assertEquals(0,testPoke.getTurnsActive());
    }

    @Test
    void getBotImage() {
        assertEquals("botUrl",testPoke.getBotImage());
    }

    @Test
    void getPlayerImage() {
        assertEquals("playerUrl",testPoke.getPlayerImage());
    }

    @Test
    void getMaxHp() {
        testPoke.receiveDamage(0.1);
        assertEquals(1.0,testPoke.getMaxHp());
    }

    @Test
    void getType1() {
        assertEquals("Type1",testPoke.getType1());
    }

    @Test
    void getType2() {
        assertEquals("Type2",testPoke.getType2());
    }

    @Test
    void getMove() {
        assertEquals("Move2",testPoke.getMove(1));
    }

    @Test
    void getProtectState() {
        assertEquals(false,testPoke.getProtectState());
    }

    @Test
    void getToxicTurn() {
        assertEquals(0,testPoke.getToxicTurn());
    }

    @Test
    void setStatusEffect() {
        assertEquals(PokemonStatusEffect.NO_EFFECT,testPoke.getStatusEffect());
        testPoke.setStatusEffect(PokemonStatusEffect.TOXIC);
        assertEquals(PokemonStatusEffect.TOXIC,testPoke.getStatusEffect());
    }

    @Test
    void setStats() {
        ArrayList<Double> stats2 = new ArrayList<>();
        stats2.add(6.0); //Hp
        stats2.add(5.0); //Atk
        stats2.add(4.0); //Def
        stats2.add(3.0); //SpAtk
        stats2.add(2.0); //SpDef
        stats2.add(1.0); //Speed

        testPoke.setStats(stats2);
        assertEquals(6.0,testPoke.getHP());
        assertEquals(5.0,testPoke.getAtk());
        assertEquals(4.0,testPoke.getDef());
        assertEquals(3.0,testPoke.getSpAtk());
        assertEquals(2.0,testPoke.getSpDef());
        assertEquals(1.0,testPoke.getSpe());
    }

    @Test
    void setIsAlive() {
        assertEquals(true,testPoke.getIsAlive());
        testPoke.setIsAlive(false);
        assertEquals(false,testPoke.getIsAlive());
    }

    @Test
    void setHp() {
        assertEquals(1,testPoke.getHP());
        testPoke.setHp(10);
        assertEquals(10,testPoke.getHP());
    }

    @Test
    void setAtk() {
        assertEquals(2,testPoke.getAtk());
        testPoke.setAtk(20);
        assertEquals(20,testPoke.getAtk());
    }

    @Test
    void setDef() {
        assertEquals(3,testPoke.getDef());
        testPoke.setDef(30);
        assertEquals(30,testPoke.getDef());
    }

    @Test
    void setSpAtk() {
        assertEquals(4,testPoke.getSpAtk());
        testPoke.setSpAtk(40);
        assertEquals(40,testPoke.getSpAtk());
    }

    @Test
    void setSpDef() {
        assertEquals(5,testPoke.getSpDef());
        testPoke.setSpDef(50);
        assertEquals(50,testPoke.getSpDef());
    }

    @Test
    void setSpe() {
        assertEquals(6,testPoke.getSpe());
        testPoke.setSpe(60);
        assertEquals(60,testPoke.getSpe());
    }

    @Test
    void setTurnsActive() {
        testPoke.setTurnsActive(3);
        assertEquals(3,testPoke.getTurnsActive());
    }

    @Test
    void setProtectState() {
        assertEquals(false,testPoke.getProtectState());
        testPoke.setProtectState(true);
        assertEquals(true,testPoke.getProtectState());
    }

    @Test
    void setToxicTurn() {
        testPoke.setToxicTurn(1);
        assertEquals(1,testPoke.getToxicTurn());
    }

    @Test
    void incToxicTurn() {
        assertEquals(0,testPoke.getToxicTurn());
    }

    @Test
    void resetstatsNoHp() {
        testPoke.setHp(0.7);
        testPoke.setStatusEffect(PokemonStatusEffect.BURNED);

        testPoke.resetstatsNoHp();

        assertEquals(0.7,testPoke.getHP());
        assertEquals(PokemonStatusEffect.NO_EFFECT,testPoke.getStatusEffect());

    }

    @Test
    void resetAllstats() {
        testPoke.setHp(0.7);
        testPoke.setIsAlive(false);
        testPoke.setStatusEffect(PokemonStatusEffect.BURNED);

        testPoke.resetAllstats();

        assertEquals(1.0,testPoke.getHP());
        assertEquals(true,testPoke.getIsAlive());
        assertEquals(PokemonStatusEffect.NO_EFFECT,testPoke.getStatusEffect());
    }

    @Test
    void checkIsAlive() {
        assertEquals(true,testPoke.getIsAlive());
        testPoke.receiveDamage(3.0);
        testPoke.checkIsAlive();
        assertEquals(false,testPoke.getIsAlive());
    }

    @Test
    void receiveDamage() {
        assertEquals(1.0,testPoke.getHP());
        testPoke.receiveDamage(0.2);
        assertEquals(0.8,testPoke.getHP());
    }

    @Test
    void heal() {
        testPoke.receiveDamage(0.5);
        testPoke.heal(0.2);
        assertEquals(0.7,testPoke.getHP());
        testPoke.heal(99.0);
        assertEquals(1.0,testPoke.getHP());
    }

    @Test
    void switch_reset() {

    }

    @Test
    void thorough_reset() {
    }




}
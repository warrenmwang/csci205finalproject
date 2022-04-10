package main;

import java.util.ArrayList;

public class Bot extends Player{
    private int difficulty;

    /**
     * Constructor, initializes Bot with list of pokemon, current pokemon to battle with,
     * and the total number of pokemon they have
     *
     * @param initPokemon
     */
    public Bot(ArrayList<Pokemon> initPokemon, int difficulty) {
        super(initPokemon);
        this.difficulty = difficulty;
    }
}

package main;

import java.util.ArrayList;

public class Bot extends Player{
    private int difficulty;

    /**
     * Constructor, initializes Bot with list of pokemon, current pokemon to battle with,
     * and the total number of pokemon they have
     *
     * @param initPokemon
     * @param selectedPokemonByIndex
     */
    public Bot(ArrayList<Pokemon> initPokemon, int selectedPokemonByIndex, int difficulty) {
        super(initPokemon, selectedPokemonByIndex);
        this.difficulty = difficulty;
    }
}

package main;

import java.util.ArrayList;

public class Player {
    private int numberOfPokemon;
    private ArrayList<Pokemon> pokemonTeam;
    private Pokemon currPokemon;

    /**
     * Constructor, initializes player with list of pokemon, current pokemon to battle with,
     * and the total number of pokemon they have
     * @param initPokemon
     * @param selectedPokemonByIndex
     */
    public Player(ArrayList<Pokemon> initPokemon, int selectedPokemonByIndex){
        this.pokemonTeam = initPokemon;
        this.currPokemon = initPokemon.get(selectedPokemonByIndex);
        this.numberOfPokemon = initPokemon.size();
    }

    /**
     * Getter method for the current active Pokemon being used by this Player.
     * @return {@link Pokemon} that is currently being used by the Player
     */
    public Pokemon getCurrPokemon() { return this.currPokemon; }

    /**
     * Switch current Pokemon with another Pokemon that is on the Player's team.
     * @param ind index of Pokemon to be selected in the Player's Pokemon Team
     */
    public void switchCurrPokemon(int ind){ this.currPokemon = this.pokemonTeam.get(ind); }
}

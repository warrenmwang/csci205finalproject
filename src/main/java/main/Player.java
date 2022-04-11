package main;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private int numberOfPokemon;
    private ArrayList<Pokemon> pokemonTeam;
    // NOTE: current Pokemon is first in list

    /**
     * Constructor, initializes player with list of pokemon, current pokemon to battle with,
     * and the total number of pokemon they have
     * @param initPokemon
     */
    public Player(ArrayList<Pokemon> initPokemon){
        this.pokemonTeam = initPokemon;
        this.numberOfPokemon = initPokemon.size();
    }

    /**
     * Getter method for the current active Pokemon being used by this Player.
     * @return {@link Pokemon} that is currently being used by the Player
     */
    public Pokemon getCurrPokemon() { return this.pokemonTeam.get(0); }

    /**
     * Switch current Pokemon with another Pokemon that is on the Player's team.
     * @param ind index of Pokemon to be selected in the Player's Pokemon Team
     */
    public void switchCurrPokemon(int ind){
        Collections.swap(pokemonTeam, 0, ind);
    }

    /**
     * Switch current Pokemon with another Pokemon that is on the Player's team.
     * @param id the ID of Pokemon to be selected in the Player's Pokemon Team
     */
    public void switchCurrPokemon(String id) {
        // find pokemon with id
        // swap it with the first pokemon in list

        int index = pokemonTeam.indexOf(pokemonTeam.stream().filter(p -> p.getID().equals(id)));
        Collections.swap(pokemonTeam, 0, index);

    }

    public ArrayList<Pokemon> getPokemonTeam(){
        return pokemonTeam;
    }

}

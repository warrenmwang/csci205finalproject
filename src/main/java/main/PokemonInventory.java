package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PokemonInventory {
    private DataLoader dataLoader;
    private HashMap<String, Pokemon> allPokemon;
    private ArrayList<String> usedPokemonIDs;

    public PokemonInventory() throws IOException {
        // when initialized, create the pokemon db
        dataLoader = new DataLoader();
        allPokemon = new HashMap<>();
        usedPokemonIDs = new ArrayList<>();

        ArrayList<Pokemon> tmp = dataLoader.getTESTSETPokemon();


        // put all pokemon into map
        for (Pokemon p : tmp) {
            allPokemon.put(p.getID(), p);
        }
    }


    // getter methods
    public HashMap<String, Pokemon> getAllPokemon() { return this.allPokemon; }
    public int getNumPokemon() { return this.allPokemon.size(); }


    //Return a Pokemon by ID
    public Pokemon getPokemon(String ID){
        return allPokemon.get(ID);
    }


    // return true if pokemon with id has NOT already been selected
    // else return false
    public boolean isPokemonAvailable(String ID){
        if(usedPokemonIDs.contains(ID)){
            return false;
        }else{
            return true;
        }
    }

    //add Pokemon to usedPokemonList if they are used
    public void setPokemonUsed(String ID){
        usedPokemonIDs.add(ID);
    }


}

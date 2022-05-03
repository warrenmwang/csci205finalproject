package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;


public class Player {
    private int numberOfPokemon;
    private ArrayList<Pokemon> pokemonTeam;
    // NOTE: current Pokemon is first in list

    private MovesInventory movesInventory;
    private boolean ForfeitStatus = false;
    private boolean protectState = true;




    /**
     * Constructor, initializes player with list of pokemon, current pokemon to battle with,
     * and the total number of pokemon they have
     *
     * @param initPokemon
     */
    public Player(ArrayList<Pokemon> initPokemon) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        this.pokemonTeam = initPokemon;
        this.numberOfPokemon = initPokemon.size();
        this.movesInventory = new MovesInventory();

    }

    /**
     * Getter method for the current active Pokemon being used by this Player.
     *
     * @return {@link Pokemon} that is currently being used by the Player
     */
    public Pokemon getCurrPokemon() {
        return this.pokemonTeam.get(0);
    }
    /**
     * Switch current Pokemon with another Pokemon that is on the Player's team.
     *
     * @param ind index of Pokemon to be selected in the Player's Pokemon Team
     */
    public void switchCurrPokemon(int ind) {

        getCurrPokemon().resetstatsNoHp();
        Collections.swap(this.pokemonTeam, 0, ind);
    }

    /**
     * Switch current Pokemon with another Pokemon that is on the Player's team.
     *
     * @param id the ID of Pokemon to be selected in the Player's Pokemon Team
     */
    public void switchCurrPokemon(String id) {
        // find pokemon with id
        // swap it with the first pokemon in list

        // get index of pokemon with the input id
        int index = 0 ;
        for(Pokemon p : pokemonTeam){
            if(p.getID().equalsIgnoreCase(id)){
                break;
            }
            index++;
        }

        getCurrPokemon().resetstatsNoHp();
        // swap current pokemon with the pokemon at index
        Collections.swap(pokemonTeam, 0, index);
    }

    public ArrayList<Pokemon> getPokemonTeam() {
        return pokemonTeam;
    }

    public int getNumberOfPokemon() {
        return numberOfPokemon;
    }
    public void setForfeitStatus(boolean status) {this.ForfeitStatus = status;}





    /**
     * Prompt player to choose a move that is available to their current Pokemon.
     *
     * @return Move object encapsulating the move
     */
    public Move chooseMove() {
        Move SelectedMove = new Move("blank", "0,0,0,0,0,0,0");

        UserInput.waitFXinput();
        String input = UserInput.getUSERINPUT();
        switch (input) {
            case ("Switch"): {
                String id = readInputLine(); // read in ID



                int id_as_index = 0;
                for(Pokemon p: pokemonTeam){
                    if(p.getID().equalsIgnoreCase(id)){
                        break;
                    }else{
                        id_as_index++;
                    }
                }


                SelectedMove = new Move("Switch", String.format("%d,0,0,0,0,0", id_as_index));
                break;
            }


            case ("attack"): {


                String selectMove = readInputLine();
                // read in user selection
                SelectedMove = movesInventory.getMove(selectMove);
                break;
            }

            case ("forfeit"): {
                System.out.println("Are you sure you want to forfeit? (y/n): ");
                UserInput.waitFXinput();
                input = UserInput.getUSERINPUT();
                if (input.equalsIgnoreCase("y")) {
                    ForfeitStatus = true;
                }

                break;
            }

        }

        return SelectedMove;

    }


    private String readInputLine(){
        UserInput.waitFXinput();
        return UserInput.getUSERINPUT();
    }

    public boolean getForfeitStatus(){
        return ForfeitStatus;
    }









}

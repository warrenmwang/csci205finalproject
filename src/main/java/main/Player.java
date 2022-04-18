package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player {
    private int numberOfPokemon;
    private ArrayList<Pokemon> pokemonTeam;
    // NOTE: current Pokemon is first in list

    private MovesInventory movesInventory;
    private boolean ForfeitStatus = false;


    /**
     * Constructor, initializes player with list of pokemon, current pokemon to battle with,
     * and the total number of pokemon they have
     *
     * @param initPokemon
     */
    public Player(ArrayList<Pokemon> initPokemon) {
        this.pokemonTeam = initPokemon;
        this.numberOfPokemon = initPokemon.size();
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
        Collections.swap(pokemonTeam, 0, ind);
    }

    /**
     * Switch current Pokemon with another Pokemon that is on the Player's team.
     *
     * @param id the ID of Pokemon to be selected in the Player's Pokemon Team
     */
    public void switchCurrPokemon(String id) {
        // find pokemon with id
        // swap it with the first pokemon in list

        int index = pokemonTeam.indexOf(pokemonTeam.stream().filter(p -> p.getID().equals(id)));
        Collections.swap(pokemonTeam, 0, index);

    }

    public ArrayList<Pokemon> getPokemonTeam() {
        return pokemonTeam;
    }

    public int getNumberOfPokemon() {
        return numberOfPokemon;
    }

    public void setPokemonTeam(ArrayList<Pokemon> newTeam) {
        this.pokemonTeam = newTeam;
    }

    /**
     * Prompt player to choose a move that is available to their current Pokemon.
     *
     * @return Move object encapsulating the move
     */
    public Move chooseMove() {
        Move SelectedMove = new Move("blank", "");

        // player can either switch, attack, or forfeit
        System.out.println("Player, do you want to switch, attack, or forfeit?");
        Scanner scnr = new Scanner(System.in);
        String input = scnr.nextLine();
        switch (input) {
            case ("Switch"): {
                // print out currently selected pokemon
                System.out.println("Your currently selected Pokemon: ");
                System.out.println(this.getCurrPokemon());

                // print out other pokemon user can switch their current pokemon with
                System.out.println("Other Pokemon you can switch with: ");
                for (int i = 1; i < this.getNumberOfPokemon(); i++) {
                    System.out.println(this.getPokemonTeam().get(i));
                }

                // get user selection
                System.out.println("Which pokemon do you want to switch?");
                String choice = readInputLine();

                //todo: change name into ID/index

                SelectedMove = new Move("Switch", choice);
                break;
            }
            ;
            case ("attack"): {
                System.out.println("Which move do you want to choose?");
                // print out the moves of the currently selected pokemon for the player to select from
                System.out.println("~~~Moves available~~~");
                for (String move : this.getCurrPokemon().getMoves()) {
                    System.out.println(move);
                }
                System.out.println("~~~~~~~~~~~~");

                String selectMove = readInputLine();
                // read in user selection
                SelectedMove = movesInventory.getMove(selectMove);
                break;
            }
            ;
            case ("forfeit"): {
                System.out.println("Are you sure you want to forfeit? (y/n): ");
                input = scnr.nextLine();
                if (input.equalsIgnoreCase("y")) {
                    ForfeitStatus = true;
                }

                break;
            }
            ;
        }

        return SelectedMove;

    }

    private String readInputLine(){
        Scanner scnr = new Scanner(System.in);
        return scnr.nextLine();
    }

    public boolean getForfeitStatus(){
        return ForfeitStatus;
    }
}

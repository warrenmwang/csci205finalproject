/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring2022
 * Instructor: Brian King
 * Section: 1 - 10 am
 *
 * Name: Warren Wang
 * Date: 01/26/2022
 *
 * Lab / Assignment:
 *
 * Description:
 *
 * *****************************************/


package main;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player{
    private int difficulty;
    private MovesInventory movesInventory;

    /**
     * Constructor
     * @param initTeam
     */
    public Bot(ArrayList<Pokemon> initTeam){
        // initialize bot's team and difficulty
        super(initTeam);
        difficulty = 0;
    }


    /**
     * Randomly select a move of the current Pokemon for the Bot
     * @return Move object that encapsulates the move that was randomly selected
     */
    public Move chooseMove(){
        int moveID = new Random().nextInt(4);
        return movesInventory.getMove(getCurrPokemon().getMove(moveID));
    }

    // getter methods
    public int getDifficulty(){ return difficulty; }

    // setter methods
    public void setDifficulty(int difficulty){ this.difficulty = difficulty; }
}





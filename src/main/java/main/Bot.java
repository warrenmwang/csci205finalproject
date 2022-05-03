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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player{
    private int difficulty;
    private MovesInventory movesInventory;

    /**
     * Constructor
     * @param initTeam
     */
    public Bot(ArrayList<Pokemon> initTeam)throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // initialize bot's team and difficulty
        super(initTeam);
        difficulty = 1; // initialize bot difficulty to be the easiest
        movesInventory = new MovesInventory();
    }

    /**
     * Randomly select a move of the current Pokemon for the Bot
     * @return Move object that encapsulates the move that was randomly selected
     */
    public Move botChooseMove(Pokemon Defender) {
        if (this.difficulty == 1) {
            //if the difficulty is normal, the bot will just select a random move and never switch
            //use completely random moves
            int moveID = new Random().nextInt(4);
            return movesInventory.getMove(getCurrPokemon().getMove(moveID));
        } else{
            //if the difficulty is hard, the bot will a select a move that will knock out the other pokemon if any of its moves can do that
            //check if one shot is possible, but if the move will not 1 shot a random move is selected
            int moveID = isOneshot(Defender);
            if (moveID != 5){
                return movesInventory.getMove(getCurrPokemon().getMove(moveID));
            }else{
                moveID = new Random().nextInt(4);
                return movesInventory.getMove(getCurrPokemon().getMove(moveID));
            }
        }
    }



    // getter methods
    public int getDifficulty(){ return difficulty; }

    // setter methods
    public void setDifficulty(int difficulty){ this.difficulty = difficulty; }

    // returns the index of the current pokemon move that will oneshot the defender Pokemon
    // if no move oneshots, return 5
    public int isOneshot(Pokemon Defender){
        for( int i = 0; i < 4; i ++){
            Move move = movesInventory.getMove(getCurrPokemon().getMove(i));
            double dmg = movesInventory.calcDamage(this.getCurrPokemon(), Defender, move);
            if(dmg > Defender.getHP()){
                return i;
            }
        }
        return 5;
    }
}
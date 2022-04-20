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
    private boolean oneshot;

    /**
     * Constructor
     * @param initTeam
     */
    public Bot(ArrayList<Pokemon> initTeam)throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // initialize bot's team and difficulty
        super(initTeam);
        difficulty = 0;
    }


    /**
     * Randomly select a move of the current Pokemon for the Bot
     * @return Move object that encapsulates the move that was randomly selected
     */
    public Move chooseMove(Pokemon Defender) {
        if (this.difficulty == 1) {
            //if the difficulty is easy, the bot will just select a random move and never switch
            //use completely random moves
            int moveID = new Random().nextInt(4);
            return movesInventory.getMove(getCurrPokemon().getMove(moveID));

        } else if (this.difficulty == 2) {
            //if the difficulty is medium, the bot will a select a move that will knock out the other pokemon if any of its moves can do that
            //check if one shot is possible, but if the move will not 1 shot a random move is selected
            int moveID = isOneshot(Defender);
            if (moveID != 5){
                return movesInventory.getMove(getCurrPokemon().getMove(moveID));
            }else{
                moveID = new Random().nextInt(4);
                return movesInventory.getMove(getCurrPokemon().getMove(moveID));
            }

        } else {
            //if the difficulty is not on easy or medium, the bot should evaluate the game state to either attack or switch
            double typeMatchUp = movesInventory.damageParam(this.getCurrPokemon().getType1(),Defender.getType1()) * movesInventory.damageParam(this.getCurrPokemon().getType1(),Defender.getType2()) * movesInventory.damageParam(this.getCurrPokemon().getType2(),Defender.getType1()) * movesInventory.damageParam(this.getCurrPokemon().getType1(),Defender.getType2());
            //if current pokemon can be oneshot by player's pokemon, it will switch to second pokemon
            if (getNumberOfPokemon() > 1 && isOneshot(Defender) == 1){
                switchCurrPokemon(1);
            }
            int randomness = new Random().nextInt(4);
            if (getNumberOfPokemon() > 1 && typeMatchUp < 1 && randomness != 2){
                //checks if the user can switch active, is weak to the enemy pokemon, and has a 25% chance of not switching to add human like features
                switchCurrPokemon(1);
            }
            int moveID = 0;
            ArrayList<Double> movedmgs = new ArrayList<>();
            while (moveID < 5){
                if (movesInventory.getMove(getCurrPokemon().getMove(moveID)).getDesignation().equals("status") && (this.getCurrPokemon().getTurnsActive() == 1)) {
                    //if the bot's pokemon is on its first turn being active, and the pokmeon has a status move, it should use that move first
                    return movesInventory.getMove(getCurrPokemon().getMove(moveID));
                }
                else{ //gets the damage from all of the moves and puts them in movedmgs arraylist
                    double dmg = movesInventory.calcDamage(this.getCurrPokemon(), Defender, movesInventory.getMove(getCurrPokemon().getMove(moveID)));
                    movedmgs.add(dmg);
                    int idmax = 0;
                    for( int i = 0; i < 4; i ++){ //sets the move's damage to a value in array list and then compares them to find the strongest attack whcih will be selected
                        double dmgmax = 0;
                        if (movedmgs.get(i) < dmgmax){
                            idmax = i;
                            dmgmax = movedmgs.get(i);
                        }
                    }
                    moveID = idmax;
                    return movesInventory.getMove(getCurrPokemon().getMove(moveID));
                }
            }

        }
        return new Move("Blank","");
    }

        // getter methods
    public int getDifficulty(){ return difficulty; }

    // setter methods
    public void setDifficulty(int difficulty){ this.difficulty = difficulty; }

    // returns the index of the current pokemon move that will oneshot the defender Pokemon
    // if no move oneshots, return 5
    public int isOneshot(Pokemon Defender){
        for( int i = 0; i < 4; i ++){
            double dmg = movesInventory.calcDamage(this.getCurrPokemon(), Defender, movesInventory.getMove(getCurrPokemon().getMove(i)));
            if(dmg > Defender.getHP()){
                return i;
            }
        }
        return 5;
    }

    public int canBeOneShot(Pokemon Defender){
        for (int i = 0; i < 4; i ++){
            double dmg = movesInventory.calcDamage(Defender,this.getCurrPokemon(),movesInventory.getMove(getCurrPokemon().getMove(i)));
            if(dmg > this.getCurrPokemon().getHP()){
                return 1;
            }
        }
        return 0;
    }


}





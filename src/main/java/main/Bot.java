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
        movesInventory = new MovesInventory();
    }



    /**
     * Randomly select a move of the current Pokemon for the Bot
     * @return Move object that encapsulates the move that was randomly selected
     */
    public Move botChooseMove(Pokemon Defender) {
        // TODO remove me
        System.out.println("----------------- Entering Bot Smart Choose Move -----------------");
        // TODO implement difficulty
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


            // CURRENT DIFFICULTY IS FROM HERE DOWN

        } else {
            //if the difficulty is not on easy or medium, the bot should evaluate the game state to either attack or switch
            double typeMatchUp = movesInventory.damageParam(this.getCurrPokemon().getType1(),Defender.getType1()) * movesInventory.damageParam(this.getCurrPokemon().getType1(),Defender.getType2()) * movesInventory.damageParam(this.getCurrPokemon().getType2(),Defender.getType1()) * movesInventory.damageParam(this.getCurrPokemon().getType1(),Defender.getType2());
            //if current pokemon can be oneshot by player's pokemon, it will switch to second pokemon
//            if (getNumberOfPokemon() > 1 && isOneshot(Defender) == 1){
//                switchCurrPokemon(1);
//                int index = getRandomAlivePokemon();
//                return new Move("Switch","0,0,0,0,0");
//            }
            int randomness = new Random().nextInt(4);
            if (getNumberOfPokemon() > 1 && typeMatchUp < 1 && randomness != 2){
                //checks if the user can switch active, is weak to the enemy pokemon, and has a 25% chance of not switching to add human like features
                if(getNumberAlive() > 1){
                    int index = getRandomAlivePokemon();
                    return new Move("Switch", index+",0,0,0,0");
                }
            }
            int moveID = 0;
            ArrayList<Double> movedmgs = new ArrayList<>();

            while (moveID < 4){
                // FIXME: "main.Move.getDesignation()" because the return value of "main.MovesInventory.getMove(String)" is null occurred 2 times, not sure for what move
                if (movesInventory.getMove(getCurrPokemon().getMove(moveID)).getDesignation().equals("status") && (this.getCurrPokemon().getTurnsActive() == 1)) {
                    //if the bot's pokemon is on its first turn being active, and the pokmeon has a status move, it should use that move first
                    return movesInventory.getMove(getCurrPokemon().getMove(moveID));
                }
                else{ //gets the damage from all of the moves and puts them in movedmgs arraylist
                    double dmg = movesInventory.calcDamage(this.getCurrPokemon(), Defender, movesInventory.getMove(getCurrPokemon().getMove(moveID)));
                    movedmgs.add(dmg);
                }
                moveID++;
            }

            int idmax = 0;
            for( int i = 0; i < 4; i ++){ //sets the move's damage to a value in array list and then compares them to find the strongest attack which will be selected
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

//        }
//        return new Move("blank","0,0,0,0,0");
//    }

    public Move testbotChooseMove(Pokemon Defender){
        return new Move("Switch","1,0,0,0,0");
    }

        // getter methods
    public int getDifficulty(){ return difficulty; }

    // setter methods
    public void setDifficulty(int difficulty){ this.difficulty = difficulty; }

    // returns the index of the current pokemon move that will oneshot the defender Pokemon
    // if no move oneshots, return 5
    public int isOneshot(Pokemon Defender){
        for( int i = 0; i < 4; i ++){
            System.out.println("curr poke: " + getCurrPokemon());

            System.out.println("curr poke move: " + getCurrPokemon().getMove(i));

            Move move = movesInventory.getMove(getCurrPokemon().getMove(i));
            System.out.println("move in isOneshot: " + move);
            double dmg = movesInventory.calcDamage(this.getCurrPokemon(), Defender, move);
            if(dmg > Defender.getHP()){
                return i;
            }
        }
        return 5;
    }

    public int canBeOneShot(Pokemon Defender){
        for (int i = 0; i < 4; i ++){
            Move move = movesInventory.getMove(getCurrPokemon().getMove(i));
            double dmg = movesInventory.calcDamage(Defender,this.getCurrPokemon(),move);
            if(dmg > this.getCurrPokemon().getHP()){
                return 1;
            }
        }
        return 0;
    }

    // return number of pokemon alive on bot team
    public int getNumberAlive(){
        int x = 0;
        for(Pokemon p : getPokemonTeam()){
            if(p.getIsAlive()) x++;
        }
        return x;
    }

    // return index of the random alive pokemon that ISN'T the current pokemon
    // ASSUMES there is an alive pokemon that isn't the current one
    public int getRandomAlivePokemon(){
        Random rand = new Random();
        int index;
        while(true){
            index = rand.nextInt(2) + 1;
            if(getPokemonTeam().get(index).getIsAlive()) return index;
        }

    }

    //TODO
    public int askChooseSwitch(){
        return 0;
    }


}





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
import java.util.Scanner;

public class BattleMacro {
    private int playerWins;
    private int playerLosses;
    private int numberOfRounds;
    private BattleMicro battleMicro;
//    private boolean whoseTurn; // 1 for Player, 0 for Bot

    private GameState gameState;


    /**
     * CONSTRUCTOR -- This will have to represent our most top level game loop.
     */
    public BattleMacro() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // initialize the BattleMicro object
        battleMicro = new BattleMicro();
        // go into teambuilding state
        setGameState(GameState.TEAMBUILDING);
    }

    // getter methods
    public GameState getGameState(){ return this.gameState; }
    // setter methods
    public void setGameState(GameState state){ this.gameState = state;}

    /**
     * Main Game Loop. Calls functions on the BattleMicro object and other BattleMacro
     * objects when necessary to facilitate the progression of the game.
     */
    public void mainGameLoop() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // TODO: check for 14 consecutive wins and then print a very special message
        //    check for loss streaks and also print a very special message (toxic)
        //    if (wins == 0 && losses >= 1){
            // sout("You smell! LOL!!!!!!"))

        // use reset function to create teams, prompt for picking teams,
        // refresh variables
        reset();

        // start game, set game state
        setGameState(GameState.BATTLE);

        // run game loop while game state is in Battle
        while(getGameState().equals(GameState.BATTLE)){
            // play one complete round (both players attack or do their moves)
            System.out.printf("Start of round %d\n", numberOfRounds);
            battleMicro.playOneTurn();

            // increment number of rounds player has played
            numberOfRounds++;

            // check if player forfeited, won, less
            if(battleMicro.getForfeitStatus()){
                // ask if player wants to play again, updates game state
                promptPlayAgain();
            }else if(battleMicro.getGameOverStatus()){
                if(battleMicro.getPlayerWonStatus()){
                    // player won
                    addWin();
                }else{
                    // player lost
                    addLoss();
                }
                // ask if player wants to play again, updates game state
                promptPlayAgain();
            }
            // if new game, reset, then change into Battle state
            if(getGameState().equals(GameState.NEW_GAME)){
                reset();
                setGameState(GameState.BATTLE);
            }else if (getGameState().equals(GameState.GAME_OVER)){
                break;
            }
        }

        // print exit message
        printExitGameMessage();
    }

    /**
     * Increment number of player wins.
     */
    public void addWin(){playerWins++;}

    /**
     * Increment number of player losses.
     */
    public void addLoss() {playerLosses++;}

    /**
     * Reset the saved game variables.
     */
    public void reset()throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        playerWins = 0;
        playerLosses = 0;
        numberOfRounds = 0;

        // reset all important BattleMicro statuses
        battleMicro.setGameOverStatus(false);
        battleMicro.setPlayerWonStatus(false);

        // generate random 6 pokemon teams
        battleMicro.generateInitialPlayerRandomTeam();
        battleMicro.generateInitialBotRandomTeam();

        // bring teams down to their final form of 3
        battleMicro.initPlayer();
        battleMicro.initBot();

        // create the Player and Bot objects with their handpicked teams for play
        battleMicro.constructPlayer();
        battleMicro.constructBot();
    }

    /**
     * Return true if player wants to play again, else return false
     * @return
     */
    public void promptPlayAgain(){
        System.out.println("Do you want to play again? [y/n]");
        String reply = readInputLine();

        if(reply.equalsIgnoreCase("y")){
            setGameState(GameState.NEW_GAME);
        } else {
            setGameState(GameState.GAME_OVER);
        }
    }

    /**
     * Exit Game Message function
     */
    public void printExitGameMessage(){
        System.out.printf("You have played %d rounds, won %d times, and loss %d times.\n", numberOfRounds, playerWins, playerLosses);
        System.out.println("We are sad to see you go, but have a nice day.");
    }

    private String readInputLine(){
        Scanner scnr = new Scanner(System.in);
        return scnr.nextLine();
    }

}

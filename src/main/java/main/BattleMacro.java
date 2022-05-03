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
import java.util.concurrent.TimeUnit;

public class BattleMacro {
    private int playerWins;
    private int playerLosses;
    private int numberOfRounds;
    private BattleMicro battleMicro;

    private GameState gameState;
    private boolean firstTurn;
    private boolean Reseting;


    /**
     * CONSTRUCTOR -- This will have to represent our most top level game loop.
     */
    public BattleMacro() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // initialize the BattleMicro object
        battleMicro = new BattleMicro();
        // go into teambuilding state
        setGameState(GameState.TEAMBUILDING);
        firstTurn = true;
        battleMicro.generateInitialPlayerRandomTeam();

    }

    // getter methods
    public GameState getGameState(){ return this.gameState; }
    // setter methods
    public void setGameState(GameState state){ this.gameState = state;}

    public BattleMicro getBattleMicro() {return this.battleMicro;}

    public double getWinRate() { return (double)playerWins/((double)playerWins+(double) playerLosses);}

    /**
     * Main Game Loop. Calls functions on the BattleMicro object and other BattleMacro
     * objects when necessary to facilitate the progression of the game.
     */
    public void mainGameLoop() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // TODO: check for 14 consecutive wins and then print a very special message
        //    check for loss streaks and also print a very special message (toxic)
        //    if (wins == 0 && losses >= 1){


        // use reset function to create teams, prompt for picking teams,
        // refresh variables
        reset();
        firstTurn = false;

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
                addLoss();
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
            try{TimeUnit.MILLISECONDS.sleep(100);} catch(Exception e){}
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

        // reset all important BattleMicro statuses
        battleMicro.setGameOverStatus(false);
        battleMicro.setPlayerWonStatus(false);

        // generate random 6 pokemon teams
        if(!firstTurn || Reseting){
            battleMicro.generateInitialPlayerRandomTeam();
            Reseting = false;
        }
        battleMicro.generateInitialBotRandomTeam(); // always reset bot team

        // bring teams down to their final form of 3
        // bring bot team down to 3
        battleMicro.initBot();

        // bring player team down to 3
        if (battleMicro.initPlayer() == -1){
            firstTurn = false;
            Reseting = true;
            reset();
            return;
        };


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
        System.out.printf("Turns Played: %d\n", numberOfRounds);
        System.out.printf("Wins: %d\n", playerWins);
        System.out.printf("Losses: %d\n", playerLosses);
        System.out.printf("Matches played: %d\n", playerLosses+playerWins);

        double winRate = (double)playerWins/((double)playerWins+(double) playerLosses);
        System.out.printf("Win Rate: %.2f\n", winRate);

    }

    private String readInputLine(){
       UserInput.waitFXinput();
       return UserInput.getUSERINPUT();
    }

}

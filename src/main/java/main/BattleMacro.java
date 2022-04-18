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

public class BattleMacro {
    private int playerPokemonAlive;
    private int botPokemonAlive;
    private int playerWins;
    private int playerLosses;
    private int numberOfRounds;
    private BattleMicro battleMicro;
    private boolean whoseTurn; // 1 for Player, 0 for Bot


    /**
     * CONSTRUCTOR -- This will have to represent our most top level game loop.
     */
    public BattleMacro(){
        // initialize the BattleMicro object
        battleMicro = new BattleMicro();
        // generate the 6 random pokemon teams for both player and bot
        battleMicro.generateInitialPlayerRandomTeam();
        battleMicro.generateInitialBotRandomTeam();
        // prompt user to select 3 of their 6 pokemon for play
        // randomly select 3 of 6 pokemon for bot
        battleMicro.initPlayer();
        battleMicro.initBot();
        // create the Player and Bot objects with their handpicked teams for play
        battleMicro.constructPlayer();
        battleMicro.constructBot();


        // initialize 2 player's pokemon alive to be 3
        // and playerWins to be 0
        playerWins = 0;
        playerLosses = 0;
        playerPokemonAlive = 3;
        botPokemonAlive = 3;
        numberOfRounds = 0;

    }

    /**
     * Main Game Loop. Calls functions on the BattleMicro object and other BattleMacro
     * objects when necessary to facilitate the progression of the game.
     */
    public void mainGameLoop(){
        // Select the first player
        battleMicro.checkTurn();

        while(true){
            // play one complete round (both players attack or do their moves)
            battleMicro.playOneTurn();
            // check if player forfeited
            if(battleMicro.getForfeitStatus()){
                break;
            }

            // if someone wins or loses, prompt user if want to keep playing or quit game
            if(winCheck()){
                // increment player wins and reset pokemon teams
                addWin();
                reset();
            }else if(loseCheck()){
                // increment player losses and reset pokemon teams
                addLoss();
                reset();
            }

            numberOfRounds++;
        }

        // print out stats that player has accrued
        System.out.printf("You have played %d rounds and won %d times.\n", numberOfRounds, playerWins);
        System.out.println("We are sad to see you go, but have a nice day.");
    }


    /**
     * Check to see if Player has won
     * @return true if player has won, else return false
     */
    public boolean winCheck(){
        return (playerPokemonAlive > 0) && (botPokemonAlive == 0);
    }

    /**
     * Check to see if Bot has won
     * @return true if bot has won, else return false
     */
    public boolean loseCheck(){
        return (playerPokemonAlive == 0) && (botPokemonAlive > 0);
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
     * // TODO reset both pokemon teams with new pokemon
     */
    public void reset(){
        playerWins = 0;
        playerPokemonAlive = 3;
        botPokemonAlive = 3;

        // generate random 6 pokemon teams
        battleMicro.generateInitialPlayerRandomTeam();
        battleMicro.generateInitialBotRandomTeam();

        // bring teams down to their final form of 3
        battleMicro.initPlayer();
        battleMicro.initBot();

    }

}

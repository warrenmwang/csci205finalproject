package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BattleMicro {
    private Player user;
    private Bot bot;
    private boolean whoseTurn; // 1 for Player, 0 for Bot
    private boolean forfeitStatus;
    private boolean gameOverStatus; // false for game in progress, true for game over
    private boolean playerWonStatus; // only use when game is over, false for player lost, true for player won


    private ArrayList<Pokemon> userTeam;   // only used to generate the initial Player team
    private ArrayList<Pokemon> botTeam;   // only used to generate the initial Bot team

    private ArrayList<Pokemon> AtkDef = new ArrayList<>(2);
    private Pokemon Attaker;
    private Pokemon Defender;

    private PokemonInventory pokemonInventory;
    private MovesInventory movesInventory;

    private Player firstPlayer;
    private Player secondPlayer;

    private Move firstMove;
    private Move secondMove;


    /**
     * Constructor
     * Manages the PokemonInventory
     * Manages the Player and Bot
     */
    public BattleMicro() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // initialize our two main inventories
        pokemonInventory = new PokemonInventory();
        movesInventory = new MovesInventory();

        // initialize any instance vars
        forfeitStatus = false;
        gameOverStatus = false;
        playerWonStatus = false;
    }


    // getter methods
    public ArrayList<Pokemon> getUserTeam(){ return this.userTeam; }
    public ArrayList<Pokemon> getBotTeam() { return this.botTeam; }
    public boolean getWhoseTurn(){ return this.whoseTurn; }
    public boolean getForfeitStatus(){ return this.forfeitStatus; }
    public boolean getGameOverStatus(){ return this.gameOverStatus; }
    public boolean getPlayerWonStatus(){ return this.playerWonStatus; }

    // setter methods
    public void setUserTeam(ArrayList<Pokemon> team){ this.userTeam = team; }
    public void setBotTeam(ArrayList<Pokemon> team){ this.botTeam = team; }
    public void setForfeitStatus(boolean status){ this.forfeitStatus = status; }
    public void setGameOverStatus(boolean status){ this.gameOverStatus = status; }
    public void setPlayerWonStatus(boolean status){ this.playerWonStatus = status; }



    /**
     * check who moves first
     */
    public void checkTurn(Move userMove, Move botMove){
        String moveName1 = userMove.getName();
        String moveName2 = botMove.getName();

        if(XOR(moveName1.equals("Switch"),moveName2.equals("Switch"))){
            if(moveName1.equals("Switch")){
                firstPlayer = user;
                firstMove = userMove;
                secondPlayer = bot;
                secondMove = botMove;
            } else{
                firstPlayer = bot;
                firstMove = botMove;
                secondPlayer = user;
                secondMove = userMove;
            }
            return;
        }

        if (user.getCurrPokemon().getSpe() > bot.getCurrPokemon().getSpe()){
            whoseTurn = true;
            firstPlayer = user;
            secondPlayer = bot;
        } else {
            whoseTurn = false;
            firstPlayer = bot;
            secondPlayer = user;
        }
    }


    // example of reflection API:
    // https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
    public void Attack(Player attacker, Move move, Player defender ) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        MovesInventory moves = null;
        try {
            moves = new MovesInventory();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class<?>[] paramTypes = {Player.class, Player.class,Move.class};
        Method setNameMethod = moves.getClass().getMethod(move.getName(), paramTypes);
        setNameMethod.invoke(moves, attacker, defender,move);
    }





    /**
     * Generates a random team of 6 pokemon
     * @return ArrayList<Pokemon> of 6 randomly selected Pokemon
     */
    public ArrayList<Pokemon> generateRandomTeam(){
        HashMap<String, Pokemon> pokemonHashMap = pokemonInventory.getAllPokemon();
        ArrayList<Pokemon> retList = new ArrayList<>();
        ArrayList<String> alreadySelectedIDs = new ArrayList<>();

        // generate a random number that will be the ID
        // of the random pokemon to be selected from the hashmap of pokemon from pokeInv
        // then add that to our to be returned ArrayList of Pokemon
        // need to make sure no duplicates are selected!
        Random rand = new Random();
        String id;
        int randNum;
        while(true){
            randNum = rand.nextInt(1, pokemonInventory.getNumPokemon() - 1) + 1; // offset by 1 bc there is no id of 0, id range is [1, totNum]
            id = String.format("%d", randNum);
            // if id NOT already selected, add this new pokemon and save the id
            // else skip to next iteration of while loop to pick randomly again
            if( ! alreadySelectedIDs.contains(id)) {
                retList.add(pokemonHashMap.get(id));
                alreadySelectedIDs.add(id);
            }else{
                continue;
            }

            // exit random selecting if we have 6 pokemon
            if(retList.size() == 6){
                break;
            }
        }

        return retList;
    }

    /**
     * Generates the 6 Random Pokemon for the Player
     */
    public void generateInitialPlayerRandomTeam(){ userTeam = generateRandomTeam(); }

    /**
     * Generates the 6 Random Pokemon for the Bot
     */
    public void generateInitialBotRandomTeam(){ botTeam = generateRandomTeam(); }


    /**
     * Prompts the Player to choose 3 Pokemons from the 6 shown to them.
     * Then, this function will only update {@link #userTeam} ArrayList of Pokemon to be those 3 selected Pokemon.
     */
    public void initPlayer(){
        // TODO: for now we will use the terminal as our interface of interaction
        // TODO: for now also assuming user enters input in 100% correctly ...

        // print out the 6 Pokemon options that the user can choose from
        System.out.println("Please choose 3 from the 6 Pokemon shown here:");
        for(int i = 0; i < userTeam.size(); i++){
            System.out.println(userTeam.get(i));
        }

        // create helper vars
        int n = 3;
        String id;
        ArrayList<String> selectedIDs = new ArrayList<>(3);
        // get user to select 3 pokemon by id
        Scanner scnr = new Scanner(System.in);
        while(n > 0){
            System.out.printf("You have %d choices remaining. Please type the ID of the Pokemon that you want: ", n);
            id = scnr.nextLine();
            selectedIDs.add(id);
            n--;
        }

        // loop thru original pokemon team, new team only has pokemon whose id are in selectedIDs list
        // original team of 6, then replace it with this new team of 3
        ArrayList<Pokemon> newTeam = new ArrayList<>();
        for(Pokemon p : userTeam){
            if(selectedIDs.contains(p.getID())){
                newTeam.add(p);
            }
        }

        // TODO remove me
        // print out selected pokemon
        System.out.println("You just selected the following pokemon:");
        for(Pokemon p: newTeam){
            System.out.println(p);
        }

        // update player's team in the Player object AND in the BattleMicro object
        setUserTeam(newTeam);
    }

    /**
     * Update the {@link #botTeam} list of 6 Pokemon to be 3 Pokemon, chosen randomly.
     */
    public void initBot(){
        Random rand = new Random();

        // generate 3 random numbers and remove the corresponding pokemon from the 6
        // for the bot object
        int randIndex;
        for(int i = 0 ; i < 3 ; i++) {
            randIndex = rand.nextInt(botTeam.size());
            botTeam.remove(randIndex);
        }

        //TODO remove me
        System.out.println("Printing BOT's Team: ");
        for(Pokemon p : botTeam){
            System.out.println(p);
        }
    }

    /**
     * Constructs the Player object once their team has been completely chosen and is ready to play.
     */
    public void constructPlayer()throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{ user = new Player(userTeam); }

    /**
     * Constructs the Bot object once their team has been completely chosen and is ready to play.
     */
    public void constructBot()throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{ bot = new Bot(botTeam); }


    /**
     * Returns who is the first player comparing both player's default pokemon speed.
     * The player with the faster Pokemon gets the first turn.
     * @param player1
     * @param player2
     * @return
     */
    public Player firstPlayer(Player player1,Player player2){
        Pokemon poke1 = player1.getCurrPokemon();
        Pokemon poke2 = player2.getCurrPokemon();

        if(poke1.getSpe() > poke2.getSpe()){
            return player1;
        }

        return player2;
    }

    /**
     * Returns who is the second player comparing both player's default pokemon speed.
     * The player with slower Pokemon gets the second turn.
     * @param player1
     * @param player2
     * @return
     */
    public Player secondPlayer(Player player1,Player player2){
        Pokemon poke1 = player1.getCurrPokemon();
        Pokemon poke2 = player2.getCurrPokemon();

        if(poke1.getSpe() > poke2.getSpe()){
            return player2;
        }

        return player1;
    }

    /**
     * Does everything within a single round of a Battle.
     */
    public void playOneTurn() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{

        // get player's moves
        Move userMove = user.chooseMove();
        Move botMove = bot.chooseMove();

        // See who gets the first move in this round
        checkTurn(userMove,botMove);

        // TODO remove me
        System.out.println("First player turn:");

        // check if forfeit
        if(firstPlayer.getForfeitStatus() || getGameOverStatus()){
            return;
        }

        // first move player goes
        Attack(firstPlayer, firstMove , secondPlayer);

        // checks to see if game is over if a pokemon has died
        checkDeath();

        // Quit if one of the following applies:
        // 1. Forfeit
        // 2. Player has won
        // 3. Player has lost
        // Battle Macro will need to know if player has won or loss (get the playerwonstatus)
        if(secondPlayer.getForfeitStatus() || getGameOverStatus()){
            return;
        }


        // If game is still in progress, second move player goes:
        Attack(secondPlayer, secondMove, firstPlayer);



        // checks to see if game is over if a pokemon has died
        checkDeath();
        // Quit if one of the following applies:
        // 1. Forfeit
        // 2. Player has won
        // 3. Player has lost
        // Battle Macro will need to know if player has won or loss (get the playerwonstatus)
        if(secondPlayer.getForfeitStatus() || getGameOverStatus()){
            return;
        }

        // end turn effect
        endTurnEffect(firstPlayer,secondPlayer);



    }




//    public Move chooseMove(){
//        Move SelectedMove = new Move("blank","");
//
//        // player can either switch, attack, or forfeit
//        System.out.println("Player, do you want to switch, attack, or forfeit?");
//        Scanner scnr = new Scanner(System.in);
//        String input = scnr.nextLine();
//        switch(input) {
//            case ("Switch"): {
//                // print out currently selected pokemon
//                System.out.println("Your currently selected Pokemon: ");
//                System.out.println(user.getCurrPokemon());
//
//                // print out other pokemon user can switch their current pokemon with
//                System.out.println("Other Pokemon you can switch with: ");
//                for (int i = 1; i < user.getNumberOfPokemon(); i++) {
//                    System.out.println(user.getPokemonTeam().get(i));
//                }
//
//                // get user selection
//                System.out.println("Which pokemon do you want to switch?");
//                String choice = readInputLine();
//
//                //todo: change name into ID/index
//                //might not need in the future, cause we can hard code within javaFX
//                SelectedMove = new Move("Switch", choice);
//                break;
//            }
//
//            case ("attack"): {
//                System.out.println("Which move do you want to choose?");
//                // print out the moves of the currently selected pokemon for the player to select from
//                System.out.println("~~~Moves available~~~");
//                for (String move : user.getCurrPokemon().getMoves()) {
//                    System.out.println(move);
//                }
//                System.out.println("~~~~~~~~~~~~");
//
//                String selectMove = readInputLine();
//                // read in user selection
//                SelectedMove = movesInventory.getMove(selectMove);
//                break;
//            }
//            case ("forfeit"): {
//                System.out.println("Are you sure you want to forfeit? (y/n): ");
//                input = scnr.nextLine();
//                if (input.equalsIgnoreCase("y")) {
//                    setForfeitStatus(true);
//                }
//
//                break;
//            }
//
//        }
//
//        return SelectedMove;
//
//
//
//
//
//    }

    private String readInputLine(){
        Scanner scnr = new Scanner(System.in);
        return scnr.nextLine();
    }

    // check if any of the current pokemon have died
    // if a pokemon has died, need to choose another pokemon to take
    // the place of the current pokemon
    // if the user's pokemon has died, let the user choose
    // if the bot's pokemon has died, just choose the next pokemon in its array
    // need to a designation somewhere that the pokemon has died and cannot be reselected

    /**
     * After Pokemon have attacked, checks to see if a Pokemon has died AND
     * if player has won or loss. Will set the gameOverStatus and playerWonStatus
     * if player has won or loss.
     *
     * Depends on the checkDeathHelper() function.
     */
    private void checkDeath() {
        // check if a pokemon has died
        // check to see if a pokemon has died, whether player of bot have
        // no more alive pokemon to switch with meaning there is a WINNER/LOSER
        switch(checkDeathHelper()){
            // player has lost
            case(3):{
                setGameOverStatus(true);
                setPlayerWonStatus(false);
                break;
            }
            // player has won
            case(4):{
                setGameOverStatus(true);
                setPlayerWonStatus(true);
                break;
            }
            // every other case
            default:{
                // do nothing
                break;
            }
        }
    }

    /**
     * Returns 0 if no pokemon has died
     * Returns 1 if Player Pokemon has died and can continue playing
     * Returns 2 if Bot Pokemon has died and can continue playing
     * Returns 3 if Player Pokemon has died and CANNOT continue playing (player lost)
     * Returns 4 if Bot Pokemon has died and CANNOT continue playing (player wins)
     * @return
     */
    public int checkDeathHelper(){

        if(user.getCurrPokemon().getHP() <= 0){
            // user's pokemon has died

            // first set user's curr pokemon isAlive status to false
            user.getCurrPokemon().setIsAlive(false);

            // check to see if player has any remaining pokemon to choose from
            // get all pokemon user has that are alive (if any)
            ArrayList<Pokemon> alivePoke = new ArrayList<>();
            for(Pokemon p : user.getPokemonTeam()){
                if(p.getIsAlive()){
                    alivePoke.add(p);
                }
            }

            // if player has pokemon to choose from
            if(alivePoke.size() > 0){
                // print out pokemon they can choose from
                System.out.println("Your remaining alive Pokemon to choose from: ");
                for(Pokemon p : alivePoke){
                    System.out.println(p);
                }
                // read in user selection
                System.out.println("ID of the Pokemon to make current: ");
                String id = readInputLine();
                // set the current pokemon isAlive status to dead
                // then swap it with the newly selected pokemon
                user.getCurrPokemon().setIsAlive(false);
                user.switchCurrPokemon(id);

                return 1;
            }else{
                // no alive pokemon to choose from, player has lost
                return 3;
            }


        }else if(bot.getCurrPokemon().getHP() <= 0){
            // bot pokemon has died, select the next available in its arraylist
            // first set the bot's current pokemon to be dead
            bot.getCurrPokemon().setIsAlive(false);

            // search for next available pokemon if any
            for(Pokemon p : bot.getPokemonTeam()){
                if(p.getIsAlive()){
                    bot.switchCurrPokemon(p.getID());
                }
            }
            // if current pokemon alive status is still dead, that means bot had no remaining
            // alive pokemon to switch with
            if(bot.getCurrPokemon().getIsAlive() == false){
                // bot has no alive pokmon remaining to choose from, player has won
                return 4;
            }else{
                // bot can continue playing
                return 2;
            }
        }else {
            // no pokemon has died
            return 0;
        }

    }

    public void endTurnEffect(Player player1,Player player2){
        Pokemon player1poke = player1.getCurrPokemon();
        Pokemon player2Poke = player2.getCurrPokemon();
    }


    public boolean XOR(boolean statement1, boolean statement2){
        return ( ( statement1 || statement2 ) && ! ( statement1 && statement2 ) );
    }
}





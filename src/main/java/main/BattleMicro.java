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
import java.util.concurrent.TimeUnit;

public class BattleMicro {
    private Player user;
    private Bot bot;
    private boolean whoseTurn; // 1 for Player, 0 for Bot
    private boolean gameOverStatus; // false for game in progress, true for game over
    private boolean playerWonStatus; // only use when game is over, false for player lost, true for player won


    private ArrayList<Pokemon> userTeam;   // only used to generate the initial Player team
    private ArrayList<Pokemon> botTeam;   // only used to generate the initial Bot team

//    private ArrayList<Pokemon> AtkDef = new ArrayList<>(2);
//    private Pokemon Attaker;
//    private Pokemon Defender;

    private PokemonInventory pokemonInventory;
    private MovesInventory movesInventory;

    private Player firstPlayer;
    private Player secondPlayer;

    private Move firstMove;
    private Move secondMove;

    private ArrayList<ArrayList<String>> PriorityMoveList;

    private ArrayList<String> attackSwitchMove;

    //private UserInput userInput;




    /**
     * Constructor
     * Manages the PokemonInventory
     * Manages the Player and Bot
     */
    public BattleMicro() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        // initialize our two main inventories
        pokemonInventory = new PokemonInventory();
        movesInventory = new MovesInventory();

        PriorityMoveList = movesInventory.getPriorityMoveList();

        attackSwitchMove = movesInventory.getAttackSwitchMove();

        // initialize any instance vars
        gameOverStatus = false;
        playerWonStatus = false;

    }


    // getter methods
    public ArrayList<Pokemon> getUserTeam(){ return this.userTeam; }
    public ArrayList<Pokemon> getBotTeam() { return this.botTeam; }
    public boolean getWhoseTurn(){ return this.whoseTurn; }
    public boolean getForfeitStatus(){ return user.getForfeitStatus(); }
    public boolean getGameOverStatus(){ return this.gameOverStatus; }
    public boolean getPlayerWonStatus(){ return this.playerWonStatus; }
    public Player getUser() { return this.user; }


    // setter methods
    public void setUserTeam(ArrayList<Pokemon> team){ this.userTeam = team; }
    public void setBotTeam(ArrayList<Pokemon> team){ this.botTeam = team; }
    public void setGameOverStatus(boolean status){ this.gameOverStatus = status; }
    public void setPlayerWonStatus(boolean status){ this.playerWonStatus = status; }




    /**
     * Assign firstPlayer and secondPlayer given:
     * userMove, botMove
     * Switch gets highest priority
     * If attack, will compare current pokemon speed
     */

    final int firstPriority = 0;
    final int secondPrority = 1;
    public void checkTurn(Move userMove, Move botMove){
        String moveName1 = userMove.getName();
        String moveName2 = botMove.getName();

        if(XOR(PriorityMoveList.get(firstPriority).contains(moveName1),PriorityMoveList.get(firstPriority).contains(moveName2))) {
            if (PriorityMoveList.get(firstPriority).contains(moveName1)) {
                firstPlayer = user;
                firstMove = userMove;
                secondPlayer = bot;
                secondMove = botMove;
            } else {
                firstPlayer = bot;
                firstMove = botMove;
                secondPlayer = user;
                secondMove = userMove;
            }
        }else if (XOR(PriorityMoveList.get(secondPrority).contains(moveName1),PriorityMoveList.get(secondPrority).contains(moveName2))){
            if(PriorityMoveList.get(secondPrority).contains(moveName1)){
                firstPlayer = user;
                firstMove = userMove;
                secondPlayer = bot;
                secondMove = botMove;
            } else {
                firstPlayer = bot;
                firstMove = botMove;
                secondPlayer = user;
                secondMove = userMove;
            }

        } else if (user.getCurrPokemon().getSpe() > bot.getCurrPokemon().getSpe()){
            whoseTurn = true;
            firstPlayer = user;
            firstMove = userMove;
            secondPlayer = bot;
            secondMove = botMove;
        } else {
            whoseTurn = false;
            firstPlayer = bot;
            firstMove = botMove;
            secondPlayer = user;
            secondMove = userMove;
        }

        // TODO : remove me
        System.out.println("\ncheckTurn");
        System.out.println("firstPlayer is: " + firstPlayer);
        System.out.println("secondPlayer is: " + secondPlayer);
        System.out.println("firstPlayer's move: " + firstMove);
        System.out.println("secondPlayer's move: " + secondMove + "\n");
    }


    // example of reflection API:
    // https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
    public void Attack(Player attacker, Move move, Player defender ) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?>[] paramTypes = {Player.class, Player.class,Move.class};
        Method setNameMethod = movesInventory.getClass().getMethod(move.getName(), paramTypes);
        setNameMethod.invoke(movesInventory, attacker, defender,move);
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
        System.out.println("Your first Pokemon choice will be the first Pokemon you deploy.");
        for(int i = 0; i < userTeam.size(); i++){
            System.out.println(userTeam.get(i));
        }

        // create helper vars
        int n = 3;
        String id;
        ArrayList<String> selectedIDs = new ArrayList<>(3);
        // get user to select 3 pokemon by id

        //Original
//        while(!canGetUSERINPUT){}
//        Scanner scnr = new Scanner(USERINPUT);
        while(n > 0){
            System.out.printf("You have %d choices remaining. Please type the ID of the Pokemon that you want: ", n);
//            System.out.println("userinput"+USERINPUT);
//            System.out.println("cangetuserinput"+canGetUSERINPUT);

            UserInput.waitFXinput();
            id = UserInput.getUSERINPUT();

            System.out.println("\n\n");
            System.out.println(id);

            selectedIDs.add(id);
            n--;
        }

        //Test
//        while(n > 0){
//            System.out.printf("You have %d choices remaining. Please type the ID of the Pokemon that you want: ", n);
//            String a = System.in.toString();
//            while(a.equals(System.in.toString())){}
//            Scanner scnr = new Scanner(System.in);
//            id = scnr.nextLine();
//            selectedIDs.add(id);
//            n--;
//        }



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
        System.out.println("------------------ You just selected the following pokemon: ------------------");
        for(Pokemon p: newTeam){
            System.out.println(p);
        }
        System.out.println("------------------  ------------------  ------------------");

        // update player's team in the Player object AND in the BattleMicro object
        setUserTeam(newTeam);

//        UserInput = user.getUserInput();
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
        System.out.println("------------------ Printing BOT's Team: ------------------");
        for(Pokemon p : botTeam){
            System.out.println(p);
        }
        System.out.println("------------------  ------------------  ------------------");

        setBotTeam(botTeam);
    }

    /**
     * Constructs the Player object once their team has been completely chosen and is ready to play.
     */
    public void constructPlayer()throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{ user = new Player(userTeam);
        // initialize userinput obj
        //initInput();
        }

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
        // FIXME: weird funkiness occurs when bot switches pokemon and player attacks in the first turn

        //System.out.println("------------------ Start of New Turn ------------------");
        System.out.printf("Current user pokemon : \n" + user.getCurrPokemon());
        System.out.printf("YOUR pokemon HP is at %.2f%s health.\n ", 100.0 * (user.getCurrPokemon().getHP() / user.getCurrPokemon().getMaxHp()), "%");
        System.out.printf("Current opponent's pokemon : \n" + bot.getCurrPokemon());
//        System.out.printf("curr %f, orig %f\n", user.getCurrPokemon().getHP(), user.getCurrPokemon().getMaxHp());
        System.out.printf("BOT's pokemon is at %.2f%s health.\n", 100.0 * (bot.getCurrPokemon().getHP() / bot.getCurrPokemon().getMaxHp()), "%");
//        System.out.printf("curr %f, orig %f\n", bot.getCurrPokemon().getHP(), bot.getCurrPokemon().getMaxHp());

        // get player's moves
        System.out.println("------------------ PLAYER CHOOSE MOVE ------------------");
        Move userMove = user.chooseMove();
        System.out.println(userMove);




        System.out.println("------------------ BOT CHOOSE MOVE ------------------");
        Move botMove = bot.botChooseMove(user.getCurrPokemon());
        System.out.println(botMove);




        // See who gets the first move in this round
        checkTurn(userMove,botMove);


        //for uturn and volt switch
        int switchIndexFirst = 0;
        int switchIndexSecond = 0;
        Pokemon currSecondPoke = secondPlayer.getCurrPokemon();
        if(attackSwitchMove.contains(firstMove.getName())){
            //show the pokemon can be changed
            switchIndexFirst = firstPlayer.askChooseSwitch();
        }




        System.out.println("------------------ First Player's Move Commences ------------------");

        // check if forfeit
        if(firstPlayer.getForfeitStatus() || getGameOverStatus()){
            return;
        }

        // first move player goes
        Attack(firstPlayer, firstMove , secondPlayer);

        // checks to see if game is over if a pokemon has died
        System.out.println("------------------ Checking if Pokemon Died after First Player's Move ------------------");
        if (checkDeath() == 1){
            return;
        }

        //switch (usually should be 0)
        firstPlayer.switchCurrPokemon(switchIndexFirst);

        // Quit if one of the following applies:
        // 1. Forfeit
        // 2. Player has won
        // 3. Player has lost
        // Battle Macro will need to know if player has won or loss (get the playerwonstatus)
        if(secondPlayer.getForfeitStatus() || getGameOverStatus()){
            return;
        }

        System.out.println("------------------ Second Player's Move Commences ------------------");
        // If game is still in progress, second move player goes:
        Attack(secondPlayer, secondMove, firstPlayer);


        System.out.println("------------------ Checking if Pokemon Died after Second Player's Move ------------------");
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
        if(attackSwitchMove.contains(secondMove.getName())) {
            if (currSecondPoke == secondPlayer.getCurrPokemon()) {
                System.out.println("which Pokemon do you want to Switch?");
                switchIndexSecond = secondPlayer.askChooseSwitch();
                secondPlayer.switchCurrPokemon(switchIndexSecond);
            }
        }


        System.out.println("------------------ End of Turn ------------------");
        // end turn effect
        endTurnEffect(firstPlayer,secondPlayer);



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
    private int checkDeath() {
        // check if a pokemon has died
        // check to see if a pokemon has died, whether player of bot have
        // no more alive pokemon to switch with meaning there is a WINNER/LOSER
        switch(checkDeathHelper()){
            //one pokemon is dead
            case(1):{
                ;
            }
            case(2):{
                return 1;
            }


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
        return 0;
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
                String id = UserInput.readInputLine();
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
            if(!bot.getCurrPokemon().getIsAlive()){
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

//    public void initInput(){
//        userInput = user.getUserInput();
//    }


}





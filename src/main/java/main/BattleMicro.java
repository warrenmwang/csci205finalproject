package main;

import main.javafx.DeathUpdate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BattleMicro {
    private Player user;
    private Bot bot;
    private boolean whoseTurn; // 1 for Player, 0 for Bot
    private boolean gameOverStatus; // false for game in progress, true for game over
    private boolean playerWonStatus; // only use when game is over, false for player lost, true for player won


    private ArrayList<Pokemon> userTeam;   // only used to generate the initial Player team
    private ArrayList<Pokemon> botTeam;   // only used to generate the initial Bot team



    private PokemonInventory pokemonInventory;
    private MovesInventory movesInventory;

    private Player firstPlayer;
    private Player secondPlayer;

    private Move firstMove;
    private Move secondMove;

    private ArrayList<ArrayList<String>> PriorityMoveList;

    private ArrayList<String> attackSwitchMove;




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
    public Bot getBot(){return bot;}


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

        Random rand = new Random(System.currentTimeMillis());
        String id;
        int randNum;
        while(true){
            int max = pokemonInventory.getNumPokemon() + 1; // add one to make 64 inclusive
            int min = 1;
            randNum = rand.nextInt(max - min) + min;
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
            if(retList.size() >= 6){
                break;
            }
        }

        //reset all pokemon's stats and alive state
        for(Pokemon p:retList){
            p.resetAllstats();
        }

        return retList;
    }

    /**
     * Generates the 6 Random Pokemon for the Player
     */
    public void generateInitialPlayerRandomTeam(){ userTeam = generateRandomTeam();}



    /**
     * Generates the 6 Random Pokemon for the Bot
     */
    public void generateInitialBotRandomTeam(){ botTeam = generateRandomTeam(); }


    /**
     * Prompts the Player to choose 3 Pokemons from the 6 shown to them.
     * Then, this function will only update {@link #userTeam} ArrayList of Pokemon to be those 3 selected Pokemon.
     */
    public int initPlayer(){

        // create helper vars
        int n = 3;
        String id;
        ArrayList<String> selectedIDs = new ArrayList<>(3);
        // get user to select 3 pokemon by id
        while(n > 0){
            UserInput.waitFXinput();
            id = UserInput.getUSERINPUT();

            if(id.equals("exit")){
                return -1;
            }

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


        // update player's team in the Player object AND in the BattleMicro object
        setUserTeam(newTeam);

        try{constructPlayer();} catch (Exception e){}
        return 0;
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
        setBotTeam(botTeam);
        try{constructBot();} catch(Exception e){}
    }

    /**
     * Constructs the Player object once their team has been completely chosen and is ready to play.
     */
    public void constructPlayer()throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{ user = new Player(userTeam);}

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

        Move botMove = bot.botChooseMove(user.getCurrPokemon());

        // See who gets the first move in this round
        checkTurn(userMove,botMove);

        // check if forfeit
        if(firstPlayer.getForfeitStatus() || getGameOverStatus()){
            return;
        }

        // first move player goes
        Attack(firstPlayer, firstMove , secondPlayer);

        // checks to see if game is over if a pokemon has died

        if (checkDeath() == 1){
            // JAVAFX
            DeathUpdate.checkNeedUpdateBattleViewSprite();
            return;
        }

        // JAVAFX
        DeathUpdate.checkNeedUpdateBattleViewSprite();



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
        // JAVAFX
        DeathUpdate.checkNeedUpdateBattleViewSprite();
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


        checkDeath();

        // JAVAFX
        DeathUpdate.checkNeedUpdateBattleViewSprite();



    }

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

                DeathUpdate.checkNeedUpdateBattleViewSprite();
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
                DeathUpdate.checkNeedUpdateBattleViewSprite();
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


            DeathUpdate.setPlayerCurrPokemonDied(true);



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
                // JAVAFX only update if player has an alive pokemon to choose from and switch to
                DeathUpdate.setPlayerCurrPokemonDied(true);



                UserInput.setNeedToSwitch(true);

                UserInput.setCanGetSwitch(true);




                try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){};


                String id = UserInput.readInputLine();
                // set the current pokemon isAlive status to dead
                // then swap it with the newly selected pokemon
                user.getCurrPokemon().setIsAlive(false);
                user.switchCurrPokemon(id);

                return 1;
            }else{
                UserInput.setCanGetSwitch(true);
                // no alive pokemon to choose from, player has lost
                return 3;
            }



        }else if(bot.getCurrPokemon().getHP() <= 0){
            // bot pokemon has died, select the next available in its arraylist
            // first set the bot's current pokemon to be dead
            bot.getCurrPokemon().setIsAlive(false);

            DeathUpdate.setBotCurrPokemonDied(true);


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
                UserInput.setCanGetSwitch(true);

                return 4;
            }else{
                // bot can continue playing
                UserInput.setCanGetSwitch(true);

                return 2;
            }
        }else {
            // no pokemon has died
            UserInput.setCanGetSwitch(true);
            return 0;
        }

    }
    //Todo
    public void endTurnEffect(Player player1,Player player2){
        Pokemon player1poke = player1.getCurrPokemon();
        Pokemon player2poke = player2.getCurrPokemon();

        if (player1poke.getStatusEffect() == PokemonStatusEffect.BURNED){
            player1poke.receiveDamage(player1poke.getMaxHp()*0.125);
        }
        if (player1poke.getStatusEffect() == PokemonStatusEffect.TOXIC){
            player1poke.incToxicTurn();
            player1poke.receiveDamage(player1poke.getToxicTurn() * player1poke.getMaxHp()*0.125);
        }

        if (player2poke.getStatusEffect() == PokemonStatusEffect.BURNED){
            player2poke.receiveDamage(player2poke.getMaxHp()*0.125);
        }
        if (player2poke.getStatusEffect() == PokemonStatusEffect.TOXIC){
            player2poke.incToxicTurn();
            player2poke.receiveDamage(player2poke.getToxicTurn() * player2poke.getMaxHp()*0.125);
        }

    }


    public boolean XOR(boolean statement1, boolean statement2){
        return ( ( statement1 || statement2 ) && ! ( statement1 && statement2 ) );
    }


}





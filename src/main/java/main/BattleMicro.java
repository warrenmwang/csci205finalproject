package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BattleMicro {
    private Player user;
    private Bot bot;
    private boolean whoseTurn; // 1 for Player, 0 for Bot
    
    private ArrayList<Pokemon> team1;   // holds team for user
    private ArrayList<Pokemon> team2;   // holds team for bot

    private ArrayList<Pokemon> AtkDef = new ArrayList<>(2);
    private Pokemon Attaker;
    private Pokemon Defender;

    private PokemonInventory pokemonInventory;

    /**
     * Constructor
     * Manages the PokemonInventory
     * Manages the Player and Bot
     */
    public BattleMicro(){
        // generate random pokemon teams and then assign them to player and bot
        // they will be prompted to pick from their teams
        try {
            pokemonInventory = new PokemonInventory();
        }
        catch(IOException e){
            // shouldn't happen.
        }
        team1 = generateRandomTeam();
        team2 = generateRandomTeam();

        this.user = new Player(team1);
        this.bot = new Bot(team2, 1); // TODO still have difficulty level? temporary set to 1

        // TODO: prompt user to choose 3 from the random 6 chosen for them, then initialize bot's team
        //initPlayer();
        //initBot();
        
        // compares the speed of the first pokemon in both player's teams
        // the faster one get's the first turn




    }

    /**
     * check who moves first
     */
    public void checkTurn(){
        if (user.getCurrPokemon().getSpe() > bot.getCurrPokemon().getSpe()){
            whoseTurn = true;
        } else {
            whoseTurn = false;
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
            randNum = rand.nextInt(pokemonInventory.getNumPokemon());
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
     * Inform the player to select 3 Pokemon out of the 6 random ones shown to them
     */
    public void initPlayer(){
        // TODO: for now we will use the terminal as our interface of interaction
        // TODO: for now also assuming user enters input in 100% correctly ...
        int n = 3;
        String id;
        ArrayList<String> selectedIDs = new ArrayList<>(3);

        // get user to select 3 pokemon by id
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please choose 3 from the 6 Pokemon shown here:");
        while(n > 0){
            System.out.printf("You have %d choices remaining. Please type the ID of the Pokemon that you want: ", n);
            id = scnr.next();
            selectedIDs.add(id);
            n--;
        }

        // now update the pokemon team of the player by removing
        // all pokemon whose id's are not in the selected id's list
        ArrayList<Pokemon> team = user.getPokemonTeam();
        for(int i = 0 ; i < team.size() ; i++){
            // if selected id's does NOT contain the id of the current pokemon, remove it from the team
            if( ! selectedIDs.contains(team.get(i).getID())){
                team.remove(i);
            }
        }

        // check there's only 3 pokemon in the new team and set player's team to be it
        if(team.size() == 3){
            user.setPokemonTeam(team);
        }else{
            // this shouldn't happen but...just in case
            System.out.println("SOMETHING WENT WRONG");
            System.exit(1);
        }

    }

    // choose completely random pokemon for bot's team
    public void initBot(){
        Random rand = new Random();
        ArrayList<String> selectedIDs = new ArrayList<>();
        // choose 3 random unique id's
        int numIdsChosen = 0;
        while(true){
            int id = rand.nextInt(6);
            if(! selectedIDs.contains(id)){
                selectedIDs.add(String.format("%d",id));
                numIdsChosen++;
            }
            if(numIdsChosen == 3) break;
        }

        // now update the pokemon team of the player by removing
        // all pokemon whose id's are not in the selected id's list
        ArrayList<Pokemon> team = bot.getPokemonTeam();
        for(int i = 0 ; i < team.size() ; i++){
            // if selected id's does NOT contain the id of the current pokemon, remove it from the team
            if( ! selectedIDs.contains(team.get(i).getID())){
                team.remove(i);
            }
        }

        // check there's only 3 pokemon in the new team and set player's team to be it
        if(team.size() == 3){
            bot.setPokemonTeam(team);
        }else{
            // this shouldn't happen but...just in case
            System.out.println("SOMETHING WENT WRONG");
            System.exit(1);
        }

    }
}

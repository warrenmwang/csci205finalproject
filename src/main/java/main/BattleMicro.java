package main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class BattleMicro {
    private Player user;
    private Bot bot;
    private boolean whoseTurn; // 1 for Player, 0 for Bot
    
    private ArrayList<Pokemon> team1;   // holds team for user
    private ArrayList<Pokemon> team2;   // holds team for bot

    private ArrayList<Pokemon> AtkDef = new ArrayList<>(2);
    private Pokemon Attaker;
    private Pokemon Defender;

    /**
     * Constructor
     */
    public BattleMicro(Player user,Bot bot){
        this.user = user;
        this.bot = bot;
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
    public void Attack(Player attacker, String moveName, Player defender ) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        MovesInventory moves =  new MovesInventory();
        Class<?>[] paramTypes = {Player.class, Player.class};
        Method setNameMethod = moves.getClass().getMethod(moveName, paramTypes);
        setNameMethod.invoke(moves, attacker, defender);
    }

    // TODO all of the functions

    /**
     * Generates a random team of 6 pokemon
     * @return
     */
    public ArrayList<Pokemon> generateRandomTeam(){
        
    }

    /**
     *
     * @param pokeTeam
     */
    public initPlayer(ArrayList<Pokemon> pokeTeam){

    }
}

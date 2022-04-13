package main;

public class BattleMicro {
    private Player user;
    private Bot bot;
    private boolean whoseTurn; // 1 for Player, 0 for Bot

    private ArrayList<Pokemon> AtkDef = new ArrayList<>(2);
    private Pokemon Attaker;
    private Pokemon

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
            whoseTurn = 1;
        } else {
            whoseTurn = 0;
        }
    }

    // example of reflection API:
    // https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
    public void Attack(Player attacker, String move, Player defender ){
        private Move move =  new move();
        Class<?>[] paramTypes = {Player.class, Player.class};
        Method setNameMethod = move.getClass().getMethod(methodName, paramTypes);
        setNameMethod.invoke(move, attacker, defender);
    }

    // TODO all of the functions
}

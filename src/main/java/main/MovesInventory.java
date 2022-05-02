package main;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MovesInventory {
    // store the numbers from that matrix of type x type
    private ArrayList<Double> dontusemeMults;
    // multipliers matrix
    private HashMap<String, HashMap<String, Double>> allMultipliers;
    private ArrayList<String> allTypes;

    // store all the move as string
    private HashMap<String, Move> allMoves;

    private ArrayList<String> firstPriorityMove;

    private ArrayList<String> secondPriorityMove;

    private ArrayList<ArrayList<String>> PriorityMoveList;

    private ArrayList<String> attackSwitchMove;



    // TODO Constructor

    /**
     * Constructor
     */
    public MovesInventory() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        allMultipliers = new HashMap<>();
        allTypes = new ArrayList<>();
        dontusemeMults = new ArrayList<>();

        firstPriorityMove = new ArrayList<>();
        firstPriorityMove.add("Switch");

        secondPriorityMove = new ArrayList<>();
        secondPriorityMove.add("ice_shard");
        secondPriorityMove.add("aqua_jet");
        secondPriorityMove.add("ackerman_insight");

        attackSwitchMove = new ArrayList<>();
        attackSwitchMove.add("u_turn");
        attackSwitchMove.add("volt_switch");

        ArrayList<String> thirdPriotityMove = new ArrayList<>();

        PriorityMoveList = new ArrayList<>();
        PriorityMoveList.add(firstPriorityMove);
        PriorityMoveList.add(secondPriorityMove);


        // load the types
        allTypes.add("NORMAL");
        allTypes.add("FIRE");
        allTypes.add("WATER");
        allTypes.add("GRASS");
        allTypes.add("ELECTRIC");
        allTypes.add("ICE");
        allTypes.add("FIGHTING");
        allTypes.add("POISON");
        allTypes.add("GROUND");
        allTypes.add("FLYING");
        allTypes.add("PSYCHIC");
        allTypes.add("BUG");
        allTypes.add("ROCK");
        allTypes.add("GHOST");
        allTypes.add("DRAGON");
        allTypes.add("DARK");
        allTypes.add("STEEL");
        allTypes.add("FAIRY");
        allTypes.add("NULL");


        // load in numbers for mult
        dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);


        // initialize the allMultipliers
        initializeDamageParam();

        // load all moves
        DataLoader data = new DataLoader();
        this.allMoves = data.getTESTSETMoves();

    }

    // getter methods
    public HashMap<String, HashMap<String, Double>> getAllMultipliers() {
        return this.allMultipliers;
    }

    public ArrayList<String> getAllTypes() {
        return this.allTypes;
    }

    public Move getMove(String moveName) {
        return this.allMoves.get(moveName);
    }

    public ArrayList<ArrayList<String>> getPriorityMoveList() {return PriorityMoveList;}

    public ArrayList<String> getAttackSwitchMove() { return attackSwitchMove;}

    // TODO: call this function in every move function
    /**
     * Attack print statement to showcase what actually happens when a pokemon attacks another pokemon
     * @param attacker
     * @param defender
     * @param move
     */
    public void printAttackMessage(Pokemon attacker, Pokemon defender, Move move, Boolean didMoveHit, Double damage, Boolean effectHit){
        if(didMoveHit){
            // move hits
            System.out.println("" + attacker.getName() + " used " + move.getName() + " against " + defender.getName() + "!");
            System.out.printf("It hits and deals %f damage!\n", damage);
        }else{
            // move missed
            System.out.println("" + attacker.getName() + " used " + move.getName() + " against " + defender.getName() + "!");
            System.out.println("It misses.");
        }
    }

    //todo
    public void printStatusMoveMessage(Pokemon attacker, Pokemon defender, Move move){}

    /**
     * Given a move's accuracy, compute whether the move successfully hits.
     * @param MoveAccur a move's accuracy
     * @return true if move hits, false otherwise
     */
    Boolean AccuracyCheck(double MoveAccur) {
        if (new Random().nextInt(1000) < 1000 * MoveAccur) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Main damage calculator
     * @param attacker
     * @param defender
     * @param move
     * @return
     */
    public double calcDamage(Pokemon attacker, Pokemon defender, Move move) {

        double moveDmg = move.getBasePower();
        String moveType = move.getType();
        String designation = move.getDesignation();

        //accounts for critical hits
        double criticalMult = new Random().nextInt(24);
        if (criticalMult == 17.0) {
            criticalMult = 2;
            System.out.println("Wow! A critical hit!");
        } else {
            criticalMult = 1;
        }

        // TODO
        // check type of move against defender's both types

        double stab = 1;
        if (moveType.equals(attacker.getType1()) || moveType.equals(attacker.getType2())) {
            stab = 1.5;
        }
        double MoveTypeModifier = damageParam(move.getType(), defender.getType2()) * damageParam(move.getType(), defender.getType1());
        double modifier = MoveTypeModifier * stab;

        //-------------
        double attack;
        double defence;
        if (designation.equals("physical")) {
            attack = attacker.getAtk();
            if (attacker.getStatusEffect().equals(PokemonStatusEffect.BURNED)) {
                attack = attack * 0.5;
            }
            defence = defender.getDef();
        } else if (designation.equals("special")) {
            attack = attacker.getSpAtk();
            defence = defender.getSpDef();
        } else {
            attack = 0;
            defence = 1;
            modifier = 0;
        }
        //-------------

        double dmg;
        dmg = ((42 * moveDmg * attack / defence) / 50 + 2); //bulk of damage calculation


        double dmgVariance = (new Random().nextInt(150) + 850) / 1000.0;
        ;
        dmg = dmg * dmgVariance;
        dmg = dmg * modifier * criticalMult;


        if(defender.getProtectState()){
            dmg = 0;
        }
        defender.setProtectState(false);

//      System.out.println(stab);
//        System.out.println(criticalMult);
//        System.out.println(dmgVariance);
//        System.out.println(modifier);
//        System.out.println(dmg);
        return Math.ceil(dmg); //rounds up damage to prevent endless battles
    }

    // loads up the nested hashmap structure holding the multiplier values
    public void initializeDamageParam() {

        int i = 0;
        for (String s1 : allTypes) {
            allMultipliers.put(s1, new HashMap<>());
            for (String s2 : allTypes) {
                allMultipliers.get(s1).put(s2, dontusemeMults.get(i));
                i++;

            }
        }
    }

    // FIXME: HashMap returns null when called by Bot's botChooseMove, calling isOneShort, calling damageParam (here)
    // returns the multiplier value at the intersection of the two moves given
    public double damageParam(String attackType, String defendType) {
        return allMultipliers.get(attackType).get(defendType);
    }

    /**
     * Of the Attacker, switches their current Pokemon with the Pokemon specified in the Move
     * @param attacker
     * @param defender
     * @param move
     */
    public void Switch(Player attacker, Player defender, Move move) {
        int SwitchIndex = (int) move.getBasePower();
//        System.out.println("switchindex: " + SwitchIndex);
        attacker.switchCurrPokemon(SwitchIndex);

        // TODO remove me
        // print out new team
//        System.out.println("------------- New Pokemon Team After Switching -------------");
//        for(Pokemon p : attacker.getPokemonTeam()){
//            System.out.println(p);
//        }
//        System.out.println("-------------  -------------  -------------");
    }


    public void simplemove(Player attacker, Player defender, Move move){
        double Accur = move.getAccuracy();

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,false);
    }


    public void blank(Player attacker, Player defender, Move move){
        ;
    }


    //power: 140, accur: 0.9, FIRE, special, half user Spatk
    public void overheat(Player attacker, Player defender, Move move) {
        // Hard
        double Accur = 0.9;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();


        double damage = 0;
        //deal damage or effect based on accuracy

        boolean hit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);

            // Move effect of halfing the special attack
            attacker.getCurrPokemon().setSpAtk(attackPoke.getSpAtk() * 0.5);
        }


        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,hit);


    }


    //power: 75, acc: 1, GRASS, special, heal half the damage to attacker
    public void giga_drain(Player attacker, Player defender, Move move) {
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);



        //Special effect: attacker heal half of the damage
        attackPoke.heal(0.5 * damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,hit);
    }

    //power: 90, accur: 1, FIGHTING, physical, no effect
    public void skyuppercut(Player attacker, Player defender, Move move) {
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        boolean hit = false;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }



        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,false);

    }

    //status, double user speed
    public void agility(Player attacker, Player defender, Move move) {
        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackPoke.setSpe(2 * attackPoke.getSpe());

        printStatusMoveMessage(attackPoke,defenderPoke,move);
    }


    //power: 110, accur: 1, GROUND, physical
    public void earthquake(Player attacker, Player defender, Move move) {
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,false);
    }

    //power: 90, Accur: 1, PSYCHIC, special, 50% chance to lower defender's SpDef by 33%
    public void psychic(Player attacker, Player defender, Move move) {
        double Accur = 1.0;
        double effectAccur = 0.5;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        boolean hit = false;
        boolean effectHit = false;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);

            //50% chance to lower defender's SpDef by 33%
            if (AccuracyCheck(effectAccur)) {
                effectHit = true;
                defenderPoke.setSpDef(0.667 * defenderPoke.getSpDef());
            }
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,effectHit);
    }

    //todo
    //status, heal 50% maxHp
    public void recover(Player attacker, Player defender, Move move) {
        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackPoke.heal(0.5 * attackPoke.getMaxHp());

        printStatusMoveMessage(attackPoke,defenderPoke,move);
    }

    //power: 70, Accur: 1, ICE, special
    public void hidden_power_ice(Player attacker, Player defender, Move move) {
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        boolean hit = false;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,false);
    }

    //power: 110, Accur:0.9, ROCK, physical
    public void stone_edge(Player attacker, Player defender, Move move) {
        double Accur = 0.9;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        boolean hit = false;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,false);
    }

    //power: 90, Accur: 1, ICE, special, 30% chance to lower defender's SpAtk by 33%
    public void ice_beam(Player attacker, Player defender, Move move) {
        double Accur = 1.0;
        double effectAccur = 0.3;


        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        boolean hit = false;
        boolean effectHit = false;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);

            //30% chance to lower defender's SpAtk by 33%

            if (AccuracyCheck(effectAccur)) {
                effectHit = true;
                defenderPoke.setSpDef(0.667 * defenderPoke.getSpDef());
            }
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,effectHit);
    }

    //power: 70, Accur: 1, GRASS, special
    public void hidden_power_grass(Player attacker, Player defender, Move move) {
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        boolean hit = false;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,false);
    }

    //power: 90, Accur: 1, ELECTRIC, special, 20% set defender statuc to paralysis
    public void thunderbolt(Player attacker, Player defender, Move move) {
        double Accur = 1.0;
        double effectAccur = 0.2;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);


            if (AccuracyCheck(effectAccur)) {
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.PARALYZED);
            }
        }
        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,effectHit);
    }

    //power: 110, Accur:0.9, FIRE, special, 30% lower defender atk by 33%
    public void heat_wave(Player attacker, Player defender, Move move) {
        double Accur = 0.9;
        double effectAccur = 0.3;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);


            if (AccuracyCheck(effectAccur)) {
                effectHit = true;
                defenderPoke.setAtk(0.667 * defenderPoke.getAtk());
            }
        }
        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,effectHit);
    }


    //power: 100, Accur: 1, FLYING, special
    public void air_slash(Player attacker, Player defender, Move move) {
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        // Pokemon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,false);
    }

    // TODO: finish
    //power: 70, Accur: 1, ELECTRIC, user switch out after move is used
    public void volt_switch(Player attacker, Player defender, Move move) {
        simplemove(attacker,defender,move);

//        double Accur = 1.0;
//
//        // Get poke
//        Pokemon attackPoke = attacker.getCurrPokemon();
//        Pokemon defenderPoke = defender.getCurrPokemon();
//
//        double damage = 0;
//        //deal damage or effect based on accuracy
//        boolean hit = false;
//        if (AccuracyCheck(Accur)) {
//            hit = true;
//            damage = calcDamage(attackPoke, defenderPoke, move);
//        }
//
//        // Pokemon receives damage, check if alive
//        defenderPoke.receiveDamage(damage);
//
//        //todo
//        if (attacker.getNumberOfPokemon() >= 2) {
//            attacker.switchCurrPokemon(2);
//        }
//
//        // Print move message
//        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,hit);
    }

    public void iron_head(Player attacker, Player defender, Move move) {
        double Accur = 1.0;
        double effectAccur = 0.3;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHt = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);

            //30% chance to raise attackers physical attack 50%

            if (AccuracyCheck(effectAccur)) {
                effectHt = true;
                attackPoke.setSpDef(0.667 * attackPoke.getSpDef());
            }
        }
        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke,defenderPoke,move,hit,damage,effectHt);

    }






    public void dragon_claw(Player attacker, Player defender, Move move) {
        double Accur = move.getAccuracy();

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,false);
    }


    public void fire_punch(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.2;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.BURNED);
            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void waterfall(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void ice_punch(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.5;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setSpAtk(defenderPoke.getSpAtk() * 0.66);
            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void dragon_dance(Player attacker,  Player defender, Move move) {
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();


        attackerPoke.setAtk(1.5 * attackerPoke.getAtk());
        attackerPoke.setSpe(1.5 * attackerPoke.getSpe());

        printStatusMoveMessage(attackerPoke,defenderPoke,move);

    }


    public void scald(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.2;

                // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.BURNED);

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void heal_bell(Player attacker,  Player defender, Move move) {
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();


        for (Pokemon p : attacker.getPokemonTeam()){
            p.setStatusEffect(PokemonStatusEffect.NO_EFFECT);
        }

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }


    //todo
    //status move with 0.9 accur
    public void thunderwave(Player attacker,  Player defender, Move move) {
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        defenderPoke.setStatusEffect(PokemonStatusEffect.PARALYZED);

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }



    public void scorching_sands(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.2;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.BURNED);

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void bug_buzz(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }

    public void quiver_dance(Player attacker,  Player defender, Move move) {
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackerPoke.setSpAtk(1.5 * attackerPoke.getSpAtk());
        attackerPoke.setSpDef(1.5 * attackerPoke.getSpDef());
        attackerPoke.setSpe(1.5 * attackerPoke.getSpe());

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }


    public void shadow_claw(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    //todo
    public void u_turn(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);

//        double Accur = move.getAccuracy();
//        double effectAccur = 0.2;
//
//        // Get poke
//        Pokemon attackPoke = attacker.getCurrPokemon();
//        Pokemon defenderPoke = defender.getCurrPokemon();
//
//        double damage = 0.0;
//        //deal damage or effect based on accuracy
//        boolean hit = false;
//
//        if (AccuracyCheck(Accur)) {
//            hit = true;
//            damage = calcDamage(attackPoke, defenderPoke, move);
//        }
//
//        defenderPoke.receiveDamage(damage);
    }

        // I believe this should call the switch move if it lands

    public void toxic(Player attacker,  Player defender, Move move) {
//        //get poke
//        double Accur = move.getAccuracy();
//        if (AccuracyCheck(Accur)) {
            Pokemon attackerPoke = attacker.getCurrPokemon();
            Pokemon defenderPoke = defender.getCurrPokemon();

            defenderPoke.setStatusEffect(PokemonStatusEffect.TOXIC);

            printStatusMoveMessage(attackerPoke, defenderPoke, move);


    }


    //todo
    //new status, immune (priority = 2)
    public void protect(Player attacker,  Player defender, Move move) {
        Pokemon attackPoke = attacker.getCurrPokemon();
        attackPoke.setProtectState(true);

    }


    public void play_rough(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void diamond_storm(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    //todo
    //test on set move base power
    public void gyro_ball(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();


        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            double pow = Math.ceil(0.5 * (defenderPoke.getSpe() - attackPoke.getSpe()));
            move.setBasePower(pow);
            if (pow < 21) {
                move.setBasePower(20);
            }
            damage = calcDamage(attackPoke, defenderPoke, move);
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void explosion(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();


        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            attackPoke.setHp(0);


        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,hit);
    }


    //todo
    //strange to change speed twice
    public void curse(Player attacker,  Player defender, Move move) {
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackerPoke.setSpe(0.66 * attackerPoke.getSpe());
        attackerPoke.setSpe(1.5 * attackerPoke.getSpe());
        attackerPoke.setDef(1.5 * attackerPoke.getDef());
        attackerPoke.setSpDef(1.5 * attackerPoke.getSpDef());

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }


    public void body_slam(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.2;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.PARALYZED);

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }

    //Todo
    //code in check turn
    // priority = 1
    public void ice_shard(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void night_slash(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void poison_jab(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.2;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.TOXIC);

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void poltergeist(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void dazzling_gleam(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void hidden_power_fire(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void calm_mind(Player attacker,  Player defender, Move move) {
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackerPoke.setSpAtk(1.5 * attackerPoke.getSpAtk());
        attackerPoke.setSpDef(1.5 * attackerPoke.getSpDef());

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }


    public void dual_wingbeat(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void draco_meteor(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();


        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;

        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);

            attackPoke.setSpAtk(0.5 * attackPoke.getSpAtk());
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,hit);
    }


    public void megahorn(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }

    //todo
    //code in check turn
    // priority = 1
    public void aqua_jet(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void aura_sphere(Player attacker,  Player defender, Move move) {
        simplemove(attacker,defender,move);
    }


    public void sludge_wave(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.2;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.TOXIC);

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void earth_power(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.3;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                attackPoke.setSpAtk(1.5 * attackPoke.getSpAtk());

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void thunder_fang(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.2;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setStatusEffect(PokemonStatusEffect.PARALYZED);

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    public void will_o_wisp(Player attacker,  Player defender, Move move) {
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        defenderPoke.setStatusEffect(PokemonStatusEffect.BURNED);

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }



    public void dark_pulse(Player attacker,  Player defender, Move move) {
        double Accur = move.getAccuracy();
        double effectAccur = 0.3;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        boolean hit = false;
        boolean effectHit = false;
        if (AccuracyCheck(Accur)) {
            hit = true;
            damage = calcDamage(attackPoke, defenderPoke, move);
            if(AccuracyCheck(effectAccur)){
                effectHit = true;
                defenderPoke.setSpDef(0.66 * defenderPoke.getSpDef());

            }
        }

        defenderPoke.receiveDamage(damage);

        // Print move message
        printAttackMessage(attackPoke, defenderPoke, move, hit, damage,effectHit);
    }


    // TODO : sequence
    public void clear_smog(Player attacker, Player defender, Move move){
        Pokemon defenderPoke = defender.getCurrPokemon();
        simplemove(attacker,defender,move);
        defenderPoke.resetstatsNoHp();
    }

    public void shadow_ball(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }

    public void hydro_pump(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }

    public void swords_dance(Player attacker, Player defender, Move move){
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackerPoke.setAtk(2* attackerPoke.getAtk());

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }

    public void titanic_rage(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }

    //second priority
    public void ackerman_insight(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }


    public void wild_growth(Player attacker, Player defender, Move move){
        //get poke
        Pokemon attackerPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackerPoke.setSpe(1.5 * attackerPoke.getSpe());
        attackerPoke.setSpAtk(1.5 * attackerPoke.getSpAtk());

        printStatusMoveMessage(attackerPoke,defenderPoke,move);
    }


    public void zen_headbutt(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }


    public void shadow_rush(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }


    public void seed_bomb(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }

    public void flash_cannon(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }


    public void shuck_you_up(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }


    public void surf(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }

    public void rock_slide(Player attacker, Player defender, Move move){
        simplemove(attacker,defender,move);
    }


    // complete turn
        // prompt users for input (switch, 1 of 4 attacks, or quit)
        // if one user chooses to switch pokemon, that pokemon switches out first, the other pokemon then gets the opportunity to use a move
        // if both switch, then the pokemon's speed is compared. the faster one switches out first
        // if both pokemon attack, the faster pokemon attacks first. If the other pokemon's health falls below 0 after the move is used it does not attack back. Otherwise, the game updates changes in status or item checks, the other pokemon attacks back and the game again checks for updates in status or items
        // after this both pokemon's end of turn effects kick in such as toxic dealing damage and leftovers healing a pokemon's health

        // methods:
        // select moves,   added
        // switch,         added
        // switch_reset,   added
        // thorough_reset, added
        // quit
        // check_first_move

        // Readinput:

        // readstring
        // readInt
        // readdouble
        // ...

    }


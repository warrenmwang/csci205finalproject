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
    private HashMap<String,Move> allMoves;

    // TODO Constructor
    /**
     * Constructor
     */
    public MovesInventory() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{
        allMultipliers = new HashMap<>();
        allTypes = new ArrayList<>();
        dontusemeMults = new ArrayList<>();

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


        // load in numbers for mult
        dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(2.0);dontusemeMults.add(1.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(1.0);dontusemeMults.add(2.0);dontusemeMults.add(2.0);dontusemeMults.add(0.5);dontusemeMults.add(1.0);

        // initialize the allMultipliers
        initializeDamageParam();

        DataLoader data = new DataLoader();
        this.allMoves = data.getTESTSETMoves();

    }

    // getter methods
    public HashMap<String, HashMap<String, Double>> getAllMultipliers() { return this.allMultipliers; }
    public ArrayList<String> getAllTypes() { return this.allTypes; }
    public Move getMove(String moveName) { return this.allMoves.get(moveName);}



    Boolean AccuracyCheck(double MoveAccur){
        if(new Random().nextInt(1000) < 1000 * MoveAccur){
            return true;}
        else{
            return false;}
        }



    public double calcDamage(Pokemon attacker, Pokemon defender, Move move){

        double moveDmg = move.getBasePower();
        String moveType = move.getType();
        String designation = move.getDesignation();

        //accounts for critical hits
        double criticalMult = new Random().nextInt(24);
        if (criticalMult == 17.0){
            criticalMult = 2;
            System.out.println("Wow! A critical hit!");
        }
        else {criticalMult = 1;}

        // TODO
        // check type of move against defender's both types

        double stab = 1;
        if (moveType.equals(attacker.getType1()) || moveType.equals(attacker.getType2())){
            stab = 1.5;
        }
        double MoveTypeModifier = damageParam(move.getType(),defender.getType2()) * damageParam(move.getType(),defender.getType1());
        double modifier = MoveTypeModifier * stab;

        //-------------
        double attack;
        double defence;
        if (designation.equals("physical")){
            attack = attacker.getAtk();
            if (attacker.getStatusEffect().equals(PokemonStatusEffect.BURNED)){
                attack = attack * 0.5;
            }
            defence = defender.getDef();
        }
        else if (designation.equals("special")) {
            attack = attacker.getSpAtk();
            defence = defender.getSpDef();
        }
        else {
            attack = 0;
            defence = 1;
            modifier = 0;
        }
        //-------------

        double dmg;
        dmg = ((42 * moveDmg * attack / defence)/50 + 2); //bulk of damage calculation


        double dmgVariance = (new Random().nextInt(150)+850)/1000.0;;
        dmg = dmg * dmgVariance;
        dmg = dmg * modifier * criticalMult;
//      System.out.println(stab);
//        System.out.println(criticalMult);
//        System.out.println(dmgVariance);
//        System.out.println(modifier);
//        System.out.println(dmg);
        return Math.ceil(dmg); //rounds up damage to prevent endless battles
    }

        // loads up the nested hashmap structure holding the multiplier values
        public void initializeDamageParam(){
            int i = 0;
            for(String s1 : allTypes){
                allMultipliers.put(s1, new HashMap<>());
                for(String s2 : allTypes){
                    allMultipliers.get(s1).put(s2, dontusemeMults.get(i));
                    i++;
                }
            }
        }

    // returns the multiplier value at the intersection of the two moves given
    public double damageParam(String attackType, String defendType){
        return allMultipliers.get(attackType).get(defendType);
    }
    

    //power: 140, accur: 0.9, FIRE, special, half user Spatk
    public void overheat(Player attacker, Player defender,Move move) {
        // Hard
        double Accur = 0.9;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();


        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);

            // Move effect of halfing the special attack
            attacker.getCurrPokemon().setSpAtk(attackPoke.getSpAtk() * 0.5);
        }


        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }


    //power: 75, acc: 1, GRASS, special, heal half the damage to attacker
    public void giga_drain(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        //Special effect: attacker heal half of the damage
       attackPoke.heal(0.5 * damage);
    }

    //power: 90, accur: 1, FIGHTING, physical, no effect
    public void skyuppercut(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

    }

    //statuc, double user speed
    public void agility(Player attacker, Player defender,Move move){
        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackPoke.setSpe(2 * attackPoke.getSpe());
    }


    //power: 110, accur: 1, GROUND, physical
    public void earthquake(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //power: 90, Accur: 1, PSYCHIC, special, 50% chance to lower defender's SpDef by 33%
    public void psychic(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);

            //50% chance to lower defender's SpDef by 33%
            double effectAccur = 0.5;
            if(AccuracyCheck(effectAccur)){
                defenderPoke.setSpDef(0.667 * defenderPoke.getSpDef());
            }
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //status, heal 50% maxHp
    public void recover(Player attacker, Player defender,Move move){
        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        attackPoke.heal(0.5 * attackPoke.getMaxHp() );
    }

    //power: 70, Accur: 1, ICE, special
    public void hidden_power_ice(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //power: 110, Accur:0.9, ROCK, physical
    public void stone_edge(Player attacker, Player defender,Move move){
        double Accur = 0.9;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //power: 90, Accur: 1, ICE, special, 30% chance to lower defender's SpAtk by 33%
    public void ice_beam(Player attacker, Player defender,Move move){
        double Accur = 1.0;


        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0.0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);

            //30% chance to lower defender's SpAtk by 33%
            double effectAccur = 0.3;
            if(AccuracyCheck(effectAccur)){
                defenderPoke.setSpDef(0.667 * defenderPoke.getSpDef());
            }
        }

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //power: 70, Accur: 1, GRASS, special
    public void hidden_power_grass(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //power: 90, Accur: 1, ELECTRIC, special, 20% set defender statuc to paralysis
    public void thunderbolt(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke, defenderPoke, move);

            double effectAccur = 0.2;
            if (AccuracyCheck(effectAccur)) {
                defenderPoke.setStatusEffect(PokemonStatusEffect.PARALYZED);
            }
        }
        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //power: 110, Accur:0.9, FIRE, special, 30% lower defender atk by 33%
    public void heat_wave(Player attacker, Player defender,Move move){
        double Accur = 0.9;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke, defenderPoke, move);

            double effectAccur = 0.3;
            if (AccuracyCheck(effectAccur)) {
                defenderPoke.setAtk(0.667 * defenderPoke.getAtk());
            }
        }
        // Pokemeon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }


    //power: 100, Accur: 1, FLYING, special
    public void air_slash(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemon receives damage, check if alive
        defenderPoke.receiveDamage(damage);
    }

    //power: 70, Accur: 1, ELECTRIC, user switch out after move is used
    public void volt_switch(Player attacker, Player defender,Move move){
        double Accur = 1.0;

        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;
        //deal damage or effect based on accuracy
        if (AccuracyCheck(Accur)) {
            damage = calcDamage(attackPoke,defenderPoke,move);}

        // Pokemon receives damage, check if alive
        defenderPoke.receiveDamage(damage);

        if(attacker.getNumberOfPokemon() >=2){
            attacker.switchCurrPokemon(2);}
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

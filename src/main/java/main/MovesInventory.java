package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MovesInventory {
    // store the numbers from that matrix of type x type
    private ArrayList<Double> dontusemeMults;
    // multipliers matrix
    private HashMap<String, HashMap<String, Double>> allMultipliers;
    private ArrayList<String> allTypes;


    // TODO Constructor
    /**
     * Constructor
     */
    public MovesInventory(){
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
    }

    // getter methods
    public HashMap<String, HashMap<String, Double>> getAllMultipliers() { return this.allMultipliers; }
    public ArrayList<String> getAllTypes() { return this.allTypes; }

//


//    Boolean AccuracyCheck(Pokemon attacker, double MoveAccur){
//        if(new Random().nextInt(1000) < 1000 * MoveAccur){
//            return true;}
//        else{
//            return false;}
//        }



//    public double calcDamage(Pokemon attacker, Pokemon defender, Move m0ve){
//        double moveDmg = move.getBaseDamage();
//        String moveType = move.getType();
//        String designation = move.getDesignation();
//        double typeModifier = *check effectiveness*;
//        //-------------
//        double attack;
//        double defence;
//        if (designation.equals("physical")){
//            attack = attacker.getAtk();
//            if attacker.getStatus.equals("BURNED"){
//                attack = attack * 0.5;
//            }
//            defence = defender.getDef();
//        }
//        else if (designation.equals("special")) {
//            attack = attacker.getSpAtk();
//            defence = defender.getDef();
//        }
//        else {
//            attack = 0;
//            defence = 1;
//            modifier = 0;
//        }
//        //-------------
//
//        double dmg;
//        dmg = ((42 * moveDmg * attack / defence)/50 + 2);
//        double dmgVariance = Random().nextInt(150)+850;;
//        dmg = dmg * dmgVariance;
//        if (moveType.equals(attacker.getType())){
//            dmg = dmg * 1.5;
//        }
//        dmg = dmg * typeModifier;
//        return dmg;
//    }

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
    

    public void overHeat(Player attacker, Player defender) {
        
        // Get poke
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defenderPoke = defender.getCurrPokemon();

        double damage = 0;

        //deal damage or effect based on accuracy
        if (AccuracyCheck) {
            damage = calcDamage(Pokemon attackPoke, Pokemon defenderPoke, Move m0ve);

            // Move effect of halfing the special attack
            attacker.getCurrPokemon().setSpAtk(attacker.getCurrPokemon().getSpAtk() * 0.5);
        }


        // Pokemeon receives damage, check if alive
        defenderPoke.setHp(defenderPoke.getHP() - damage);
        defenderPoke.checkIsAlive();

    }


    // complete turn
    // prompt users for input (switch, 1 of 4 attacks, or quit)
    // if one user chooses to switch pokemon, that pokemon switches out first, the other pokemon then gets the opportunity to use a move
    // if both switch, then the pokemon's speed is compared. the faster one switches out first
    // if both pokemon attack, the faster pokemon attacks first. If the other pokemon's health falls below 0 after the move is used it does not attack back. Otherwise, the game updates changes in status or item checks, the other pokemon attacks back and the game again checks for updates in status or items
    // after this both pokemon's end of turn effects kick in such as toxic dealing damage and leftovers healing a pokemon's health









        }

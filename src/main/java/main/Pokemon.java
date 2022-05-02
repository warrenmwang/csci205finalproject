package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Pokemon {
    private String id;
    private String name;
    private String botImage;
    private String playerImage;
    private ArrayList<Double> currStats;
    private ArrayList<String> moves;
    private ArrayList<String> types;
    private String item;
    private PokemonStatusEffect statusEffect;
    private int toxicTurn;
    private boolean isAlive;
    private ArrayList<Double> originalStats;
    private int turnsActive;
    private boolean protectState;

    /**
     * Constructor, initialize Pokemon with given information.
     * Defaults: status is NO EFFECT and isAlive is True
     * @param id
     * @param name
     * @param bot_image
     * @param stats
     * @param moves
     */
    public Pokemon(String id, String name, String bot_image, ArrayList<Double> stats, ArrayList<String> moves, ArrayList<String> types, String item,String player_image){
        this.id = id;
        this.name = name;
        this.botImage = bot_image;
        this.currStats = new ArrayList<>(stats);
        this.moves = moves;
        this.types = types;
        this.item = item;
        this.statusEffect = PokemonStatusEffect.NO_EFFECT;
        this.isAlive = true;
        this.originalStats = new ArrayList<>(stats);
        this.turnsActive = 0;
        this.protectState = false;
        this.playerImage = player_image;

        this.toxicTurn = 0;

    }
    final private int HP = 0;
    final private int ATK = 1;
    final private int DEF = 2;
    final private int SPATK = 3;
    final private int SPDEF = 4;
    final private int SPEED = 5;


    // Getter Methods
    public boolean getIsAlive() { return this.isAlive; }
    public String getName() { return this.name;}
    public String getID() {return this.id;}
    public PokemonStatusEffect getStatusEffect() { return this.statusEffect; }
    public Double getHP() { return this.currStats.get(HP); }
    public Double getAtk() { return this.currStats.get(ATK); }
    public Double getDef() { return this.currStats.get(DEF); }
    public Double getSpAtk() { return this.currStats.get(SPATK); }
    public Double getSpDef() { return this.currStats.get(SPDEF); }
    public Double getSpe() { return this.currStats.get(SPEED); }
    public ArrayList<Double> getStats() { return this.currStats; }
    public ArrayList<String> getTypes() { return this.types; }
    public ArrayList<String> getMoves() { return this.moves; }
    public String getItems() { return this.item; }
    public int getTurnsActive() { return this.turnsActive;}

    public String getBotImage() {return this.botImage;}
    public String getPlayerImage() {return this.playerImage;}

    public Double getMaxHp() {return this.originalStats.get(HP);}


    public String getType1() { return types.get(0);}
    public String getType2() { return types.get(1);}
    //public double getAccurRate() { return 1.0;}

    public String getMove(int index){
        return moves.get(index);
    }
    public boolean getProtectState() { return this.protectState;}
    public int getToxicTurn() {return toxicTurn;}

    // Setter/Update Methods
    public void setStatusEffect(PokemonStatusEffect statusEffect){ this.statusEffect = statusEffect; }
    public void setStats(ArrayList<Double> stats) { this.currStats = stats; }
    public void setIsAlive(boolean state) { this.isAlive = state; }
    public void setHp(double hp) { this.currStats.set(HP,hp);}
    public void setAtk(double Atk) {this.currStats.set(ATK, Atk);}
    public void setDef(double Def) { this.currStats.set(DEF,Def);}
    public void setSpAtk(double SpAtk) {this.currStats.set(SPATK,SpAtk);}
    public void setSpDef(double SpDef) {this.currStats.set(SPDEF,SpDef);}
    public void setSpe(double spe) { this.currStats.set(SPEED, spe);}
    public void setTurnsActive(int turn) {this.turnsActive = turn;}
    public void setProtectState(boolean bool) { this.protectState = bool;}
    public void setToxicTurn(int turn) {this.toxicTurn = turn;}
    public void incToxicTurn() { this.toxicTurn += 1;}



    public void resetstatsNoHp(){
        double currentHp = this.getHP();
        this.setStats(new ArrayList<>(this.originalStats));
        this.setHp(currentHp);
        this.statusEffect = PokemonStatusEffect.NO_EFFECT;
    }

    public void resetAllstats(){
        this.setStats(new ArrayList<>(this.originalStats));
        this.statusEffect = PokemonStatusEffect.NO_EFFECT;
    }


    // check if alive
    public void checkIsAlive(){
        if(this.getHP() <= 0){
            this.isAlive = false;
        }
    }

    // take damage as specified in input
    public void receiveDamage(double damage){
        this.setHp(this.getHP()-damage);
        this.checkIsAlive();
    }

    // heal amount specified in input
    public void heal(double heal){
        if((this.getMaxHp()-this.getHP() )< heal){
            this.setHp(this.getMaxHp());
        } else{
            this.setHp(this.getHP() + heal);
        }
    }

    // reset the stats that get reset when this pokemon is switched
    public void switch_reset(){
        ArrayList<Double> tempStats = this.originalStats;
        tempStats.set(HP,this.getHP());
        this.setStats(tempStats);
        this.setStatusEffect(PokemonStatusEffect.NO_EFFECT);
        this.setTurnsActive(-1);
    }

    // reset this pokemon completely
    public void thorough_reset(){
        this.setStats(this.originalStats);
        this.setStatusEffect(PokemonStatusEffect.NO_EFFECT);
        this.setIsAlive(true);
        this.setTurnsActive(0);
    }




    /**
     * TODO mainly for testing purposes, probably remove later.
     * @return
     */
    @Override
    public String toString() {
        return String.format("id: %s\n" +
                "name: %s\n"+
//                "image: %s\n" +
                "currStats: %s\n" +
                "origStats: %s\n" +
                "types: %s\n" +
                "moves: %s\n" +
                "statusEffect: %s\n" +
                "isAlive: %s\n" +
                "turnsActive: %s\n"
                , id, name, currStats, originalStats, types, moves, statusEffect, isAlive, turnsActive);
    }

    public String toSmallString(){
        return String.format(
                "name: %s\n"+
                "Stats: %s\n" +
                "types: %s\n" +
                "moves: %s\n",name,originalStats,types,moves);

    }
}

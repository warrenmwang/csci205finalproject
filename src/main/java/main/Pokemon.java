package main;

import java.util.ArrayList;

public class Pokemon {
    private String id;
    private String name;
    private String image;
    private ArrayList<Double> stats;
    private ArrayList<String> moves;
    private ArrayList<String> types;
    private String item;
    private PokemonStatusEffect statusEffect;
    private boolean isAlive;
    private ArrayList<Double> originalStats;

    /**
     * Constructor, initialize Pokemon with given information.
     * Defaults: status is NO EFFECT and isAlive is True
     * @param id
     * @param name
     * @param image
     * @param stats
     * @param moves
     */
    public Pokemon(String id, String name, String image, ArrayList<Double> stats, ArrayList<String> moves, ArrayList<String> types, String item){
        this.id = id;
        this.name = name;
        this.image = image;
        this.stats = stats;
        this.moves = moves;
        this.types = types;
        this.item = item;
        this.statusEffect = PokemonStatusEffect.NO_EFFECT;
        this.isAlive = true;
        this.originalStats = stats;

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
    public Double getHP() { return this.stats.get(HP); }
    public Double getAtk() { return this.stats.get(ATK); }
    public Double getDef() { return this.stats.get(DEF); }
    public Double getSpAtk() { return this.stats.get(SPATK); }
    public Double getSpDef() { return this.stats.get(SPDEF); }
    public Double getSpe() { return this.stats.get(SPEED); }
    public ArrayList<Double> getStats() { return this.stats; }
    public ArrayList<String> getTypes() { return this.types; }
    public ArrayList<String> getMoves() { return this.moves; }
    public String getItems() { return this.item; }

    public Double getMaxHp() {return this.originalStats.get(HP);}


    public String getType1() { return types.get(0);}
    public String getType2() { return types.get(1);}
    //public double getAccurRate() { return 1.0;}

    public String getMove(int index){
        return moves.get(index);
    }



    // Setter/Update Methods
    public void setStatusEffect(PokemonStatusEffect statusEffect){ this.statusEffect = statusEffect; }
    public void setStats(ArrayList<Double> stats) { this.stats = stats; }
    public void setIsAlive(boolean state) { this.isAlive = state; }
    public void setHp(double hp) { this.stats.set(HP,hp);}
    public void setAtk(double Atk) {this.stats.set(ATK, Atk);}
    public void setDef(double Def) { this.stats.set(DEF,Def);}
    public void setSpAtk(double SpAtk) {this.stats.set(SPATK,SpAtk);}
    public void setSpDef(double SpDef) {this.stats.set(SPDEF,SpDef);}
    public void setSpe(double spe) { this.stats.set(SPEED, spe);}



    public void checkIsAlive(){
        if(this.getHP() <= 0){
            this.isAlive = false;
        }
    }


    public void receiveDamage(double damage){
        this.setHp(this.getHP()-damage);
        this.checkIsAlive();
    }

    public void heal(double heal){
        if((this.getMaxHp()-this.getHP() )< heal){
            this.setHp(this.getMaxHp());
        } else{
            this.setHp(this.getHP() + heal);
        }
    }

    public void switch_reset(){
        ArrayList<Double> tempStats = this.originalStats;
        tempStats.set(HP,this.getHP());
        this.setStats(tempStats);
        this.setStatusEffect(PokemonStatusEffect.NO_EFFECT);
    }

    public void thorough_reset(){
        this.setStats(this.originalStats);
        this.setStatusEffect(PokemonStatusEffect.NO_EFFECT);
        this.setIsAlive(true);
    }



    /**
     * TODO mainly for testing purposes, probably remove later.
     * @return
     */
    @Override
    public String toString() {
        return String.format("id: %s\nname: %s\nimage: %s\nstats: %s\nmoves: %s\nstatusEffect: %s\nisAlive: %s\n", id, name, image, stats, moves, statusEffect, isAlive);
    }
}

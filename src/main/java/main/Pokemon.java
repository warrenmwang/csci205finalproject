package main;

import java.util.ArrayList;

public class Pokemon {
    private String id;
    private String name;
    private String image;
    private ArrayList<Double> stats;
    private ArrayList<String> moves;
    private PokemonStatusEffect statusEffect;
    private boolean isAlive;

    /**
     * Constructor, initialize Pokemon with given information.
     * Defaults: status is NO EFFECT and isAlive is True
     * @param id
     * @param name
     * @param image
     * @param stats
     * @param moves
     */
    public Pokemon(String id, String name, String image, ArrayList<Double> stats, ArrayList<String> moves){
        this.id = id;
        this.name = name;
        this.image = image;
        this.stats = stats;
        this.moves = moves;
        this.statusEffect = PokemonStatusEffect.NO_EFFECT;
        this.isAlive = true;
    }

    // Getter Methods
    public boolean getIsAlive() { return this.isAlive; }
    public String getName() { return this.name;}
    public String getID() {return this.id;}
    public PokemonStatusEffect getStatusEffect() { return this.statusEffect; }
    public Double getHP() { return this.stats.get(0); }
    public Double getAtk() { return this.stats.get(1); }
    public Double getDef() { return this.stats.get(2); }
    public Double getSpAtk() { return this.stats.get(3); }
    public Double getSpDef() { return this.stats.get(4); }
    public Double getSpe() { return this.stats.get(5); }
    public ArrayList<Double> getStats() { return this.stats; }



    // Setter/Update Methods
    public void setStatusEffect(PokemonStatusEffect statusEffect){ this.statusEffect = statusEffect; }
    public void setStats(ArrayList<Double> stats) { this.stats = stats; }
    public void setIsAlive(boolean state) { this.isAlive = state; }
    public void setHp(double hp) { this.stats.set(0,hp);}



    public void checkIsAlive(){
        if(this.getHP() <= 0){
            this.isAlive = false;
        }
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

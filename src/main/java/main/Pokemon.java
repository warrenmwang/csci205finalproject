package main;

import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;
    private String image;
    private ArrayList<Integer> stats;
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
    public Pokemon(int id, String name, String image, ArrayList<Integer> stats, ArrayList<String> moves){
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
    public PokemonStatusEffect getStatusEffect() { return this.statusEffect; }
    public int getHP() { return this.stats.get(0); }
    public int getAtk() { return this.stats.get(1); }
    public int getDef() { return this.stats.get(2); }
    public int getSpAtk() { return this.stats.get(3); }
    public int getSpDef() { return this.stats.get(4); }
    public int getSpe() { return this.stats.get(5); }
    public ArrayList<Integer> getStats() { return this.stats; }


    // Setter/Update Methods
    public void setStatusEffect(PokemonStatusEffect statusEffect){ this.statusEffect = statusEffect; }
    public void setStats(ArrayList<Integer> stats) { this.stats = stats; }
    public void setIsAlive(boolean state) { this.isAlive = state; }

    /**
     * TODO mainly for testing purposes, probably remove later.
     * @return
     */
    @Override
    public String toString() {
        return String.format("id: %d\nname: %s\nimage: %s\nstats: %s\nmoves: %s\nstatusEffect: %s\nisAlive: %s\n", id, name, image, stats, moves, statusEffect, isAlive);
    }
}

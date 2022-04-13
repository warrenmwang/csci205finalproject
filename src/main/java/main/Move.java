package main;

import java.util.HashMap;
import java.util.Random;

public class Move {

    public double damageParam(String attackType, String defendType){
        return 0.5;
    }



    public void overHeat(Player attacker, Player defender){
        // Data from the sheet
        final double BaseDamage = 140;
        final double BaseAccur = 0.9;
        final String Type = "FIRE";

        //Get the pokemon and their type information
        Pokemon attackPoke = attacker.getCurrPokemon();
        Pokemon defendePoke = defender.getCurrPokemon();
        String defendType = defendePoke.getType();
        Double DefAccurRate = defendePoke.getAccurRate();

        //Calculate the damage
        double damage = 0;

        if(new Random().nextInt(1000) < 1000 * BaseAccur * (1+DefAccurRate)){
            damage = BaseDamage * damageParam(Type,defendType);

            // Move effect of halfing the special attack
            attacker.getCurrPokemon().setSpAtk(attacker.getCurrPokemon().getSpAtk() * 0.5);
        }



        // Pokemeon receives damage, check if alive
        defendePoke.setHp(defendePoke.getHP() - damage);
        defendePoke.checkIsAlive();
    }
}
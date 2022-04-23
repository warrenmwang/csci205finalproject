/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Warren Wang
 * Section: 10am
 * Date: 4/14/22
 * Time: 5:47 PM
 *
 * Project: csci205finalproject
 * Package: main
 * Class: Move
 *
 * Description:
 *
 *
 ****************************************
 */
package main;

import java.util.Scanner;

public class Move {
    private String name;
    private double basePower;
    private double accuracy;
    private String Type;
    private String designation;
    private String specialEffect;

    /**
     * Constructor 1
     *
     * @param Name
     * @param csvCopy comma separated 5 values
     */
    public Move(String Name, String csvCopy){
        Scanner scnr = new Scanner(csvCopy);
        scnr.useDelimiter(",");
        this.name = Name;
        this.basePower = Double.parseDouble(scnr.next());   // NOTE: for Switch move, this holds the index of the Pokemon in their team arraylist that the Player wants to switch their current Pokemon with
        this.accuracy = Double.parseDouble(scnr.next());
        this.Type = scnr.next();
        this.designation = scnr.next();
        this.specialEffect = scnr.next();
    }

    // Constructor 2
    public Move(String Name, double basePower, double accuracy, String Type, String designation, String specialEffect){
        this.name = Name;
        this.basePower = basePower;
        this.accuracy = accuracy;
        this.Type = Type;
        this.designation = designation;
        this.specialEffect = specialEffect;
    }

    public String getName(){ return this.name;}

    public double getBasePower() {return this.basePower;}

    public double getAccuracy() {return this.accuracy;}

    public String getType() {return this.Type;}

    public String getDesignation() {return this.designation;}

    public String getSpecialEffect() {return this.specialEffect;}

    public void setBasePower(double basePower) {this.basePower = basePower;}


    @Override
    public String toString(){
        String Description;
        if(this.designation.equals("status")){
            Description = String.format("%s is a %s type %s move that has a basepower of %.1f", this.name, this.Type, this.designation, this.basePower);
        } else {
            Description = String.format("%s is a %s type %s move that has a basepower of %.1f ,accuracy of %.1f", this.name, this.Type, this.designation, this.basePower, this.accuracy);
        }
        if(!(this.specialEffect.equals("NULL"))){
            Description += ", and an effect that will " + this.specialEffect;
        }
        return Description;
    }
}
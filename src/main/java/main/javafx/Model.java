/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring2022
 * Instructor: Brian King
 * Section: 1 - 10 am
 *
 * Name: Warren Wang
 * Date: 01/26/2022
 *
 * Lab / Assignment:
 *
 * Description:
 *
 * *****************************************/


package main.javafx;

import main.BattleMacro;

public class Model {

    private BattleMacro battleMacro;

    // getter method
    public BattleMacro getBattleMacro(){ return battleMacro; }

    /**
     * Constructor
     */
    public Model(){
        try {
            battleMacro = new BattleMacro();
        }catch(Exception e){
            // blah
        }
    }
}

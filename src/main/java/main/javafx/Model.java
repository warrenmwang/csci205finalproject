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
    Thread t;


    /**
     * Constructor
     */
    public Model(BattleMacro battleMacro){

        this.battleMacro = battleMacro;

        Runnable r = () -> {
            try {
                battleMacro.mainGameLoop();
            } catch (Exception e) {}
        };
        t = new Thread(r);
        t.setDaemon(true);
    }


    public void run(){
        t.start();
    }
}

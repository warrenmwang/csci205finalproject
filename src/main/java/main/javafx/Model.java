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

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Model {

    public Thread getT() {
        return t;
    }

    private BattleMacro battleMacro;
    Thread t;
//
//    // getter method
//    public BattleMacro getBattleMacro(){ return battleMacro; }

    public void setBattleMacro(BattleMacro bm){battleMacro = bm;}

    /**
     * Constructor
     */
    public Model(BattleMacro battleMacro){

        this.battleMacro = battleMacro;

        Runnable r = () -> {
            try {
                battleMacro.mainGameLoop();
            } catch (Exception e) {
                // blah
            }
        };
        t = new Thread(r);
        t.setDaemon(true);

    }


    public void run(){
        t.start();
    }


    public void getNewThread(){
        Runnable r = () -> {
            try {
                battleMacro.mainGameLoop();
            } catch (Exception e) {
                // blah
            }
        };
        t = new Thread(r);
        t.setDaemon(true);
    }

    public void stop(){
        t.interrupt();

    }
}



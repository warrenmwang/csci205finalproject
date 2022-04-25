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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Model {

    private BattleMacro battleMacro;

    // getter method
    public BattleMacro getBattleMacro(){ return battleMacro; }

    /**
     * Constructor
     */
    public Model() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException{

        battleMacro = new BattleMacro();

        // TODO might have to use a thread
        Runnable r = () -> {
            try {
                battleMacro.mainGameLoop();
            } catch (Exception e) {
                // blah
            }
        };
        Thread t = new Thread(r);
        t.start();

    }
}

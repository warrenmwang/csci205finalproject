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

import javafx.scene.control.TextArea;
import main.BattleMacro;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Controller {

    private Model model;
    private View view;
    private BattleMacro battleMacro;
    private ByteArrayOutputStream newSysOut;

    /**
     * Constructor of the JavaFX controller
     * @param model
     * @param view
     */
    public Controller(Model model, View view){
        // before creating out main game, change sys.out to something we capture
        PrintStream sysOutOrig = System.out;
        newSysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newSysOut));

        // create the javafx
        this.view = view;

        // create the game
        this.model = model;

        battleMacro = this.model.getBattleMacro();

        initEventHandlers();
    }

    // TODO this entire function
    public void initEventHandlers(){
        // put system.out in the textArea whenever new system.out is outputted
//        TextArea output = view.getOutputText();
        // TODO bind system.out to the javafx output
        // capture system.out

        view.getBtn1().setOnMouseClicked(Event -> {
            Runnable r = () -> {
                view.getOutputText().appendText("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                view.getOutputText().appendText(newSysOut.toString());
            };
            Thread t = new Thread(r);
            t.start();
        });

        // TODO: have 7 buttons for Attack, Switch, Forfeit, Move1, Move2, Move3, Move4

        // TODO when setting up the game, have a bottom box item be a swappable thing
        // that can be swapped with a 3 button box (Attk, Switch, Forfeit)
        // or with the 4 button box




        // get input from inputTextField and put into system.in
        // only send to system.in when user presses enter

        // restore system.out
//        System.setOut(sysOutOrig);
    }

}

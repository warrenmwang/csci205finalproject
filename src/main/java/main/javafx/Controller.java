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
import main.UserInput;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Controller {

    private Model model;
    private View view;
    private BattleMacro battleMacro;
    private ByteArrayOutputStream newSysOut;
//    private ByteArrayInputStream newSysIn;
    private String stringToPassIntoNewSysIn;

    //private UserInput playerUserInput;

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

        // also change sys.in to something we capture
//        InputStream sysInOrig = System.in;
//        stringToPassIntoNewSysIn = "";
//        newSysIn = new ByteArrayInputStream(stringToPassIntoNewSysIn.getBytes());
//        System.setIn(newSysIn);

        // create the javafx
        this.view = view;

        // create the game
        this.model = model;

        battleMacro = this.model.getBattleMacro();
        //playerUserInput = battleMacro.getBattleMicro().getUser().getUserInput();

        initEventHandlers();
    }

    public void initEventHandlers(){
        // put system.out in the textArea whenever new system.out is outputted
        // capture system.out

        // refresh the screen when we press the refresh button
        view.getBtn1().setOnMouseClicked(Event -> {
            refreshTextAreaWithSysOut();
        });

        // TODO SEND INPUT FROM TEXTFIELD TO SYSTEM.IN
        // get user input from the textField box
        view.getInputTextField().setOnAction(event -> {
            // read in user input from System.in
            stringToPassIntoNewSysIn = view.getInputTextField().getText();

            // TODO remove me
            view.getTestBottomLabel().setText(stringToPassIntoNewSysIn);

            UserInput.setUSERINPUT(stringToPassIntoNewSysIn);
            UserInput.setCanGetUSERINPUT(true);

//            System.out.println("controller get userinput" + battleMacro.getBattleMicro().getUSERINPUT());
//            System.out.println("controller get cangetuserinput" + battleMacro.getBattleMicro().getCanGetUSERINPUT());
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){

            }
            refreshTextAreaWithSysOut();
        });


        // TODO: have 7 buttons for Attack, Switch, Forfeit, Move1, Move2, Move3, Move4

        // TODO when setting up the game, have a bottom box item be a swappable thing
        // that can be swapped with a 3 button box (Attk, Switch, Forfeit)
        // or with the 4 button box

        // TODO: when mouse hovers over move or any button, give more info
        //   on a floating popup above button, goes away when mouse is not hovering
        //   over button

        // TODO change pokemon pictures when pokemon are switched (switch move, or died)


        // get input from inputTextField and put into system.in
        // only send to system.in when user presses enter

        // restore system.out
//        System.setOut(sysOutOrig);
    }

    public void refreshTextAreaWithSysOut(){
        Runnable r = () -> {
            view.getOutputText().setText("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
            view.getOutputText().appendText(newSysOut.toString());
            view.getOutputText().setScrollTop(Double.MAX_VALUE); // set scrolled to bottom
        };
        Thread t = new Thread(r);
        t.start();
    }
}

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

public class Controller {

    private Model model;
    private View view;
    private BattleMacro battleMacro;

    /**
     * Constructor of the JavaFX controller
     * @param model
     * @param view
     */
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
        battleMacro = this.model.getBattleMacro();
    }
}

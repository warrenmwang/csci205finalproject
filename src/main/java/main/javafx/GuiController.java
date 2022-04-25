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

public class GuiController {
    public ViewGridPane view;

    public GuiController(ViewGridPane view){
        this.view = view;

        initEventHandler();
    }

    public void initEventHandler() {
        // ------- event handler -------
        view.getBtn1().setOnMouseClicked(event -> {
            view.setRoot(view.getRoot2());
        });

        view.getBtn2().setOnMouseClicked(event -> {
            view.setRoot(view.getRoot1());
        });
    }
}

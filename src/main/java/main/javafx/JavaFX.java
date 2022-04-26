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
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFX extends Application{

    private TextView view1;
    private BattleView view2;
    private GuiController controller2;

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed, but before the
     * start() method is invoked.
     */
    @Override
    public void init() throws Exception {
        String placeholder1 = "https://img.pokemondb.net/sprites/black-white/anim/back-normal/rotom-wash.gif";
        String placerholder2 = "https://img.pokemondb.net/sprites/heartgold-soulsilver/back-normal/bulbasaur.png";
        super.init();
        view1 = new TextView();
        view2 = new BattleView(placeholder1, placerholder2);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    @Override
    public void start(Stage primaryStage) {
        // give the primary stage to the controller to be able to switch scenes
        try {
            controller2 = new GuiController(view1, view2, primaryStage);
        }catch(Exception e){

        }
    }

    /**
     * Our standard main program for a JavaFX application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

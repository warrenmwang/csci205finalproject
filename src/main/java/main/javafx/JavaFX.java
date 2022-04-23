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
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFX extends Application{

    private View view;
    private Model model;
    private Controller controller;

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed, but before the
     * start() method is invoked.
     */
    @Override
    public void init() throws Exception {
        super.init();
        view = new View();
        model = new Model();
        controller = new Controller(model, view);
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
        Scene scene = new Scene(this.view.getRoot());
        primaryStage.setTitle("Pokemon BattleFactory Simulator");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Our standard main program for a JavaFX application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

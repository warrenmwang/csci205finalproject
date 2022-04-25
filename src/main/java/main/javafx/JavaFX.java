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

    private View view1;
    private ViewGridPane view2;
    private Model model;
    private Controller controller;
    private GuiController controller2;

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed, but before the
     * start() method is invoked.
     */
    @Override
    public void init() throws Exception {
        super.init();
//        view1 = new View();
        view2 = new ViewGridPane();
//        model = new Model();
//        controller = new Controller(model, view1);
        controller2 = new GuiController(view2);
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
        // original text based view
//        Scene scene = new Scene(this.view1.getRoot());

        // testing GridPane view
        Scene scene = new Scene(this.view2.getRoot());

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

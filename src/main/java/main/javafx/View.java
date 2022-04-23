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

import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class View {
    private HBox root;
    private TextField inputTextField;
    private Label inputTextFieldLabel;
    private TextArea outputText;
    private VBox vbox;

    private Button btn1;

    // getter methods
    public HBox getRoot(){ return root; }
    public TextArea getOutputText() { return outputText; }
    public Button getBtn1() { return btn1; }


    /**
     * Constructor
     */
    public View(){
        initSceneGraph();
        initStyling();
    }

    /**
     * Initialize Scene Variables
     */
    public void initSceneGraph(){
        root = new HBox();
        root.setId("root");

        inputTextField = new TextField();
        inputTextFieldLabel = new Label("User input here:");
        outputText = new TextArea("THIS IS WHERE OUTPUT WILL GO");
        vbox = new VBox();

        btn1 = new Button("Refresh");

        vbox.getChildren().add(inputTextFieldLabel);
        vbox.getChildren().add(inputTextField);
        vbox.getChildren().add(btn1);

        root.getChildren().add(vbox);
        root.getChildren().add(outputText);

    }

    /**
     * Style the Scene Variables
     */
    public void initStyling(){
        root.setPrefSize(500.0, 500.0);
        root.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
    }
}

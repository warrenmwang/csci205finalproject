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
    private Label testBottomLabel;
    private TextArea outputText;
    private VBox vbox;

    private Button btn1;

    // getter methods
    public HBox getRoot(){ return root; }
    public TextArea getOutputText() { return outputText; }
    public Button getBtn1() { return btn1; }
    public TextField getInputTextField(){ return inputTextField; }
    public Label getTestBottomLabel(){ return testBottomLabel; }

    // TODO: add all the buttons for the moves, attack, switch, forfeit
    //   2 pokemon sprite/picture things
    //   background image
    //   textboxes for now that show the 2 current poke's name and health
    //   textbox to show what round it is
    //   textbox (critical hit, who attacked who and how much damage)

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
        testBottomLabel = new Label("to be replaced");

        vbox.getChildren().add(inputTextFieldLabel);
        vbox.getChildren().add(inputTextField);
        vbox.getChildren().add(btn1);
        vbox.getChildren().add(testBottomLabel);

        root.getChildren().add(vbox);
        root.getChildren().add(outputText);

    }

    /**
     * Style the Scene Variables
     */
    public void initStyling(){
        root.setPrefSize(1000.0, 1000.0);
        root.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        outputText.setPrefSize(700.0, 900.0);
        outputText.setScrollTop(Double.MIN_VALUE); // set scrolled to bottom
    }
}

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

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Transition / End Game View
 *
 * Will be used in 3 places
 * 1. When game ends in the middle of the battle (player or bot loses)
 * 2. When player forfeits
 * 3. When player wants to see the rules
 */
public class TransitionAndEndGameView {

    private VBox root;
    private Button yesBtn;
    private Button noBtn;
    private TextArea textArea;
    private HBox yesNoBox;

    // GETTER METHODS
    public Button getYesBtn(){return yesBtn;}
    public Button getNoBtn(){return noBtn;}
    public VBox getRoot(){return root;}



    /**
     * Constructor
     */
    public TransitionAndEndGameView(){
        initSceneGraph();
        initSceneStyling();
    }

    public void initSceneGraph(){
        // instantiate vars
        root = new VBox();
        yesBtn = new Button("yes");
        noBtn = new Button("no");
        textArea = new TextArea("replace me");
        yesNoBox = new HBox();

        // add yes and no button to yes no hbox
        yesNoBox.getChildren().add(yesBtn);
        yesNoBox.getChildren().add(noBtn);

        // add things to root
        root.getChildren().add(textArea);
        root.getChildren().add(yesNoBox);
    }
    public void initSceneStyling(){
        root.setPrefSize(500,500);
        textArea.setPrefSize(500, 400);
    }

    /**
     * Updates the content in the bottom left text box with the given input
     * @param content
     */
    public void updateTextArea(String content){
        textArea.setText(content);
        textArea.setScrollTop(Double.MAX_VALUE);
    }
}

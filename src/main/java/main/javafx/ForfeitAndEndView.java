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

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



/**
 * Transition / End Game View
 *
 * Will be used in 3 places
 * 1. When game ends in the middle of the battle (player or bot loses)
 * 2. When player forfeits
 */
public class ForfeitAndEndView {

    private VBox root;
    private Button yesBtn;
    private Button noBtn;
    private Label textToDisplay;
    private HBox yesNoBox;
    private ImageView clown;
    private Label levelOfPlayerMsg;


    // GETTER METHODS
    public Button getYesBtn(){return yesBtn;}
    public Button getNoBtn(){return noBtn;}
    public VBox getRoot(){return root;}


    public ImageView getClown(){return clown;}
    public Label getlevelOfPlayerMsg(){return levelOfPlayerMsg;}

    /**
     * Constructor
     */
    public ForfeitAndEndView(){
        initSceneGraph();
        initSceneStyling();
    }

    public void initSceneGraph(){
        // instantiate vars
        root = new VBox();
        yesBtn = new Button("Yes");
        noBtn = new Button("No");
        textToDisplay = new Label("replace me");
        yesNoBox = new HBox();
        clown = new ImageView(new Image("https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/240/apple/325/clown-face_1f921.png"));
        levelOfPlayerMsg = new Label("");

        // add yes and no button to yes no hbox
        yesNoBox.getChildren().add(yesBtn);
        yesNoBox.getChildren().add(noBtn);

        // add things to root
        root.getChildren().add(textToDisplay);
        root.getChildren().add(yesNoBox);
    }
    public void initSceneStyling(){
        root.setPrefSize(500,500);
        root.setAlignment(Pos.CENTER);

        // All settings for buttons
        int ButtonWidth = 90;
        int ButtonHeight = 50;
        int ButtonHorizontalSpacing = 20;
        int ButtonVerticalSpacing = 20;

        yesNoBox.setAlignment(Pos.CENTER);
        yesNoBox.setSpacing(ButtonHorizontalSpacing);
        yesBtn.setPrefSize(ButtonWidth, ButtonHeight);
        yesBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        noBtn.setPrefSize(ButtonWidth, ButtonHeight);
        noBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        textToDisplay.setPrefSize(500, 250);
        textToDisplay.setAlignment(Pos.CENTER);
        textToDisplay.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        textToDisplay.setWrapText(true);

        clown.setFitWidth(200);
        clown.setPreserveRatio(true);
    }

    /**
     * Updates the content in the bottom left text box with the given input
     * @param content
     */
    public void updateTextArea(String content){
        textToDisplay.setText(content);
    }
}

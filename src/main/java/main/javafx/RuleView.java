package main.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RuleView {
    private VBox root;
    private Label Rules;
    private HBox bottomBox;
    private Button BackButton;



    public  RuleView(){
        initSceneGraph();
        initStyling();

    }

    public void initSceneGraph(){
        root = new VBox();

        Rules = new Label();

        bottomBox = new HBox();
        BackButton = new Button("Back");

        bottomBox.getChildren().add(BackButton);

        root.getChildren().add(Rules);
        root.getChildren().add(bottomBox);

    }

    public void initStyling(){
        // set window size
        root.setPrefSize(800, 700);
        // style rules text
        Insets padding = new Insets(30,50,0,50);

        String Pokerules = "Hello! Welcome to an interaction of the infamous pokemon battle factory, where trainers' knowledge is put to the test! \n\n" +
                "Rules proceed as follows.\n" +
                "\t1. Players will select 3 pokemon from a list of 6 randomly selected pokemon before battling. Whoever wins the battle will progress.\n" +
                "\t2. Unlike the original battle factory, teams are re-selected from another randomly generated pool of 6 pokemon after each match. There is also no team preview for the opposing team.\n" +
                "\t3. There are 64 pokemon from which the 6 pokemon are randomly selected for the players. In this iteration, there are no special items, sleep or freeze moves, and lastly no abilities. Additionally some pokemon have had their stats altered and some moves have had their effects changed or base powers altered. \n" +
                "\t4. Four of the 64 pokemon are themed around the show “Attack on Titan”, this application has no affiliation with the brand at all whatsoever. \n";

        Rules.setText(Pokerules);
        Rules.setWrapText(true);
        Rules.setAlignment(Pos.CENTER);
        Rules.setPadding(padding);
        Rules.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        // style the back btn
        // All settings for buttons
        int ButtonWidth = 90;
        int ButtonHeight = 50;
        BackButton.setPrefSize(ButtonWidth, ButtonHeight);
        BackButton.setAlignment(Pos.CENTER);
        BackButton.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        // style bottom box
        bottomBox.setPadding(padding);
        bottomBox.setAlignment(Pos.CENTER);

    }

    public Button getBackButton() {
        return BackButton;
    }

    public VBox getRoot() { return root;}
}
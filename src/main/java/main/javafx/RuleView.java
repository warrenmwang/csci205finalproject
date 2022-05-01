package main.javafx;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RuleView {
    private VBox root;
    private TextArea Rules;
    private HBox bottomBox;
    private Button BackButton;



    public  RuleView(){
        initSceneGraph();
        initStyling();

    }

    public void initSceneGraph(){
        root = new VBox();

        Rules = new TextArea();

        bottomBox = new HBox();
        BackButton = new Button("Back");

        bottomBox.getChildren().add(BackButton);

        root.getChildren().add(Rules);
        root.getChildren().add(bottomBox);

    }

    public void initStyling(){
        String Pokerules = "Hello! Welcome to an interaction of the infamous pokemon battle factory, where trainers' knowledge is put to the test! \n" +
                "Rules proceed as follows.\n" +
                "\t1. Players will select 3 pokemon from a list of 6 randomly selected pokemon before battling. Whoever wins the battle will progress.\n" +
                "\t2. Unlike the original battle factory, teams are re-selected from another randomly generated pool of 6 pokemon after each match. There is also no team preview for the opposing team.\n" +
                "\t3. There are 64 pokemon from which the 6 pokemon are randomly selected for the players. In this iteration, there are no special items, sleep or freeze moves, and lastly no abilities. Additionally some pokemon have had their stats altered and some moves have had their effects changed or base powers altered. \n" +
                "\t4. 4 of the 64 pokemon are themed around the show “Attack on Titan”, this application has no affiliation with the brand at all whatsoever. \n";
        Rules.setText(Pokerules);


    }

    public TextArea getRules() {
        return Rules;
    }

    public HBox getBottomBox() {
        return bottomBox;
    }

    public Button getBackButton() {
        return BackButton;
    }

    public VBox getRoot() { return root;}
}
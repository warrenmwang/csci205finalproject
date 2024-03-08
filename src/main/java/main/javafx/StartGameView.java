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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartGameView {
    private VBox root;
    private Button startGameNormal;
    private Button startGameHard;
    private Button Rule;
    private Button exit_btn;
    private HBox btnsBox;
    private ImageView logoView;



    public StartGameView(){
        initSceneGraph();
        initStyling();
    }

    public VBox getRoot() {
        return root;
    }

    public Button getExit_btn() {
        return exit_btn;
    }

    public Button getRule() {
        return Rule;
    }

    public Button getStartGameNormal() {
        return startGameNormal;
    }
    public Button getStartGameHard(){return startGameHard;}

    public void initSceneGraph(){
        root = new VBox();
        Image image1 = new Image(getClass().getResourceAsStream("/data/updatedbattlefactorymenubackground.jpg"));
        logoView = new ImageView(image1);

        startGameNormal = new Button("Normal");
        startGameHard = new Button("Hard");
        Rule = new Button("Rules");
        exit_btn = new Button("Exit");

        btnsBox = new HBox();
        btnsBox.getChildren().add(Rule);
        btnsBox.getChildren().add(startGameNormal);
        btnsBox.getChildren().add(startGameHard);
        btnsBox.getChildren().add(exit_btn);

        root.getChildren().addAll(logoView,btnsBox);
    }

    public void initStyling(){
        root.setPrefSize(700,550);
        root.setAlignment(Pos.TOP_CENTER);

        logoView.setFitWidth(700);
        logoView.setPreserveRatio(true);

        int ButtonWidth = 215;
        int ButtonHeight = 65;
        int spacing = 10;

        startGameNormal.setPrefSize(ButtonWidth,ButtonHeight);
        startGameHard.setPrefSize(ButtonWidth, ButtonHeight);
        Rule.setPrefSize(ButtonWidth,ButtonHeight);
        exit_btn.setPrefSize(ButtonWidth,ButtonHeight);

        startGameNormal.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        startGameHard.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Rule.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        exit_btn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        btnsBox.setSpacing(10);
        btnsBox.setAlignment(Pos.CENTER);
        btnsBox.setPadding(new Insets(0,0,5,0));

        root.setSpacing(spacing);

    }



}

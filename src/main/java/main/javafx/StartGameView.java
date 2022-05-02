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
    private Button start_game;
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

    public Button getStart_game() {
        return start_game;
    }

    public void initSceneGraph(){
        root = new VBox();
        String logo = "http://eg.bucknell.edu/~wmw015/code/csci205-final/updatedbattlefactorymenubackground.jpg";
        Image image1 = new Image(logo);
        logoView = new ImageView(image1);

        start_game = new Button("Start Game");

        Rule = new Button("Rules");

        exit_btn = new Button("Exit");

        btnsBox = new HBox();
        btnsBox.getChildren().add(Rule);
        btnsBox.getChildren().add(start_game);
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

        start_game.setPrefSize(ButtonWidth,ButtonHeight);
        Rule.setPrefSize(ButtonWidth,ButtonHeight);
        exit_btn.setPrefSize(ButtonWidth,ButtonHeight);
        start_game.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Rule.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        exit_btn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        btnsBox.setSpacing(10);
        btnsBox.setAlignment(Pos.CENTER);
        btnsBox.setPadding(new Insets(0,0,5,0));

        root.setSpacing(spacing);

    }



}

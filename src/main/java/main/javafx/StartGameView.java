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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class StartGameView {
    private VBox root;
    private Button start_game;
    private Button Rule;
    private Button exit_btn;

    public StartGameView(){
        initSceneGraph();
        initStyling();
        initEventHandler();
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
//        String logo  = "https://scontent.fagc2-1.fna.fbcdn.net/v/t1.18169-9/13221733_1245911422087669_2877110423342676825_n.png?_nc_cat=109&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=0uwTrWnJrGcAX_GSEvC&_nc_ht=scontent.fagc2-1.fna&oh=00_AT9_x3MoYsNw3MYoosqUnghJg_xIb0-No5ndrH_EVjB96g&oe=629138CD";
        String logo = "http://eg.bucknell.edu/~wmw015/code/csci205-final/updatedbattlefactorymenubackground.jpg";
        Image image1 = new Image(logo);
        ImageView logoView = new ImageView(image1);

        start_game = new Button("Start Game");

        Rule = new Button("Rules");

        exit_btn = new Button("Exit");

        root.getChildren().addAll(logoView,start_game,Rule,exit_btn);
    }

    public void initStyling(){

    }


    public void initEventHandler(){


    }
}

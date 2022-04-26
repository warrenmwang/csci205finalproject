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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//viewchoosePokemon

public class test {
    private VBox root;
    private BorderPane upside;
    private HBox downside;

//    public viewchoosePokemon(){
//        initSceneGraph();
//        initEventHandler();
//    }

    private void initSceneGraph(){
        root = new VBox();


        upside = new BorderPane();

        HBox toppane = new HBox();
        toppane.getChildren().add(new Button("Exit"));
        upside.setTop(toppane);

        VBox leftpane = new VBox();
        leftpane.getChildren().add(new Button("<"));
        upside.setLeft(leftpane);

        VBox rightpane = new VBox();
        rightpane.getChildren().add(new Button(">"));
        upside.setRight(rightpane);

        //test centerpane with test image
        VBox centerpane = new VBox();
        String test1 = "https://img.pokemondb.net/sprites/heartgold-soulsilver/normal/bulbasaur.png";
        Image image1 = new Image(test1);
        ImageView imageView1 = new ImageView(image1);
        centerpane.getChildren().add(imageView1);
        upside.setCenter(centerpane);

        HBox buttompane = new HBox();
        Label move1 = new Label("move1");
        Label move2 = new Label("move2");
        Label move3 = new Label("move3");
        Label move4 = new Label("move4");
        buttompane.getChildren().addAll(move1,move2,move3,move4);
        upside.setBottom(buttompane);


        downside = new HBox();
        VBox confirm = new VBox();
        confirm.getChildren().add(new Button("√"));
        VBox cancel = new VBox();
        cancel.getChildren().add(new Button("×"));
        downside.getChildren().addAll(confirm,cancel);




        root.getChildren().add(upside);
        root.getChildren().add(downside);
    }

    private void initEventHandler(){

    }

}

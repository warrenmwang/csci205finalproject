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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class LoadingScreenView {
    private VBox root;
    private ImageView loadingImage;

    public VBox getRoot(){return root;}

    // https://i.gifer.com/YCZH.gif

    public LoadingScreenView(){
        initSceneGraph();
        initStyling();


    }
//https://i.gifer.com/YCZH.gif
    public void initSceneGraph(){
        root = new VBox();
        loadingImage = new ImageView(new Image("https://img.pokemondb.net/sprites/black-white/anim/shiny/blaziken.gif"));
        root.getChildren().add(loadingImage);
    }


    public void initStyling(){
        root.setPrefSize(460,500);
        root.setAlignment(Pos.CENTER);
        loadingImage.setFitWidth(460);
        loadingImage.setFitHeight(500);


    }
}

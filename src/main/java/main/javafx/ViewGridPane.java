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
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.net.URL;

public class ViewGridPane {

    private VBox root;
    private GridPane gridPane;
    private HBox bottomHBox;
    private StackPane stackPane;

    private VBox root1;
    private VBox root2;

    private Button btn1;
    private Button btn2;

    public VBox getRoot() {
        return root;
    }
    public VBox getRoot1(){return root1;}
    public VBox getRoot2(){return root2;}
    public Button getBtn1(){ return btn1; }
    public Button getBtn2(){ return btn2; }

    public void setRoot(VBox newRoot){this.root = newRoot; }

    public ViewGridPane() {
        initSceneGraph();
        initSceneStyling();
    }

    public void initSceneGraph() {
        // --------- graphical root ----------
        root1 = new VBox();


        gridPane = new GridPane();
        bottomHBox = new HBox();
        stackPane = new StackPane();


//        URL url1 = new URL("http://eg.bucknell.edu/~wmw015/code/test1.png");
        String backgroundImage = "http://eg.bucknell.edu/~wmw015/code/backgroundforpokemon.jfif";
        String test1 = "https://img.pokemondb.net/sprites/heartgold-soulsilver/normal/bulbasaur.png";
        String test2 = "https://img.pokemondb.net/sprites/heartgold-soulsilver/back-normal/bulbasaur.png";


        try {
            // add pictures to gridpane
//            FileInputStream input = new FileInputStream(url1);
            Image image1 = new Image(test1);
            ImageView imageView1 = new ImageView(image1);
            Image image2 = new Image(test2);
            ImageView imageView2 = new ImageView(image2);
            gridPane.add(new Text("123/456"), 1, 0);
            gridPane.add(new Text("456/789"), 1, 3);
            gridPane.add(imageView1, 0, 0, 1, 1);
            gridPane.add(imageView2, 2, 0, 1, 2);
//            root.getChildren().add(imageView);
        } catch (Exception e) {

        }

        stackPane.getChildren().add(gridPane);

        root1.getChildren().add(stackPane);
        root1.getChildren().add(bottomHBox);
        btn1 = new Button("switch root");
        root1.getChildren().add(btn1);

        // -------- root 2 text -------
        root2 = new VBox();

        root2.getChildren().add(new Text("haha"));
        btn2 = new Button("switch root");
        root2.getChildren().add(btn2);

        // ------- initialize main root to root 1 graphical ------
        root = new VBox();
        root.getChildren().add(root1);
    }

    public void initSceneStyling() {
        Image img = new Image("http://eg.bucknell.edu/~wmw015/code/backgroundforpokemon.jfif");

        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Background bGround = new Background(bImg);

        stackPane.setBackground(bGround);

        // auto resize stackpane
        stackPane.setPrefSize(1050, 540);

        // set vbox size
        root.setPrefSize(1050, 700);


    }

}
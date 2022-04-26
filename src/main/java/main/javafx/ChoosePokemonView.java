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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Pokemon;


import java.util.ArrayList;

public class ChoosePokemonView {

    private ArrayList<Pokemon> chooseFromPoke;
    private int currPokeInd;

    // layers are from top to bottom
    private VBox root;
    private HBox layer1;
    private HBox layer2;
    private HBox layer3;
    private HBox layer4;
    private Button exitBtn;
    private Button leftArrow;
    private Button rightArrow;
    private ArrayList<ImageView> allPokeImgs;
    private ImageView currViewPokemon;
    private Pokemon currPokemon;
    private Button Move1;
    private Button Move2;
    private Button Move3;
    private Button Move4;
    private Button checkMark;
    private Label moveDesc;
    private int pokemonChosenCounter;

    /**
     * Constructor
     */
    public ChoosePokemonView(ArrayList<Pokemon> team){
        this.chooseFromPoke = team;
        currPokeInd = 0;
        pokemonChosenCounter = 0;
        initSceneGraph();
        initSceneStyling();
    }

    /**
     * Create scene contents
     */
    public void initSceneGraph(){
        root = new VBox();
        layer1 = new HBox();
        layer2 = new HBox();
        layer3 = new HBox();
        layer4 = new HBox();


        // layer 1 contents
        exitBtn = new Button("Exit");
        layer1.getChildren().add(exitBtn);

        // layer 2 contents
        leftArrow = new Button("<--");
        rightArrow = new Button("-->");
        allPokeImgs = new ArrayList<>();
        for(Pokemon p : chooseFromPoke){
            String url = p.getPlayerImage();
            allPokeImgs.add(new ImageView(new Image(url)));
        }
        currViewPokemon = allPokeImgs.get(0);
        layer2.getChildren().add(leftArrow);
        layer2.getChildren().add(currViewPokemon);
        layer2.getChildren().add(rightArrow);

        // layer 3 contents
        Move1 = new Button("");
        setMove1();

        Move2 = new Button("");
        setMove2();

        Move3 = new Button("");
        setMove3();

        Move4 = new Button("");
        setMove4();

        layer3.getChildren().add(Move1);
        layer3.getChildren().add(Move2);
        layer3.getChildren().add(Move3);
        layer3.getChildren().add(Move4);

        // layer 4 contents
        checkMark = new Button("✅");
        moveDesc = new Label("");
        layer4.getChildren().add(moveDesc);
        layer4.getChildren().add(checkMark);


        // add all layers to root
        root.getChildren().add(layer1);
        root.getChildren().add(layer2);
        root.getChildren().add(layer3);
        root.getChildren().add(layer4);

    }

    /**
     * Style scene contents
     */
    public void initSceneStyling(){

    }
    public Button getCheckMark(){return this.checkMark;}
    public int getPointer() {return this.currPokeInd % 6;}
    public void incPointer() {this.currPokeInd += 1;}
    public void decPointer() {this.currPokeInd -= 1;}
    public int getPokemonChosenCounter() {return this.pokemonChosenCounter;}

    public String getCurrPokemonID() {
        return this.chooseFromPoke.get(currPokeInd).getID();
    }

    public void incrementChosenPokemonCounter(){ this.pokemonChosenCounter += 1;}

    public void setCurrPokeInd(int ind){this.currPokeInd = ind;}

    public void setMove1(){
        Move1.setText(this.chooseFromPoke.get(currPokeInd).getMoves().get(0));
    }

    public void setMove2(){
        Move2.setText(this.chooseFromPoke.get(currPokeInd).getMoves().get(1));
    }

    public void setMove3(){
        Move3.setText(this.chooseFromPoke.get(currPokeInd).getMoves().get(2));
    }

    public void setMove4(){
        Move4.setText(this.chooseFromPoke.get(currPokeInd).getMoves().get(3));
    }

    public VBox getRoot() {
        return this.root;
    }
}

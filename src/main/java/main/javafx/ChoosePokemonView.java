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
    private ArrayList<Image> allPokeImgs;
    private ImageView currViewPokemon;

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
        this.chooseFromPoke = new ArrayList<>(team);
        System.out.println(team);
        System.out.println("start of view");
        for(Pokemon p:team){
            System.out.println(p.getID());
        }
        System.out.println("end of view");
        currPokeInd = 0;
        pokemonChosenCounter = 0;
        System.out.println("step3");
        initSceneGraph();
        System.out.println("step4");
        initSceneStyling();
        System.out.println("step5");
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
            String url = p.getBotImage(); // get bot image bc that faces forward
            allPokeImgs.add(new Image(url));
        }

        // set currViewPokemon based on currPokeInd
        currViewPokemon = new ImageView(allPokeImgs.get(currPokeInd));
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
        checkMark = new Button("yes");
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
        // set a pref size for the whole thing
        root.setPrefSize(450, 450);

        // set a max size for pokemon being viewed
        currViewPokemon.setFitHeight(250);
        currViewPokemon.setFitWidth(250);

        //left and right switches
        layer2.setAlignment(Pos.CENTER_LEFT);
        layer2.setPadding(new Insets(10,50,10,50));
        layer2.setSpacing(10);
        leftArrow.setPrefSize(60,10);
        rightArrow.setPrefSize(60,10);

        //buttons
        layer3.setPadding(new Insets(10,10,10,10));
        layer3.setSpacing(10);
        Move1.setPrefSize(100,40);
        Move2.setPrefSize(100,40);
        Move3.setPrefSize(100,40);
        Move4.setPrefSize(100,40);


    }


    public Button getCheckMark(){return this.checkMark;}
    public void incCurrPokeInd() {
        this.currPokeInd += 1;
        if(this.currPokeInd >= allPokeImgs.size()) this.currPokeInd = 0;
    }
    public void decCurrPokeInd() {
        this.currPokeInd -= 1;
        if(this.currPokeInd <= -1) this.currPokeInd = (allPokeImgs.size()-1);
    }
    public int getPokemonChosenCounter() {return this.pokemonChosenCounter;}

    public String getCurrPokemonID() {
        return this.chooseFromPoke.get(currPokeInd).getID();
    }
    public int getCurrPokeInd(){return currPokeInd;}
    public ArrayList<Image> getAllPokeImgs(){return allPokeImgs;}

    public Button getExitBtn(){return this.exitBtn;}
    public Button getLeftArrow(){return this.leftArrow;}
    public Button getRightArrow(){return this.rightArrow;}

    public void incrementChosenPokemonCounter(){ this.pokemonChosenCounter += 1;}

    // assumes currpokeind is updated before this function is called
    public void updateCurrViewPokemon(){
        System.out.println("updatecurrviewpoke " + currPokeInd);
        this.currViewPokemon.setImage(this.allPokeImgs.get(currPokeInd));
    }

    // just calls the 4 set move functions to be displayed
    public void setAllMoves(){
        setMove1(); setMove2(); setMove3(); setMove4();
    }

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

    public ArrayList<Pokemon> getChooseFromPoke(){return chooseFromPoke;}

}

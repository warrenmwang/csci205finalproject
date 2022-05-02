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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Pokemon;


import java.util.ArrayList;

public class ChoosePokemonView {

    private ArrayList<Pokemon> chooseFromPoke;
    private ArrayList<Pokemon> originalPoketeam;
    private ArrayList<Image> originalPokeImg;
    private int currPokeInd;

    // layers are from top to bottom
    private VBox root;
    private HBox layer1;
    private HBox layer2;
    private HBox layer3;
    private HBox layer4;
    private HBox layer5;
    private Label bottomTip;
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
    private TextArea moveDesc;
    private int pokemonChosenCounter;

    /**
     * Constructor
     */
    public ChoosePokemonView(ArrayList<Pokemon> team){
        this.originalPoketeam = new ArrayList<>(team);
        this.chooseFromPoke = new ArrayList<>(team);

        for(Pokemon p:team){
            System.out.println(p.getID());
        }
        currPokeInd = 0;
        pokemonChosenCounter = 0;

        initSceneGraph();
        initSceneStyling();
    }

    public void setChooseFromPoke(ArrayList<Pokemon> chooseFromPoke) {
        this.chooseFromPoke = chooseFromPoke;
    }

    public void setCurrPokeInd(int currPokeInd) {
        this.currPokeInd = currPokeInd;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }

    public HBox getLayer1() {
        return layer1;
    }

    public void setLayer1(HBox layer1) {
        this.layer1 = layer1;
    }

    public HBox getLayer2() {
        return layer2;
    }

    public void setLayer2(HBox layer2) {
        this.layer2 = layer2;
    }

    public HBox getLayer3() {
        return layer3;
    }

    public void setLayer3(HBox layer3) {
        this.layer3 = layer3;
    }

    public HBox getLayer4() {
        return layer4;
    }

    public void setLayer4(HBox layer4) {
        this.layer4 = layer4;
    }

    public void setExitBtn(Button exitBtn) {
        this.exitBtn = exitBtn;
    }

    public void setLeftArrow(Button leftArrow) {
        this.leftArrow = leftArrow;
    }

    public void setRightArrow(Button rightArrow) {
        this.rightArrow = rightArrow;
    }

    public void setAllPokeImgs(ArrayList<Image> allPokeImgs) {
        this.allPokeImgs = allPokeImgs;
    }

    public ImageView getCurrViewPokemon() {
        return currViewPokemon;
    }

    public void setCurrViewPokemon(ImageView currViewPokemon) {
        this.currViewPokemon = currViewPokemon;
    }

    public Button getMove1() {
        return Move1;
    }

    public void setMove1(Button move1) {
        Move1 = move1;
    }

    public Button getMove2() {
        return Move2;
    }

    public void setMove2(Button move2) {
        Move2 = move2;
    }

    public Button getMove3() {
        return Move3;
    }

    public void setMove3(Button move3) {
        Move3 = move3;
    }

    public Button getMove4() {
        return Move4;
    }

    public void setMove4(Button move4) {
        Move4 = move4;
    }

    public void setCheckMark(Button checkMark) {
        this.checkMark = checkMark;
    }

    public TextArea getMoveDesc() {
        return moveDesc;
    }

    public void setMoveDesc(TextArea moveDesc) {
        this.moveDesc = moveDesc;
    }

    public void setPokemonChosenCounter(int pokemonChosenCounter) {
        this.pokemonChosenCounter = pokemonChosenCounter;
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
        layer5 = new HBox();

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

        originalPokeImg = new ArrayList<>(allPokeImgs);

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
        checkMark = new Button("Yes");
        moveDesc = new TextArea();
        layer4.getChildren().add(moveDesc);
        layer4.getChildren().add(checkMark);

        // layer 5 contents
        bottomTip = new Label("Click on the Pokemon Image or the Move Name for more information.");
        layer5.getChildren().add(bottomTip);

        // add all layers to root
        root.getChildren().add(layer1);
        root.getChildren().add(layer2);
        root.getChildren().add(layer3);
        root.getChildren().add(layer4);
        root.getChildren().add(layer5);
    }

    /**
     * Style scene contents
     */
    public void initSceneStyling(){
        // set a pref size for the whole thing
        root.setPrefSize(460, 500);

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
        int ButtonWidth = 110;
        int ButtonHeight = 40;

        layer3.setPadding(new Insets(10,10,10,10));
        layer3.setSpacing(10);
        Move1.setPrefSize(ButtonWidth,ButtonHeight);
        Move1.setAlignment(Pos.CENTER);

        Move2.setPrefSize(ButtonWidth,ButtonHeight);
        Move2.setAlignment(Pos.CENTER);

        Move3.setPrefSize(ButtonWidth,ButtonHeight);
        Move3.setAlignment(Pos.CENTER);

        Move4.setPrefSize(ButtonWidth,ButtonHeight);
        Move4.setAlignment(Pos.CENTER);


        //TextArea
        layer4.setPadding(new Insets(10,0,10,20));
        layer4.setSpacing(30);

        layer4.setAlignment(Pos.CENTER);
        moveDesc.setPrefSize(300,100);
        moveDesc.setWrapText(true);
        checkMark.setPrefSize(70,70);

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

    public void resetPointers(){
        pokemonChosenCounter =0;
        currPokeInd = 0;
        chooseFromPoke = new ArrayList<>(originalPoketeam);
        allPokeImgs = new ArrayList<>(originalPokeImg);
    }


    public void incrementChosenPokemonCounter(){ this.pokemonChosenCounter += 1;}

    // assumes currpokeind is updated before this function is called
    public void updateCurrViewPokemon(){
//        System.out.println("updatecurrviewpoke " + currPokeInd);
        this.currViewPokemon.setImage(this.allPokeImgs.get(currPokeInd));
    }

    // just calls the 4 set move functions to be displayed
    public void setAllMoves(){
        setMove1(); setMove2(); setMove3(); setMove4();
    }

    public void setMove1(){
        String moveName = this.chooseFromPoke.get(currPokeInd).getMoves().get(0);
        if(moveName.length()>12){
        moveName = moveName.replace("_","\n");}
        else {
            moveName = moveName.replace("_"," ");
        }
        moveName = moveName.replace("$"," ");

        Move1.setText(moveName);
    }

    public void setMove2(){
        String moveName = this.chooseFromPoke.get(currPokeInd).getMoves().get(1);
        //moveName = moveName.replace("_","\n ");

        if(moveName.length()>12){
            moveName = moveName.replace("_","\n");}
        else {
            moveName = moveName.replace("_"," ");
        }
        moveName = moveName.replace("$"," ");


        Move2.setText(moveName);
    }

    public void setMove3(){
        String moveName = this.chooseFromPoke.get(currPokeInd).getMoves().get(2);
        //moveName = moveName.replace("_","\n ");

        if(moveName.length()>12){
            moveName = moveName.replace("_","\n");}
        else {
            moveName = moveName.replace("_"," ");
        }
        moveName = moveName.replace("$"," ");

        Move3.setText(moveName);
    }

    public void setMove4(){
        String moveName = this.chooseFromPoke.get(currPokeInd).getMoves().get(3);
        //moveName = moveName.replace("_","\n ");

        if(moveName.length()>12){
            moveName = moveName.replace("_","\n");}
        else {
            moveName = moveName.replace("_"," ");
        }
        moveName = moveName.replace("$"," ");

        Move4.setText(moveName);
    }

    public VBox getRoot() {
        return this.root;
    }

    public ArrayList<Pokemon> getChooseFromPoke(){return chooseFromPoke;}

    // set new team and erase choosefrompoke list
    public void setOriginalPoketeamAndCleanChooseFromPoke(ArrayList<Pokemon> newTeam){
        originalPoketeam = new ArrayList<>(newTeam);
        chooseFromPoke = new ArrayList<>(newTeam);

        currPokeInd = 0;
        pokemonChosenCounter = 0;

        // update poke images
        allPokeImgs = new ArrayList<>();

        for(Pokemon p : chooseFromPoke){
            p.resetAllstats(); // reset poke's stats

            String url = p.getBotImage(); // get bot image bc that faces forward
            allPokeImgs.add(new Image(url));
        }

    }

}

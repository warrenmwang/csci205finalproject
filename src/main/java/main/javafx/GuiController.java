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

import javafx.scene.Scene;
import javafx.stage.Stage;
import main.BattleMacro;
import main.MovesInventory;
import main.UserInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class GuiController {
    private TextView textView;
    private BattleView battleView;
    private ChoosePokemonView choosePokemonView;
    private StartGameView startGameView;

    private Stage primaryStage;
    private Scene guiScene;
    private Scene textScene;
    private Scene battleScene;
    private Scene choosePokemonScene;
    private Scene startScene;
    private MovesInventory movesInventory;

    private Model model;
    private ByteArrayOutputStream newSysOut;
    private BattleMacro battleMacro;


    public GuiController(TextView textView, BattleView battleView, Stage primaryStage) throws Exception{
        // before creating out main game, change sys.out to something we capture
        PrintStream sysOutOrig = System.out;
        newSysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newSysOut));

        movesInventory = new MovesInventory();


        // create model
        battleMacro = new BattleMacro();
        this.model = new Model(battleMacro);

        // create all the views
        this.startGameView = new StartGameView();
        this.battleView = battleView;
        this.textView = textView;
        this.choosePokemonView = new ChoosePokemonView(battleMacro.getBattleMicro().getUserTeam());

        // create scenes from the views
        this.startScene = new Scene(this.startGameView.getRoot());
        this.textScene = new Scene(this.textView.getRoot());
        this.battleScene = new Scene(this.battleView.getRoot());
        this.choosePokemonScene = new Scene(this.choosePokemonView.getRoot());

        // start with the gui scene
        // TODO start the game with the StartGameScene
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(startScene);
        //this.primaryStage.setScene(choosePokemonScene);
        this.primaryStage.sizeToScene();
        this.primaryStage.show();

        this.primaryStage.setTitle("Pokemon BattleFactory Simulator");


        initEventHandler();
    }

    public void initEventHandler() {
        // ------- event handler to switch scenes on the main stage -------
        startGameView.getStart_game().setOnMouseClicked(event -> {
            model.getNewThread();
            model.run();
            primaryStage.setScene(choosePokemonScene);
        });

        startGameView.getExit_btn().setOnMouseClicked(event -> {
            //TODO terminate the program
            exit(0);

        });

        startGameView.getRule().setOnMouseClicked(event -> {

        });

        // switch from gui to text
        battleView.getSwitchSceneBtn().setOnMouseClicked(event -> {
              primaryStage.setScene(textScene);
        });

        // switch from text to gui
        textView.getSwitchSceneBtn().setOnMouseClicked(event -> {
            primaryStage.setScene(battleScene);
        });

        // TODO switch from BattleView to DoYouWantToPlayAgainAndEndScene



        // ------- stuff to handle events in the text view --------

        // refresh the screen when we press the refresh button
        textView.getBtn1().setOnMouseClicked(Event -> {
            refreshTextAreaWithSysOut();
        });

        // get user input from the textField box
        textView.getInputTextField().setOnAction(event -> {
            // read in user input from System.in
            String stringToPassIntoNewSysIn = textView.getInputTextField().getText();

            textView.getTestBottomLabel().setText(stringToPassIntoNewSysIn);

            UserInput.setUSERINPUT(stringToPassIntoNewSysIn);
            UserInput.setCanGetUSERINPUT(true);
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){

            }
            refreshTextAreaWithSysOut();
        });

        // ----------- CHOOSE POKEMON VIEW Controls -------------
        // choose pokemon
        choosePokemonView.getCheckMark().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT(choosePokemonView.getCurrPokemonID());
            UserInput.setCanGetUSERINPUT(true);
            choosePokemonView.incrementChosenPokemonCounter();

            // remove chosen pokemon from being able to be chosen again
            choosePokemonView.getChooseFromPoke().remove(choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()));
            choosePokemonView.getAllPokeImgs().remove(choosePokemonView.getCurrPokeInd());

            // increment counter, then set curr pokemon to be where the new index is at
            choosePokemonView.incCurrPokeInd();
            choosePokemonView.updateCurrViewPokemon();
            // then update the moves on the screen
            choosePokemonView.setAllMoves();


            // check if need to switch scene when 3 pokemon are chosen
            if(choosePokemonView.getPokemonChosenCounter() == 3){
                primaryStage.setScene(battleScene);
            }
        });

        // left arrow cycle pokemon, subtract index, wrap around if needed
        choosePokemonView.getLeftArrow().setOnMouseClicked(event -> {
            // decrement counter, then set curr pokemon to be where the new index is at
            choosePokemonView.decCurrPokeInd();
            choosePokemonView.updateCurrViewPokemon();
            // then update the moves on the screen
            choosePokemonView.setAllMoves();
        });

        // right arrow cycle pokemon, add index, wrap around if neeeded
        choosePokemonView.getRightArrow().setOnMouseClicked(event -> {
            // increment counter, then set curr pokemon to be where the new index is at
            choosePokemonView.incCurrPokeInd();
            choosePokemonView.updateCurrViewPokemon();
            // then update the moves on the screen
            choosePokemonView.setAllMoves();
        });


        //In choosePokemonscene, display move description when move button is clicked

        choosePokemonView.getCurrViewPokemon().setOnMouseClicked(event -> {
            String PokeDes = choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()).toSmallString();
            try {
                newSysOut.reset();
            }catch(Exception e){
                System.out.println("???");
            }
            System.out.println(PokeDes);
            choosePokemonView.getMoveDesc().clear();
            choosePokemonView.getMoveDesc().appendText(newSysOut.toString());
            choosePokemonView.getMoveDesc().setScrollTop(Double.MAX_VALUE);
        });


        choosePokemonView.getMove1().setOnMouseClicked(event -> {
            String moveName = choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()).getMove(0);
            try {
                newSysOut.reset();
            }catch(Exception e){
                System.out.println("???");
            }
            System.out.println(movesInventory.getMove(moveName));
            choosePokemonView.getMoveDesc().clear();
            choosePokemonView.getMoveDesc().appendText(newSysOut.toString());
            choosePokemonView.getMoveDesc().setScrollTop(Double.MAX_VALUE);
        });

        choosePokemonView.getMove2().setOnMouseClicked(event -> {
            String moveName = choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()).getMove(1);
            try {
                newSysOut.reset();
            }catch(Exception e){
                System.out.println("???");
            }
            System.out.println(movesInventory.getMove(moveName));
            choosePokemonView.getMoveDesc().clear();
            choosePokemonView.getMoveDesc().appendText(newSysOut.toString());
            choosePokemonView.getMoveDesc().setScrollTop(Double.MAX_VALUE);
        });

        choosePokemonView.getMove3().setOnMouseClicked(event -> {
            String moveName = choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()).getMove(2);
            try {
                newSysOut.reset();
            }catch(Exception e){
                System.out.println("???");
            }
            System.out.println(movesInventory.getMove(moveName));
            choosePokemonView.getMoveDesc().clear();
            choosePokemonView.getMoveDesc().appendText(newSysOut.toString());
            choosePokemonView.getMoveDesc().setScrollTop(Double.MAX_VALUE);
        });

        choosePokemonView.getMove4().setOnMouseClicked(event -> {
            String moveName = choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()).getMove(3);
            try {
                newSysOut.reset();
            }catch(Exception e){
                System.out.println("???");
            }
            System.out.println(movesInventory.getMove(moveName));
            choosePokemonView.getMoveDesc().clear();
            choosePokemonView.getMoveDesc().appendText(newSysOut.toString());
            choosePokemonView.getMoveDesc().setScrollTop(Double.MAX_VALUE);
        });





        // exit button returns player to the start game scene
        choosePokemonView.getExitBtn().setOnMouseClicked(event -> {
            // switch to the starting scene
            model.stop();
            choosePokemonView.setPokemonChosenCounter(0);
            primaryStage.setScene(startScene);
        });



    }


    // ------------------- for the Text scene, which won't be used in final product -----
    /**
     * Refresh text area of the TextView
     */
    public void refreshTextAreaWithSysOut(){
        textView.getOutputText().setText("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        textView.getOutputText().appendText(newSysOut.toString());
        textView.getOutputText().setScrollTop(Double.MAX_VALUE); // set scrolled to bottom
    }
}

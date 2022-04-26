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
import main.UserInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class GuiController {
    private TextView textView;
    private BattleView battleView;
    private ChoosePokemonView choosePokemonView;
    private Stage primaryStage;
    private Scene guiScene;
    private Scene textScene;
    private Scene battleScene;
    private Scene choosePokemonScene;
    private Model model;
    private ByteArrayOutputStream newSysOut;
    private BattleMacro battleMacro;

    public GuiController(TextView textView, BattleView battleView, Stage primaryStage) throws Exception{
        // before creating out main game, change sys.out to something we capture
        PrintStream sysOutOrig = System.out;
        newSysOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newSysOut));

        // create model
        battleMacro = new BattleMacro();
        this.model = new Model(battleMacro);


        // create all the views
        this.battleView = battleView;
        this.textView = textView;
        this.choosePokemonView = new ChoosePokemonView(battleMacro.getBattleMicro().getUserTeam());

        // create scenes from the views
        this.textScene = new Scene(this.textView.getRoot());
        this.battleScene = new Scene(this.battleView.getRoot());
        this.choosePokemonScene = new Scene(this.choosePokemonView.getRoot());

        // start with the gui scene
        // TODO choose the first scene to start with
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(choosePokemonScene);
        this.primaryStage.sizeToScene();
        this.primaryStage.show();

        this.primaryStage.setTitle("Pokemon BattleFactory Simulator");
        initEventHandler();
    }

    public void initEventHandler() {
        // ------- event handler to switch scenes on the main stage -------

        // switch from gui to text
        battleView.getSwitchSceneBtn().setOnMouseClicked(event -> {
//            view.setRoot(view.getRoot2());
              primaryStage.setScene(textScene);
        });

        // switch from text to gui
        textView.getSwitchSceneBtn().setOnMouseClicked(event -> {
            primaryStage.setScene(battleScene);
        });

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

            // check if need to switch scene when 3 pokemon are chosen
            if(choosePokemonView.getPokemonChosenCounter() == 3){
                primaryStage.setScene(battleScene);
            }
        });

    }

    /**
     * Refresh text area of the TextView
     */
    public void refreshTextAreaWithSysOut(){
        textView.getOutputText().setText("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
        textView.getOutputText().appendText(newSysOut.toString());
        textView.getOutputText().setScrollTop(Double.MAX_VALUE); // set scrolled to bottom
    }
}

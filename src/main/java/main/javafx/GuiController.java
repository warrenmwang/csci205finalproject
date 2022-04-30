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
import main.Pokemon;
import main.UserInput;

import javax.print.event.PrintJobAttributeListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class GuiController {
    private TextView textView;
    private BattleView battleView;
    private ChoosePokemonView choosePokemonView;
    private StartGameView startGameView;
    private TransitionAndEndGameView transitionAndEndGameView;

    private Stage primaryStage;
    private Scene guiScene;
    private Scene textScene;
    private Scene battleScene;
    private Scene choosePokemonScene;
    private Scene startScene;
    private Scene transitionAndEndGameScene;
    private MovesInventory movesInventory;

    private Model model;
    private ByteArrayOutputStream newSysOut;
    private BattleMacro battleMacro;

    public BattleMacro getBattleMacro(){return battleMacro;}
    public BattleView getBattleView(){return battleView;}

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
//        this.battleView = new BattleView(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage(), battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
//        this.textView = new TextView();
        this.battleView = battleView;
        this.textView = textView;

        this.choosePokemonView = new ChoosePokemonView(battleMacro.getBattleMicro().getUserTeam());
        transitionAndEndGameView = new TransitionAndEndGameView();

        // create scenes from the views
        this.startScene = new Scene(this.startGameView.getRoot());
        this.textScene = new Scene(this.textView.getRoot());
        this.battleScene = new Scene(this.battleView.getRoot());
        this.choosePokemonScene = new Scene(this.choosePokemonView.getRoot());
        transitionAndEndGameScene = new Scene(transitionAndEndGameView.getRoot());

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

        // ---------- START GAME ------------------

        startGameView.getStart_game().setOnMouseClicked(event -> {

            // restart main game loop
            model.getNewThread();
            model.run();
            // update choosepokemon scene
            choosePokemonView.updateCurrViewPokemon();
            choosePokemonView.setAllMoves();

            // switch to choosepokemonscene
            primaryStage.setScene(choosePokemonScene);
        });

        startGameView.getExit_btn().setOnMouseClicked(event -> {
            //TODO terminate the program
            exit(0);

        });

        startGameView.getRule().setOnMouseClicked(event -> {
            // let's reuse the transition and end game view for this
            primaryStage.setScene(transitionAndEndGameScene);
            transitionAndEndGameView.updateTextArea("RULES To be written...");

        });

        // TODO switch from BattleView to DoYouWantToPlayAgainAndEndScene


        // -------------------- BATTLE VIEW -------------------

        battleView.getRefreshTextAreaBtn().setOnMouseClicked(event -> {
            battleView.updateBottomLeftTextBox(newSysOut.toString());
        });


        // switch from gui to text
        battleView.getSwitchSceneBtn().setOnMouseClicked(event -> {
              primaryStage.setScene(textScene);
        });

        // attack switch forfeit buttons
        battleView.getAttack().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT("attack");
            UserInput.setCanGetUSERINPUT(true);

            try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){};


            // show moves box
            battleView.bottomRightBoxToggleChoices(0);
        });

        battleView.getSwitch().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT("Switch");
            UserInput.setCanGetUSERINPUT(true);

            // show what pokemon user can choose from
            try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){};
            battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

            // TODO switch to the moves buttons and rename them with the names of the remaining
            //   pokemon that user can choose from
            battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
            battleView.bottomRightBoxToggleChoices(2);
        });

        battleView.getForfeit().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT("forfeit");
            UserInput.setCanGetUSERINPUT(true);

            // tell user if they really want to forfeit
            battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();


            // transition to end scene
            try{TimeUnit.MILLISECONDS.sleep(50);}catch(Exception e){};
            primaryStage.setScene(transitionAndEndGameScene);
            transitionAndEndGameView.updateTextArea(newSysOut.toString());
        });


        // MOVES
        battleView.getMove1().setOnMouseClicked(event -> {
//            newSysOut.reset();
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(0));
            UserInput.setCanGetUSERINPUT(true);

            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (Exception e) {}
            if(UserInput.getNeedToSwitch()){
                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
                System.out.println("not approved to switch");
                UserInput.setNeedToSwitch(false);


                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {}

                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update view with new sprites and moves
                battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
                battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

                battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
                battleView.updatePokemonSprites(); // update sprites shown
                battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
            }
        });

        battleView.getMove2().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(1));
            UserInput.setCanGetUSERINPUT(true);

            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (Exception e) {}
            if(UserInput.getNeedToSwitch()){
                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }
                ;
                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update view with new sprites and moves
                battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
                battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

                battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
                battleView.updatePokemonSprites(); // update sprites shown
                battleView.updateMovesBox();
            }// update 4moves box with curr pokemon move names
        });

        battleView.getMove3().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(2));
            UserInput.setCanGetUSERINPUT(true);
            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (Exception e) {}
            if(UserInput.getNeedToSwitch()){
                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }
                ;
                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update view with new sprites and moves
                battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
                battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

                battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
                battleView.updatePokemonSprites(); // update sprites shown
                battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
            }
        });

        battleView.getMove4().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(3));
            UserInput.setCanGetUSERINPUT(true);
            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (Exception e) {}
            if(UserInput.getNeedToSwitch()){
                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }
                ;
                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update view with new sprites and moves
                battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
                battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

                battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
                battleView.updatePokemonSprites(); // update sprites shown
                battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
            }
        });


        battleView.getPoke0Btn().setOnMouseClicked(event -> {
            if(battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive()) {
                // if alive, switch to that pokemon
                UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUserTeam().get(1).getID());
                UserInput.setCanGetUSERINPUT(true);

                // update text area
                try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){};
                battleView.updateBottomLeftTextBox(newSysOut.toString());
                newSysOut.reset();

                // switch box back to 3 choices
                battleView.bottomRightBoxToggleChoices(1);

                // update view with new sprites and moves
                battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
                battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

                battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
                battleView.updatePokemonSprites(); // update sprites shown
                battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
            }
            // if dead, do nothing
        });

        battleView.getPoke1Btn().setOnMouseClicked(event -> {
            if(battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive()) {
                // if alive, switch to that pokemon
                UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUserTeam().get(2).getID());
                UserInput.setCanGetUSERINPUT(true);


                // update text area
                try {TimeUnit.SECONDS.sleep(1);} catch (Exception e) {}

                battleView.updateBottomLeftTextBox(newSysOut.toString());
                newSysOut.reset();

                // switch box back to 3 choices
                battleView.bottomRightBoxToggleChoices(1);

                // update view with new sprites and moves
                battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
                battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

                battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
                battleView.updatePokemonSprites(); // update sprites shown
                battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
            }
            // if dead, do nothing
        });




        // ----------- CHOOSE POKEMON VIEW -------------

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
                // update the battlescene with player's curr pokemon
                // sleep to allow botteam to update
                try{TimeUnit.MILLISECONDS.sleep(5);}catch(Exception e){}
                // update view with new sprites and moves
                battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
                battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

                battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
                battleView.updatePokemonSprites(); // update sprites shown
                battleView.updateMovesBox(); // update 4moves box with curr pokemon move names

                // change to battlescene
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


        // show move 1 description
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

        // show move 2 description
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

        // show move 3 description
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

        // show move 4 description
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

            // stop the model and reset pokemon view pointers (if go back to choosepokescene, pointer will be 0)
            model.stop();
            choosePokemonView.resetPointers();

            // reset teams in battlemacro, and recreate everything
//            try{battleMacro.reset();}catch(Exception e){}
//            battleMacro.getBattleMicro().generateInitialPlayerRandomTeam();
//            battleMacro.getBattleMicro().generateInitialBotRandomTeam();
            try{battleMacro = new BattleMacro();}catch(Exception e){}
            battleMacro.getBattleMicro().generateInitialBotRandomTeam();

            try{TimeUnit.MILLISECONDS.sleep(500);}catch(Exception e){}

            model.setBattleMacro(battleMacro);
            choosePokemonView = new ChoosePokemonView(battleMacro.getBattleMicro().getUserTeam());
            choosePokemonScene = new Scene(choosePokemonView.getRoot());
            this.battleView = new BattleView(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage(), battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
            battleScene = new Scene(battleView.getRoot());

            initEventHandler();

            // switch to start scene
            primaryStage.setScene(startScene);
        });

        // ------- Transition and End Scene -------
        transitionAndEndGameView.getYesBtn().setOnMouseClicked(event -> {
            // send y to sys.in
            UserInput.setUSERINPUT("y");
            UserInput.setCanGetUSERINPUT(true);

            // refresh textArea
            try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){};
            transitionAndEndGameView.updateTextArea(newSysOut.toString());
            newSysOut.reset();
        });

        transitionAndEndGameView.getNoBtn().setOnMouseClicked(event -> {
            // send n to sys.in
            UserInput.setUSERINPUT("n");
            UserInput.setCanGetUSERINPUT(true);

            // refresh textArea
            try{TimeUnit.SECONDS.sleep(1);}catch(Exception e){};
            transitionAndEndGameView.updateTextArea(newSysOut.toString());
            newSysOut.reset();
        });



        // ------- TEXT VIEW (NOT NEEDED AT END) --------

        // switch from text to gui
        textView.getSwitchSceneBtn().setOnMouseClicked(event -> {
            primaryStage.setScene(battleScene);
        });

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

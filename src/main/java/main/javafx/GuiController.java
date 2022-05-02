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
//    private TextView textView;
    private BattleView battleView;
    private ChoosePokemonView choosePokemonView;
    private StartGameView startGameView;
    private ForfeitAndEndView forfeitView;
    private RuleView ruleView;
    private ForfeitAndEndView endGameView;
    private LoadingScreenView loadingScreenView;

    private Stage primaryStage;
    private Scene guiScene;
    private Scene textScene;
    private Scene battleScene;
    private Scene choosePokemonScene;
    private Scene startScene;
    private Scene forfeitScene;
    private Scene ruleScene;
    private Scene endGameScene;
    private Scene loadingScreenScene;
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
        model = new Model(battleMacro);

        // create all the views
        startGameView = new StartGameView();
//        this.battleView = new BattleView(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage(), battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
//        this.textView = new TextView();
        this.battleView = battleView;
//        this.textView = textView;
        choosePokemonView = new ChoosePokemonView(battleMacro.getBattleMicro().getUserTeam());
        forfeitView = new ForfeitAndEndView();
        ruleView = new RuleView();
        endGameView = new ForfeitAndEndView();
        loadingScreenView = new LoadingScreenView();

        // create scenes from the views
        startScene = new Scene(startGameView.getRoot());
        textScene = new Scene(textView.getRoot());
        battleScene = new Scene(battleView.getRoot());
        choosePokemonScene = new Scene(choosePokemonView.getRoot());
        forfeitScene = new Scene(forfeitView.getRoot());
        ruleScene = new Scene(ruleView.getRoot());
        endGameScene = new Scene(endGameView.getRoot());
        loadingScreenScene = new Scene(loadingScreenView.getRoot());

        // start the game with the StartGameScene
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(startScene);
        this.primaryStage.sizeToScene();
        this.primaryStage.show();

        this.primaryStage.setTitle("Pokemon BattleFactory Simulator");

        this.primaryStage.setResizable(false);

        model.run();
        initEventHandler();
    }

    public void initEventHandler() {
        // ------- event handler to switch scenes on the main stage -------

        // ---------- START GAME ------------------

        startGameView.getStart_game().setOnMouseClicked(event -> {

            // restart main game loop
            //model.getNewThread();

            updateChoosePokemonScene();

            // switch to choosepokemonscene
            primaryStage.setScene(choosePokemonScene);
        });

        startGameView.getExit_btn().setOnMouseClicked(event -> {
            // terminate the program
            exit(0);

        });

        startGameView.getRule().setOnMouseClicked(event -> {
            // switch to rule scene (rules are hardcoded in)
            primaryStage.setScene(ruleScene);
        });


        // -------------------- BATTLE VIEW -------------------

//        battleView.getRefreshTextAreaBtn().setOnMouseClicked(event -> {
//            battleView.updateBottomLeftTextBox(newSysOut.toString());
//        });


//        // switch from gui to text
//        battleView.getSwitchSceneBtn().setOnMouseClicked(event -> {
//              primaryStage.setScene(textScene);
//        });

        // attack switch forfeit buttons
        battleView.getAttack().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT("attack");
            UserInput.setCanGetUSERINPUT(true);

            try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){};

            // show moves box
            battleView.bottomRightBoxToggleChoices(0);
        });

        battleView.getSwitch().setOnMouseClicked(event -> {
            // if player has a pokemon alive to switch to
            if(battleMacro.getBattleMicro().getUser().getPokemonTeam().get(1).getIsAlive() || battleMacro.getBattleMicro().getUser().getPokemonTeam().get(2).getIsAlive()) {
//                System.out.println("sendSwitch command");
                UserInput.setUSERINPUT("Switch");
                UserInput.setCanGetUSERINPUT(true);


                // show what pokemon user can choose from
                try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

                battleView.updateBottomLeftTextBox(newSysOut.toString());
                //            newSysOut.reset();

                // TODO switch to the moves buttons and rename them with the names of the remaining
                //   pokemon that user can choose from
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
            } else {
                // no pokemon alive to switch to, tell user
                System.out.println("You have no alive Pokemon left to switch to.");
            }
        });

        battleView.getForfeit().setOnMouseClicked(event -> {
            newSysOut.reset();
            UserInput.setUSERINPUT("forfeit");
            UserInput.setCanGetUSERINPUT(true);

            // tell user if they really want to forfeit
            battleView.updateBottomLeftTextBox(newSysOut.toString());

            // transition to end scene
            try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){};
            primaryStage.setScene(forfeitScene);
            forfeitView.updateTextArea(newSysOut.toString());
        });


        // MOVES
        battleView.getMove1().setOnMouseClicked(event -> {
            newSysOut.reset();
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(0));
            UserInput.setCanGetUSERINPUT(true);

            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

            // check if all of user's pokemon are dead, then switch to end scene
            if(battleMacro.getBattleMicro().getGameOverStatus()){
                endGameView.updateTextArea(newSysOut.toString());
                primaryStage.setScene(endGameScene);
            }

            if(UserInput.getNeedToSwitch()){
                battleView.getPlayerHpBar().setWidth(0); // set hp bar to zero
//                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
//                System.out.println("not approved to switch");
                UserInput.setNeedToSwitch(false);


                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){};

                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update battle view's sprites and names
                updateBattleView();
            }
        });

        battleView.getMove2().setOnMouseClicked(event -> {
            newSysOut.reset();
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(1));
            UserInput.setCanGetUSERINPUT(true);

            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

            // check if all of user's pokemon are dead, then switch to end scene
            if(battleMacro.getBattleMicro().getGameOverStatus()){
                endGameView.updateTextArea(newSysOut.toString());
                primaryStage.setScene(endGameScene);
            }

            if(UserInput.getNeedToSwitch()){
                battleView.getPlayerHpBar().setWidth(0); // set hp bar to zero
//                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){};

                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update battle view's sprites and names
                updateBattleView();
            }
        });

        battleView.getMove3().setOnMouseClicked(event -> {
            newSysOut.reset();
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(2));
            UserInput.setCanGetUSERINPUT(true);
            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

            // check if all of user's pokemon are dead, then switch to end scene
            if(battleMacro.getBattleMicro().getGameOverStatus()){
                endGameView.updateTextArea(newSysOut.toString());
                primaryStage.setScene(endGameScene);
            }

            if(UserInput.getNeedToSwitch()){
                battleView.getPlayerHpBar().setWidth(0); // set hp bar to zero
//                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){};

                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update battle view's sprites and names
                updateBattleView();
            }
        });

        battleView.getMove4().setOnMouseClicked(event -> {
            newSysOut.reset();
            UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUser().getCurrPokemon().getMove(3));
            UserInput.setCanGetUSERINPUT(true);
            UserInput.waitPlayerDied();
            try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

            // check if all of user's pokemon are dead, then switch to end scene
            if(battleMacro.getBattleMicro().getGameOverStatus()){
                endGameView.updateTextArea(newSysOut.toString());
                primaryStage.setScene(endGameScene);
            }

            if(UserInput.getNeedToSwitch()){
                battleView.getPlayerHpBar().setWidth(0); // set hp bar to zero
//                System.out.println("approved to switch");
                battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                battleView.bottomRightBoxToggleChoices(2);
                UserInput.setNeedToSwitch(false);
            } else {
                // TODO show results of battle outcome in text area
//            newSysOut.reset();
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){};

                battleView.updateBottomLeftTextBox(newSysOut.toString());
//            newSysOut.reset();

                // toggle to show 3 moves after fight
                battleView.bottomRightBoxToggleChoices(1);

                // update battle view's sprites and names
                updateBattleView();
            }


        });


        battleView.getPoke0Btn().setOnMouseClicked(event -> {
            if(battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive()) {
                // if alive, switch to that pokemon
                UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUserTeam().get(1).getID());
                UserInput.setCanGetUSERINPUT(true);

                try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}
                // after switch, bot will attack me, check if need to switch if user gets one shot
                if(UserInput.getNeedToSwitch()){
                    battleView.getPlayerHpBar().setWidth(0); // set hp bar to zero
                    System.out.println("approved to switch");
                    battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                    battleView.bottomRightBoxToggleChoices(2);
                    UserInput.setNeedToSwitch(false);
                } else {

                    // update text area
                    try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

                    battleView.updateBottomLeftTextBox(newSysOut.toString());
                    newSysOut.reset();

                    // switch box back to 3 choices
                    battleView.bottomRightBoxToggleChoices(1);

                    // update battle view's sprites and names
                    updateBattleView();

                    // check if all of user's pokemon are dead, then switch to end scene
                    if (battleMacro.getBattleMicro().getGameOverStatus()) {
                        endGameView.updateTextArea(newSysOut.toString());
                        primaryStage.setScene(endGameScene);
                    }
                }
            }
            // if dead, do nothing
        });

        battleView.getPoke1Btn().setOnMouseClicked(event -> {
            if(battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive()) {
                // if alive, switch to that pokemon
                UserInput.setUSERINPUT(battleMacro.getBattleMicro().getUserTeam().get(2).getID());
                UserInput.setCanGetUSERINPUT(true);

                try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

                // after switch, bot will attack me, check if need to switch if user gets one shot
                if(UserInput.getNeedToSwitch()){
                    battleView.getPlayerHpBar().setWidth(0); // set hp bar to zero
                    System.out.println("approved to switch");
                    battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
                    battleView.bottomRightBoxToggleChoices(2);
                    UserInput.setNeedToSwitch(false);
                } else {

                    // update text area
                    try {TimeUnit.MILLISECONDS.sleep(100);} catch (Exception e) {}

                    battleView.updateBottomLeftTextBox(newSysOut.toString());
                    newSysOut.reset();

                    // switch box back to 3 choices
                    battleView.bottomRightBoxToggleChoices(1);

                    // update battle view's sprites and names
                    updateBattleView();

                    // check if all of user's pokemon are dead, then switch to end scene
                    if (battleMacro.getBattleMicro().getGameOverStatus()) {
                        endGameView.updateTextArea(newSysOut.toString());
                        primaryStage.setScene(endGameScene);
                    }
                }
            }
            // if dead, do nothing
        });




        // ----------- CHOOSE POKEMON VIEW -------------

        // choose pokemon
        choosePokemonView.getCheckMark().setOnMouseClicked(event -> {
            UserInput.setUSERINPUT(choosePokemonView.getCurrPokemonID());

//            System.out.println(choosePokemonView.getCurrPokemonID());

            UserInput.setCanGetUSERINPUT(true);
            choosePokemonView.incrementChosenPokemonCounter();

            // remove chosen pokemon from being able to be chosen again
            choosePokemonView.getChooseFromPoke().remove(choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()));
            choosePokemonView.getAllPokeImgs().remove(choosePokemonView.getCurrPokeInd());

            // increment counter, then set curr pokemon to be where the new index is at
            choosePokemonView.incCurrPokeInd();

            choosePokemonView.getMoveDesc().setText(newSysOut.toString());
            updateChoosePokemonScene();

            choosePokemonView.getMoveDesc().setText(newSysOut.toString());


            // check if need to switch scene when 3 pokemon are chosen
            if(choosePokemonView.getPokemonChosenCounter() == 3){
                newSysOut.reset();
                // update the battlescene with player's curr pokemon
                // sleep to allow botteam to update
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){}
                // update battle view's sprites and names
                updateBattleView();

                // change to battlescene
                primaryStage.setScene(battleScene);
            }
        });

        // left arrow cycle pokemon, subtract index, wrap around if needed
        choosePokemonView.getLeftArrow().setOnMouseClicked(event -> {
            // decrement counter, then set curr pokemon to be where the new index is at
            choosePokemonView.decCurrPokeInd();
            updateChoosePokemonScene();
        });

        // right arrow cycle pokemon, add index, wrap around if neeeded
        choosePokemonView.getRightArrow().setOnMouseClicked(event -> {
            // increment counter, then set curr pokemon to be where the new index is at
            choosePokemonView.incCurrPokeInd();
            updateChoosePokemonScene();
        });


        //In choosePokemonscene, display move description when move button is clicked
        choosePokemonView.getCurrViewPokemon().setOnMouseClicked(event -> {
            String PokeDes = choosePokemonView.getChooseFromPoke().get(choosePokemonView.getCurrPokeInd()).toSmallString();
            try {
                newSysOut.reset();
            }catch(Exception e){
//                System.out.println("???");
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
//                System.out.println("???");
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
//                System.out.println("???");
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
//                System.out.println("???");
            }
            System.out.println(movesInventory.getMove(moveName));
            choosePokemonView.getMoveDesc().clear();
            choosePokemonView.getMoveDesc().appendText(newSysOut.toString());
            choosePokemonView.getMoveDesc().setScrollTop(Double.MAX_VALUE);
        });

        // exit button returns player to the start game scene
        choosePokemonView.getExitBtn().setOnMouseClicked(event -> {

//            primaryStage.setScene(loadingScreenScene);

            UserInput.setUSERINPUT("exit");
            UserInput.setCanGetUSERINPUT(true);

            // switch to the starting scene

            // stop the model and reset pokemon view pointers (if go back to choosepokescene, pointer will be 0)
            //model.stop();
            choosePokemonView.resetPointers();

            // switch to loading screen, wait, and do stuff before switching back to original scene

            try{TimeUnit.MILLISECONDS.sleep(2000);}catch(Exception e){}

            // update choosepokemonview with new team of 6 generated from battlemacro
//            System.out.println("\n\n\ngeting user team from macro\n\n\n");
            choosePokemonView.setOriginalPoketeamAndCleanChooseFromPoke(battleMacro.getBattleMicro().getUserTeam());

            // reset teams in battlemacro, and recreate everything
//            try{battleMacro.reset();}catch(Exception e){}
//            battleMacro.getBattleMicro().generateInitialPlayerRandomTeam();
//            battleMacro.getBattleMicro().generateInitialBotRandomTeam();
//            try{battleMacro = new BattleMacro();}catch(Exception e){}
//            battleMacro.getBattleMicro().generateInitialBotRandomTeam();
//
//            try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){}
//
//            model.setBattleMacro(battleMacro);
//            choosePokemonView = new ChoosePokemonView(battleMacro.getBattleMicro().getUserTeam());
//            choosePokemonScene = new Scene(choosePokemonView.getRoot());
//            this.battleView = new BattleView(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage(), battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
//            battleScene = new Scene(battleView.getRoot());
//            initEventHandler();

            // switch to start scene
            primaryStage.setScene(startScene);
        });

        // ------- Forfeit Scene -------
        forfeitView.getYesBtn().setOnMouseClicked(event -> {
            // send y to sys.in
            UserInput.setUSERINPUT("y");
            UserInput.setCanGetUSERINPUT(true);

            // switch to end game scene
            newSysOut.reset();
            System.out.println("Do you want to play again? (y/n)");
            endGameView.updateTextArea(newSysOut.toString());

//            endGameView.getYesBtn().setText("Yes - end");
//            endGameView.getNoBtn().setText("No - end");
            primaryStage.setScene(endGameScene);
        });

        forfeitView.getNoBtn().setOnMouseClicked(event -> {
            // send n to sys.in
            UserInput.setUSERINPUT("n");
            UserInput.setCanGetUSERINPUT(true);

            // go to battleview
            primaryStage.setScene(battleScene);
        });

        // -------- rule scene -------
        ruleView.getBackButton().setOnMouseClicked(event -> {
//            primaryStage.setScene(loadingScreenScene);
//            this.primaryStage.sizeToScene();
//            primaryStage.show();

//            try{TimeUnit.MILLISECONDS.sleep(2000);}catch(Exception e){}
            primaryStage.setScene(startScene);

//            Runnable r = () -> {
//                try{TimeUnit.MILLISECONDS.sleep(2000);}catch(Exception e){}
//                primaryStage.setScene(startScene);
//            };
//            Thread t = new Thread(r);
//            t.start();

        });

        // ------- end game scene ------
        endGameView.getYesBtn().setOnMouseClicked(event -> {
            // send y to sys.in
            UserInput.setUSERINPUT("y");
            UserInput.setCanGetUSERINPUT(true);



            // wait
            try{TimeUnit.MILLISECONDS.sleep(2200);}catch(Exception e){}

            // update choosepokemonview with new team of 6 generated from battlemacro
//            System.out.println("\n\n\ngeting user team from macro\n\n\n");
            choosePokemonView.setOriginalPoketeamAndCleanChooseFromPoke(battleMacro.getBattleMicro().getUserTeam());
            // update choosefrompokemon scene (should display 6 new random poke to choose from)
            updateChoosePokemonScene();
            // switch to scene
            newSysOut.reset();
            primaryStage.setScene(choosePokemonScene);
        });

        endGameView.getNoBtn().setOnMouseClicked(event -> {
            // send n to sys.in
            newSysOut.reset();
            UserInput.setUSERINPUT("n");
            UserInput.setCanGetUSERINPUT(true);

            // refresh textArea
            try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){};

            battleMacro.printExitGameMessage();
            endGameView.updateTextArea(newSysOut.toString()); // print end game stats

            // remove all buttons in this end scene, player only can exit now.
            endGameView.getYesBtn().setDisable(true);
            endGameView.getYesBtn().setVisible(false);

            endGameView.getNoBtn().setDisable(true);
            endGameView.getNoBtn().setVisible(false);
        });




//        // ------- TEXT VIEW (NOT NEEDED AT END) --------
//
//        // switch from text to gui
//        textView.getSwitchSceneBtn().setOnMouseClicked(event -> {
//            primaryStage.setScene(battleScene);
//        });
//
//        // refresh the screen when we press the refresh button
//        textView.getBtn1().setOnMouseClicked(Event -> {
//            refreshTextAreaWithSysOut();
//        });
//
//        // get user input from the textField box
//        textView.getInputTextField().setOnAction(event -> {
//            // read in user input from System.in
//            String stringToPassIntoNewSysIn = textView.getInputTextField().getText();
//
//            textView.getTestBottomLabel().setText(stringToPassIntoNewSysIn);
//
//            UserInput.setUSERINPUT(stringToPassIntoNewSysIn);
//            UserInput.setCanGetUSERINPUT(true);
//            try{
//                TimeUnit.MILLISECONDS.sleep(100);
//            }catch(Exception e){
//
//            }
//            refreshTextAreaWithSysOut();
//        });



    }

    private void updateChoosePokemonScene() {
        // update choosepokemon scene
        choosePokemonView.updateCurrViewPokemon();
        choosePokemonView.setAllMoves();
    }

    /**
     * update Battle View's sprites and names
     */
    public void updateBattleView(){
        try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){}

        // update view with new sprites and moves
        battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());
        battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

        battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
        battleView.setBotCurrPokemon(battleMacro.getBattleMicro().getBotTeam().get(0)); // update curr bot poke
        battleView.updatePokemonSprites(); // update sprites and names shown
        battleView.updateMovesBox(); // update 4moves box with curr pokemon move names

        // update both pokemon's healthbar
        double newPercentageHealthPlayer = battleMacro.getBattleMicro().getUser().getCurrPokemon().getHP() / battleMacro.getBattleMicro().getUser().getCurrPokemon().getMaxHp();
        double newPercentageHealthBot = battleMacro.getBattleMicro().getBotTeam().get(0).getHP() / battleMacro.getBattleMicro().getBotTeam().get(0).getMaxHp();
        double newHealthWidthPlayer = (newPercentageHealthPlayer * battleView.getNAMETEXTBARWIDTH());
        double newHealthWidthBot = (newPercentageHealthBot * battleView.getNAMETEXTBARWIDTH());

        battleView.getPlayerHpBar().setWidth(newHealthWidthPlayer);
        battleView.getBotHpBar().setWidth(newHealthWidthBot);

    }



//    // ------------------- for the Text scene, which won't be used in final product -----
//    /**
//     * Refresh text area of the TextView
//     */
//    public void refreshTextAreaWithSysOut(){
//        textView.getOutputText().setText("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
//        textView.getOutputText().appendText(newSysOut.toString());
//        textView.getOutputText().setScrollTop(Double.MAX_VALUE); // set scrolled to bottom
//    }



}

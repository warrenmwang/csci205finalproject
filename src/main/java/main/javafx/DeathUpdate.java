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


import main.BattleMacro;

public class DeathUpdate {
    private static boolean playerCurrPokemonDied = false;
    private static boolean botCurrPokemonDied = false;
    private static GuiController guiController;

    public static boolean getPlayerCurrPokemonDied(){return playerCurrPokemonDied;}
    public static void setPlayerCurrPokemonDied(boolean bool){playerCurrPokemonDied = bool;}
    public static void setBotCurrPokemonDied(boolean bool){botCurrPokemonDied = bool;}
    public static void setGuiController(GuiController gc){guiController = gc;}

    // makes bottom right box to show remaining alive pokemon to choose from
    public static void switchOtherPokemonToChooseFromBox(){
        System.out.println("entered switchOtherPokemonToChooseFromBox");

        BattleView battleView = guiController.getBattleView();
        System.out.println("battleview get");
        BattleMacro battleMacro = guiController.getBattleMacro();
        System.out.println("battlemacro getted");
        battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
        System.out.println("switch poke updated");
        battleView.bottomRightBoxToggleChoices(2);


        System.out.println("exiting switchOtherPokemonToChooseFromBox");
    }

    public static void checkNeedUpdateBattleViewSprite(){
        System.out.println("enter checkNeedUpdateBattleViewSprite");

        if(playerCurrPokemonDied){
            System.out.println("playerCurrPokemonDied");

            // reset var
            playerCurrPokemonDied = false;

            BattleView battleView = guiController.getBattleView();
            BattleMacro battleMacro = guiController.getBattleMacro();


            // update the sprites and moves in the BattleView
            // update view with new sprites and moves
            battleView.setPlayerPokemonImageURL(battleMacro.getBattleMicro().getUserTeam().get(0).getPlayerImage());

            battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
            battleView.updatePokemonSprites(); // update sprites shown
            battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
        }

        if(botCurrPokemonDied){
            System.out.println("botCurrPokemonDied");

            // reset var
            botCurrPokemonDied = false;

            BattleView battleView = guiController.getBattleView();
            BattleMacro battleMacro = guiController.getBattleMacro();


            // update the sprites and moves in the BattleView
            // update view with new sprites and moves
            battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());

            //battleView.setCurrPokemon(battleMacro.getBattleMicro().getUserTeam().get(0)); // update curr poke
            battleView.updatePokemonSprites(); // update sprites shown
            battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
        }

        System.out.println("leaving checkNeedUpdateBattleViewSprite");

    }
}

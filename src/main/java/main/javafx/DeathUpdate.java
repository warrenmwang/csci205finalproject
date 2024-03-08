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
    public static void setPlayerCurrPokemonDied(boolean bool){playerCurrPokemonDied = bool;}
    public static void setBotCurrPokemonDied(boolean bool){botCurrPokemonDied = bool;}
    public static void setGuiController(GuiController gc){guiController = gc;}

    // makes bottom right box to show remaining alive pokemon to choose from
    public static void switchOtherPokemonToChooseFromBox(){
        BattleView battleView = guiController.getBattleView();
        BattleMacro battleMacro = guiController.getBattleMacro();
        // NOTE: what the fuck is this super long line -- 2 years later me 
        battleView.updateSwitchPoke(battleMacro.getBattleMicro().getUserTeam().get(1).getName(), battleMacro.getBattleMicro().getUserTeam().get(1).getIsAlive(), battleMacro.getBattleMicro().getUserTeam().get(2).getName(), battleMacro.getBattleMicro().getUserTeam().get(2).getIsAlive());
        battleView.bottomRightBoxToggleChoices(2);
    }

    public static void checkNeedUpdateBattleViewSprite(){

        if(playerCurrPokemonDied){

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

            // reset var
            botCurrPokemonDied = false;

            BattleView battleView = guiController.getBattleView();
            BattleMacro battleMacro = guiController.getBattleMacro();


            // update the sprites and moves in the BattleView
            // update view with new sprites and moves
            battleView.setBotPokemonImageURL(battleMacro.getBattleMicro().getBotTeam().get(0).getBotImage());

            battleView.updatePokemonSprites(); // update sprites shown
            battleView.updateMovesBox(); // update 4moves box with curr pokemon move names
        }
    }
}

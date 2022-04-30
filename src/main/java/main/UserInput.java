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


package main;

import java.util.concurrent.TimeUnit;

public class UserInput {
    private static String USERINPUT = "";
    private static Boolean canGetUSERINPUT = false;
    private static boolean canGetSwitch = false;
    private static boolean needToSwitch = false;

    public static String getUSERINPUT() { return USERINPUT; }
    private static Boolean getCanGetUSERINPUT() { return canGetUSERINPUT; }
    public static void setUSERINPUT(String input){ USERINPUT = input; }
    public static void setCanGetUSERINPUT(Boolean status){ canGetUSERINPUT = status;}


    public static void setNeedToSwitch(boolean status) {needToSwitch = status;}
    public static void setCanGetSwitch(boolean status){canGetSwitch = status;}
    public static boolean getNeedToSwitch(){return needToSwitch;}


    public static String readInputLine(){
        waitFXinput();
        return USERINPUT;
    }

    public static void waitFXinput(){
        while(true){
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            }catch(Exception e){}

            if(canGetUSERINPUT)break;
        }

        canGetUSERINPUT = false;
    }

    public static void waitPlayerDied() {
        while (true) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (Exception e) {}

            if (canGetSwitch) break;
        }
        canGetSwitch = false;
    }
}

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

    public static String getUSERINPUT() { return USERINPUT; }
    private static Boolean getCanGetUSERINPUT() { return canGetUSERINPUT; }
    public static void setUSERINPUT(String input){ USERINPUT = input; }
    public static void setCanGetUSERINPUT(Boolean status){ canGetUSERINPUT = status;}

//    public UserInput(){
//        canGetUSERINPUT = false;
//        USERINPUT = "";
//    }

    public static String readInputLine(){
        waitFXinput();
        return USERINPUT;
    }

    public static void waitFXinput(){
        while(true){
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            }catch(Exception e){

            }

            if(canGetUSERINPUT)break;
        }
        //System.out.println("out");


        canGetUSERINPUT = false;

    }
}

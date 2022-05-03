package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {
    private Runnable r;
    private Thread t;

    @BeforeEach
    void setUp(){
        r = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                UserInput.setUSERINPUT("sendingData");
                UserInput.setCanGetUSERINPUT(true);

                TimeUnit.MILLISECONDS.sleep(10);
                UserInput.setNeedToSwitch(true);
                UserInput.setCanGetSwitch(true);

            } catch (Exception e) {}
        };
        t = new Thread(r);
        t.start();

    }




    @Test
    void readInputLineTest() {

        assertEquals("sendingData",UserInput.readInputLine());

    }

    @Test //If the program can pass the waitFXinput, then it means this method works.
         // this method wait until the thread changes the canGetUserInput to true
    void waitFXinputTest() {
        UserInput.waitFXinput();
        assertEquals(false,UserInput.getCanGetUserInput());

    }

    @Test
    void waitPlayerDiedTest() {

        UserInput.waitPlayerDied();
        assertEquals(false,UserInput.getCanGetSwitch());
    }
}
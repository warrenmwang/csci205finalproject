
package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // Ideally, just run the Battle Macro game loop to let the game go.
        BattleMacro battleMacro = new BattleMacro();
        battleMacro.mainGameLoop();
    }
}

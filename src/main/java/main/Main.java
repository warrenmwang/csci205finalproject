
package main;

import main.javafx.JavaFX;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // start javafx and the main game
        JavaFX gui = new JavaFX();
        gui.main(args);
    }
}
package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    private Move testMove;
    @BeforeEach
    void setUp() {
        testMove = new Move("MoveName","1,2,typing,designation,priority,effect");
    }

    @Test
    void getName() {
        assertEquals("MoveName",testMove.getName());
    }

    @Test
    void getBasePower() {
        assertEquals(1,testMove.getBasePower());
    }

    @Test
    void getAccuracy() {
        assertEquals(2,testMove.getAccuracy());
    }

    @Test
    void getType() {
        assertEquals("typing",testMove.getType());
    }

    @Test
    void getDesignation() {
        assertEquals("designation",testMove.getDesignation());
    }

    @Test
    void setBasePower() {
        testMove.setBasePower(23);
        assertEquals(23,testMove.getBasePower());

    }


}
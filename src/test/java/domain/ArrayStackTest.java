package domain;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    @Test
    void test(){
        ArrayStack arrayStack = new ArrayStack(10);
        for (int i = 0; i < 10; i++){
            arrayStack.push(util.Utility.random(30));
        }
    }
}
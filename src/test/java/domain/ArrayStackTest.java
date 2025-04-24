package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    @Test
    void test(){
        ArrayStack arrayStack = new ArrayStack(10);
        try {
        for (int i = 0; i < 10; i++){
                arrayStack.push(util.Utility.random(30));
        }
        }catch (StackException e) {throw new RuntimeException(e);}
        Person p1=new Person(1, "Alana", 18);
        Person p2=new Person(2, "Pablo", 20);
        Person p3 =new Person(3, "Ana", 21);
        Person p4= new Person(4, "María", 20);
        Person p5=new Person(5, "Victoria", 18);
        Person p6=new Person(6, "Nicole", 19);
        Person p7=new Person(7, "Mateo", 18);
        Person p8=new Person(8, "Nicole", 23);
        Person p9=new Person(9, "Alana", 22);
        Person p10=new Person(10, "Pablo", 19);
        Person p11=new Person(11, "Ana", 18);
    }
}
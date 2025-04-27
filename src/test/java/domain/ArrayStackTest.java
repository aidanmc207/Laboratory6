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
        ArrayStack stack=new ArrayStack(11);
        try {
            stack.push(p1);
            stack.push(p2);
            stack.push(p3);
            stack.push(p4);
            stack.push(p5);
            stack.push(p6);
            stack.push(p7);
            stack.push(p8);
            stack.push(p9);
            stack.push(p10);
            stack.push(p11);
        }catch (StackException e){}
        System.out.println(stack);
        desapilar(stack);
        System.out.println(stack);

    }

    private void desapilar(ArrayStack stack) {
        ArrayStack aux = new ArrayStack(stack.size()); // Pila auxiliar

        try {
            while (!stack.isEmpty()) {
                Person p = (Person) stack.pop();
                if ((p.getAge() == 18 && p.getName().startsWith("A")) ||
                        (p.getName().equals("Nicole") && p.getAge() == 19) ||
                        (p.getAge() >= 20 && p.getAge() <= 23)) {//Aqui metí todo en una condicion
                } else {
                    aux.push(p);//se guarda
                }
            }
            while (!aux.isEmpty()) {
                stack.push(aux.pop());//llenamos la pila de nuevo
            }
        } catch (StackException e) {
            e.printStackTrace();
        }
    }
}

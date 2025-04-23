package domain;

import java.util.Arrays;

public class ArrayStack implements Stack {
    private int n;
    private Object[] dataStack;
    private int top;
    public ArrayStack(int n) {
        if (n <= 0)System.exit(1);
        this.n = n;
        dataStack = new Object[n];
        top = -1;//la pila inicia vacia
    }
    @Override
    public void push(Object o) {
        if (top <= n-1)
            dataStack[++top] = o;
    }

    @Override
    public Object pop() {
        if (isEmpty())
            System.exit(1);
        Object o = dataStack[top];
        dataStack[top] = null;
        top--;
        return o;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Array Strack is Empty";
        String result = "Array Stack Content: \n";
        ArrayStack aux = new ArrayStack(size());
        while (!isEmpty()){
            result += peek()+" ";
            aux.push(pop());
        }
        while (!aux.isEmpty()){
            push(aux.pop());
        }
        return result;
    }

    @Override
    public Object peek() {
        return dataStack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public Object top() {
        return peek();
    }

    @Override
    public int size() {
        return n-top;
    }

    @Override
    public void clear() {
        this.dataStack = new Object[this.n];
    }
}

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
    public void push(Object o)throws StackException {
        if (top == n)
            throw new StackException("La lista está llena");
            dataStack[++top] = o;
    }

    @Override
    public Object pop()throws StackException {
        if (isEmpty())
            throw new StackException("La lista está vacia");
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
        try {
            while (!isEmpty()) {
                result += peek() + " ";
                aux.push(pop());
            }
            while (!aux.isEmpty()) {
                push(aux.pop());
            }
        }catch (StackException e){}
        return result;
    }

    @Override
    public Object peek()throws StackException {
        if (isEmpty())
            throw new StackException("La lista está vacia");
        return dataStack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public Object top()throws StackException {
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

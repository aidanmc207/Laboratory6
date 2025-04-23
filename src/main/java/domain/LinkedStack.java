package domain;

public class LinkedStack implements Stack {
    private Node top;
    private int counter;

    public LinkedStack() {
        this.top = null;
        this.counter = 0;
    }

    @Override
    public void push(Object o) {
        Node newNode= new Node(o);
        if (this.top == null) {
            this.top = newNode;
            counter++;
        }
        else {
            this.top.next = newNode;
            counter++;
        }
    }

    @Override
    public Object pop() {
        Object result = this.top.data;
        this.top = this.top.next;
        counter--;
        return result;
    }

    @Override
    public Object peek() {
        return this.top.data;
    }

    @Override
    public boolean isEmpty() {
        return top.data == null;
    }

    @Override
    public Object top() {
        return peek();
    }

    @Override
    public int size() {
        return this.counter;
    }

    @Override
    public void clear() {
        this.top = null;
        this.counter = 0;
    }
}

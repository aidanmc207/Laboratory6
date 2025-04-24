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
        Node newNode = new Node(o);
        newNode.next = this.top;
        this.top = newNode;
        counter++;
    }

    @Override
    public Object pop()throws StackException {
        if (isEmpty())
            throw new StackException("La lista está vacia");
        Object result = this.top.data;
        this.top = this.top.next;
        counter--;
        return result;
    }

    @Override
    public Object peek()throws StackException {
        if (isEmpty())
            throw new StackException("La lista está vacia");
        return this.top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Object top()throws StackException {
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

package domain;

public interface Stack {
    public void push(Object o)throws StackException;
    public Object pop()throws StackException;
    public Object peek()throws StackException;
    public boolean isEmpty();
    public Object top()throws StackException;
    public int size();
    public void clear();
}
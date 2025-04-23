package domain;

public interface Stack {
    public void push(Object o);
    public Object pop();
    public Object peek();
    public boolean isEmpty();
    public Object top();
    public int size();
    public void clear();
}
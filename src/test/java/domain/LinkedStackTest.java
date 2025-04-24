package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void size() {
        String balanced = "({[]})";
        String notBalanced="([)]";
        System.out.println(isBalanced(balanced)? balanced+ " está balanceado":balanced+ " no está balanceado");
        System.out.println(isBalanced(notBalanced)? notBalanced+ " está balanceado":notBalanced+ " no está balanceado");

    }
    private boolean isBalanced(String exp) {
        LinkedStack stack = new LinkedStack();
        try {
            for (char ch : exp.toCharArray()) {
                if (ch == '(' || ch == '[' || ch == '{') {
                    stack.push(ch);
                } else if (ch == ')' || ch == ']' || ch == '}') {
                    if (stack.isEmpty()) return false;
                    char aux = (char) stack.pop();
                    if (!pairsWith(aux, ch)) return false;
                }
            }
        } catch (StackException e) {
            return false;
        }
        return stack.isEmpty();
    }

    private boolean pairsWith(char ch1, char ch2) {
        switch (ch1) {
            case '(': return ch2 == ')';
            case '[': return ch2 == ']';
            case '{': return ch2 == '}';
            default: return false;
        }
    }
}
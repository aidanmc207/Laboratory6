package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void size() {
        String balanced = "({[]})";
        String notBalanced="([)]";
        isBalanced("({[]})");
        isBalanced("([])");
        isBalanced("([)]");
        isBalanced("((()))");
        isBalanced("{[}");
        isBalanced("]");
        isBalanced("");
        System.out.println(isBalanced(balanced)? balanced+ " está balanceado":balanced+ " no está balanceado");
        System.out.println(isBalanced(notBalanced)? notBalanced+ " está balanceado":notBalanced+ " no está balanceado");
        System.out.println(isBalanced("({[]})") ? "({[]}) está balanceado" : "({[]}) no está balanceado");
        System.out.println(isBalanced("([])") ? "([]) está balanceado" : "([]) no está balanceado");
        System.out.println(isBalanced("([)]") ? "([)] está balanceado" : "([)] no está balanceado");
        System.out.println(isBalanced("((()))") ? "((())) está balanceado" : "((())) no está balanceado");
        System.out.println(isBalanced("{[}") ? "{[} está balanceado" : "{[} no está balanceado");
        System.out.println(isBalanced("]") ? "] está balanceado" : "] no está balanceado");
        System.out.println(isBalanced("") ? "\"\" está balanceado" : "\"\" no está balanceado");

    }
    @Test
    void decimaltoBinaryTest() {
        int[] numbers = {0, 3, 4, 5, 6, 7, 9, 10, 15, 17, 23, 32, 255, 1023, 1025, 4192, 8586};
        try {
        for (int value : numbers)
            System.out.println("Decimal: " + value + " → Binary: " + decimalToBinary(value));
        }catch (StackException e){}
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
    private String decimalToBinary(int number) throws StackException {
        LinkedStack stack = new LinkedStack();
        if (number == 0) return "0";
        while (number > 0) {
            stack.push(number%2);
            number = number / 2;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }
}
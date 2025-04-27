package domain;

import java.util.ArrayList;

public class ArithmeticConverter {

    private ArrayStack arrayStack;
    private String expression;
    private String firstResult;
    private String secondResult;

    public ArithmeticConverter(String expression) {
        this.expression = expression;
        arrayStack = new ArrayStack(expression.length());
    }

    public String infixToPostfix() {
        StringBuilder result = new StringBuilder();
        Stack stack = new LinkedStack();

        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (Character.isLetterOrDigit(c)) {
                    result.append(c);
                }
                else if (c == '(') {
                    stack.push(c);
                }
                else if (c == ')') {
                    while (!stack.isEmpty() && !stack.peek().equals('(')) {
                        result.append(stack.pop());
                    }
                    if (!stack.isEmpty() && stack.peek().equals('(')) {
                        stack.pop(); // quitar el paréntesis abierto
                    } else {
                        return "Expresión inválida (paréntesis desbalanceado)";
                    }
                }
                else { // operador
                    while (!stack.isEmpty() && precedence(c) <= precedence((char) stack.peek())) {
                        result.append(stack.pop());
                    }
                    stack.push(c);
                }
            }

            // Sacar todo lo que quede en la pila
            while (!stack.isEmpty()) {
                if (stack.peek().equals('(')) {
                    return "Expresión inválida (paréntesis desbalanceado)";
                }
                result.append(stack.pop());
            }
        } catch (StackException e) {
            return "Error en el manejo de la pila: " + e.getMessage();
        }

        return result.toString();
    }

    public String postfixToInfix() {
        Stack stack = new LinkedStack(); // o ArrayStack, depende de cuál quieras usar

        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + ""); // Se convierte a String y se guarda
                } else { // Es un operador
                    if (stack.size() < 2) {
                        return "Expresión inválida (no hay suficientes operandos)";
                    }
                    String op2 = (String) stack.pop(); // Segundo operando
                    String op1 = (String) stack.pop(); // Primer operando
                    String newExpr = "(" + op1 + c + op2 + ")";
                    stack.push(newExpr); // Lo nuevo se vuelve el tope de la pila
                }
            }

            if (stack.size() != 1) {
                return "Expresión inválida (sobran operandos)";
            }
            return (String) stack.pop(); // Resultado final
        } catch (StackException e) {
            return "Error en el manejo de la pila: " + e.getMessage();
        }
    }

    private int precedence(char ch) {
        switch (ch) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }
}

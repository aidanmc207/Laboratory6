package domain;

import java.util.ArrayList;

public class ArithmeticConverter {

    private LinkedStack stack;
    private String expression;
    private StringBuilder result;

    public ArithmeticConverter() {}

    public String infixToPostfix(String expression) {
        result = new StringBuilder();
        stack = new LinkedStack();
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

    public String infixToPrefix(String expression) {
        Stack operators = new LinkedStack();
        Stack operands = new LinkedStack();
        try {
            for (int i = expression.length() - 1; i >= 0; i--) {
                char c = expression.charAt(i);

                if (Character.isLetterOrDigit(c)) {
                    operands.push(c + "");
                } else if (c == ')') {
                    operators.push(c);
                } else if (c == '(') {
                    while (!operators.isEmpty() && (char) operators.peek() != ')') {
                        processPrefixStep(operators, operands);
                    }
                    if (!operators.isEmpty()) {
                        operators.pop();
                    }
                } else {
                    while (!operators.isEmpty() && precedence((char) operators.peek()) > precedence(c)) {
                        processPrefixStep(operators, operands);
                    }
                    operators.push(c);
                }
            }

            while (!operators.isEmpty()) {
                processPrefixStep(operators, operands);
            }

            return (String) operands.pop();

        } catch (StackException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String postfixToInfix(String expression) {
        stack = new LinkedStack();
        try {
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + "");
                } else {
                    if (stack.size() < 2) {
                        return "Expresión inválida (no hay suficientes operandos)";
                    }
                    String op2 = (String) stack.pop();
                    String op1 = (String) stack.pop();
                    String newExpr = "(" + op1 + c + op2 + ")";
                    stack.push(newExpr);//devuelve al tope de la pila
                }
            }

            if (stack.size() != 1) {
                return "Expresión inválida (sobran operandos)";
            }
            return (String) stack.pop();//resultado
        } catch (StackException e) {
            return "Error en el manejo de la pila: " + e.getMessage();
        }
    }

    public String prefixToInfix(String expression) {
        stack = new LinkedStack();

        try {
            for (int i = expression.length() - 1; i >= 0; i--) {
                char c = expression.charAt(i);

                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + "");
                } else {
                    if (stack.size() < 2) {
                        return "Expresión inválida (faltan operandos)";
                    }
                    String op1 = (String) stack.pop();
                    String op2 = (String) stack.pop();
                    String expr = "(" + op1 + c + op2 + ")";
                    stack.push(expr);
                }
            }

            if (stack.size() != 1) {
                return "Expresión inválida (sobran operandos)";
            }
            return (String) stack.pop();

        } catch (StackException e) {
            return "Error: " + e.getMessage();
        }
    }
    public String postfixToPrefix(String expression){
        String infix = postfixToInfix(expression);
        return infixToPrefix(infix);
    }

    public String prefixToPostfix(String expression){
        String infix = prefixToInfix(expression);
        return infixToPostfix(infix);
    }

    // Método auxiliar para evitar la repetición en un paso de la construcción del prefijo
    private void processPrefixStep(Stack operators, Stack operands) throws StackException {
        String op1 = (String) operands.pop();
        String op2 = (String) operands.pop();
        char op = (char) operators.pop();
        String expr = op + op1 + op2;
        operands.push(expr);
    }


    private int precedence(char ch) {//determina la precedencia de los operadores
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

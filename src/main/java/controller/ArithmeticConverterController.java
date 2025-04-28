package controller;

import domain.ArithmeticConverter;
import domain.StackException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import util.FXUtility;

public class ArithmeticConverterController {

    @javafx.fxml.FXML
    private TextField secondConvertedExpressionTf;
    @javafx.fxml.FXML
    private TextField firstExpressionConvertedTf;
    @javafx.fxml.FXML
    private TextField expressionTextField;
    @javafx.fxml.FXML
    private Label firstConversionText;
    @javafx.fxml.FXML
    private Label secondConversionText;
    @javafx.fxml.FXML
    private RadioButton postfixButton;
    @javafx.fxml.FXML
    private RadioButton prefixButton;
    @javafx.fxml.FXML
    private RadioButton infixButton;

    private String expression;
    private static Alert alert;
    private String expressionType;
    private ArithmeticConverter converter;

    @FXML
    public void initialize() {
        alert = FXUtility.alert("Arithmetic Expression Converter", "Expression Conversion");
        alert.setAlertType(Alert.AlertType.ERROR);
        setResultExpressionNames("", "");
        firstExpressionConvertedTf.setDisable(true);
        secondConvertedExpressionTf.setDisable(true);
        converter = new ArithmeticConverter();
    }

    @javafx.fxml.FXML
    public void infixExpressionOn(ActionEvent actionEvent) {
        expressionType = "Infix";
        postfixButton.setSelected(false);
        prefixButton.setSelected(false);
        setResultExpressionNames("Prefix", "Postfix");
    }

    @javafx.fxml.FXML
    public void postfixExpressionOn(ActionEvent actionEvent) {
        expressionType = "Postfix";
        prefixButton.setSelected(false);
        infixButton.setSelected(false);
        setResultExpressionNames("Prefix", "Infix");
    }

    @javafx.fxml.FXML
    public void prefixExpressionOn(ActionEvent actionEvent) {
        expressionType = "Prefix";
        infixButton.setSelected(false);
        postfixButton.setSelected(false);
        setResultExpressionNames("Postfix", "Infix");
    }

    @javafx.fxml.FXML
    public void convertOnAction(ActionEvent actionEvent) {
        expression = expressionTextField.getText().trim();
        String result="";
        switch (expressionType) {
            case "Infix":
                firstExpressionConvertedTf.setText(converter.infixToPrefix(expression));
                if (converter.isNumericExpression(converter.infixToPostfix(expression))) {
                    try {
                        result=String.valueOf(converter.evaluatePostfix(converter.infixToPostfix(expression)));
                        secondConvertedExpressionTf.setText(converter.infixToPostfix(expression)+"="+result);
                    } catch (StackException e) {
                        throw new RuntimeException(e);
                    }
                }else secondConvertedExpressionTf.setText(converter.infixToPostfix(expression));
                break;
                case "Postfix":
                    firstExpressionConvertedTf.setText(converter.postfixToPrefix(expression));
                    if (converter.isNumericExpression(expression)){
                        try {
                            result=String.valueOf(converter.evaluatePostfix(expression));
                            secondConvertedExpressionTf.setText(converter.postfixToInfix(expression)+"="+result);
                        } catch (StackException e) {
                            throw new RuntimeException(e);
                        }
                    }else{ secondConvertedExpressionTf.setText(converter.postfixToInfix(expression));}
                    break;
                    case "Prefix":
                        firstExpressionConvertedTf.setText(converter.prefixToPostfix(expression));
                        secondConvertedExpressionTf.setText(converter.prefixToInfix(expression));
                        break;
        }
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        firstExpressionConvertedTf.clear();
        secondConvertedExpressionTf.clear();
        expressionTextField.clear();
    }

    private void setResultExpressionNames(String name1, String name2) {
        firstConversionText.setText(name1 + ": ");
        secondConversionText.setText(name2 + ": ");
        firstExpressionConvertedTf.setDisable(false);
        secondConvertedExpressionTf.setDisable(false);
    }
}

package controller;

import domain.ArithmeticConverter;
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

    private static Alert alert;
    private String expressionType;

    @FXML
    public void initialize() {
        alert = FXUtility.alert("Arithmetic Expression Converter", "Expression Conversion");
        alert.setAlertType(Alert.AlertType.ERROR);
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
        //TODO método de conversión de expresiones aritméticas (llamar clase ArithmeticController())
        ArithmeticConverter converter = new ArithmeticConverter(expressionTextField.getText());
        switch (expressionType) {
            case "Infix":
                secondConvertedExpressionTf.setText(converter.infixToPostfix());
                break;
                case "Postfix":
                    secondConvertedExpressionTf.setText(converter.postfixToInfix());
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
    }
}

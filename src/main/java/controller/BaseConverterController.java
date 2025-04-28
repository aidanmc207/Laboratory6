package controller;

import domain.StackException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class BaseConverterController
{
    @javafx.fxml.FXML
    private TextField resultTextField;
    @javafx.fxml.FXML
    private TextField valueTextField;
    private int value;
    private String result;
    private String parametro;
    @javafx.fxml.FXML
    private RadioButton binBTN;
    @javafx.fxml.FXML
    private RadioButton octalBTN;
    @javafx.fxml.FXML
    private RadioButton hexBTN;
    private Alert alert;
    @javafx.fxml.FXML
    private ButtonBar btnBar;

    @javafx.fxml.FXML
    public void initialize() {
        alert= new Alert(Alert.AlertType.INFORMATION);
    }

    @javafx.fxml.FXML
    public void convertButtonOnAction(ActionEvent actionEvent) {
        try {
            if (!this.valueTextField.getText().isEmpty()) {
                if (value!=Integer.parseInt(this.valueTextField.getText().trim())) {
                    value = Integer.parseInt(this.valueTextField.getText().trim());
                }
                this.result = util.Utility.conversor(parametro,value);
                this.resultTextField.setText(result);
            }
        } catch (Exception e) {
            if (!this.valueTextField.getText().isEmpty())
                alert.setContentText("Seleccione una base para convertir");
            else alert.setContentText("Ingrese un numero para convertir");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void hexaOnAction(ActionEvent actionEvent) {
        parametro="Hexadecimal";
        this.binBTN.setSelected(false);
        this.octalBTN.setSelected(false);
        if (!this.valueTextField.getText().isEmpty())
            value=Integer.parseInt(this.valueTextField.getText().trim());
    }

    @javafx.fxml.FXML
    public void octalOnAction(ActionEvent actionEvent) {
        parametro="Octal";
        this.binBTN.setSelected(false);
        this.hexBTN.setSelected(false);
        if (!this.valueTextField.getText().isEmpty())
            value=Integer.parseInt(this.valueTextField.getText().trim());
    }

    @javafx.fxml.FXML
    public void clearButtonOnAction(ActionEvent actionEvent) {
        this.resultTextField.clear();
        this.valueTextField.clear();
        this.result=null;
        parametro=null;
        this.value=0;
        clearButtons();
    }

    private void clearButtons() {
        this.binBTN.setSelected(false);
        this.octalBTN.setSelected(false);
        this.hexBTN.setSelected(false);
    }

    @javafx.fxml.FXML
    public void binaryOnAction(ActionEvent actionEvent) {
        parametro="Binary";
        this.hexBTN.setSelected(false);
        this.octalBTN.setSelected(false);
        if (!this.valueTextField.getText().isEmpty())
            value=Integer.parseInt(this.valueTextField.getText().trim());
    }
}
package application.controller;

import application.model.JShellCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

	@FXML
	Button dotButton;
	@FXML
	Label inputLabel;
	@FXML
	Label operationSubjectLabel;

	private JShellCalculator calc = new JShellCalculator();
	
	private String getButtonValue(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();
		String value = clickedButton.getText();
		return value;
	}

	private void setResult() {

		operationSubjectLabel.setText(operationSubjectLabel.getText() + inputLabel.getText());
		String value = calc.calculate(operationSubjectLabel.getText());
		if (value != null) {
			inputLabel.setText(value);
		} else {
			inputLabel.setText("ERROR");
			showAlert();
			clickC();
		}
	}

	private void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ZJEBA�E�!");
		alert.setHeaderText(null);
		alert.setContentText("Nie umiesz w kalkulator :(");

		alert.showAndWait();

	}

	@FXML
	public void clickDigit(ActionEvent event) {
		String value = getButtonValue(event);

		if (calc.isOperationBefore()) {
			inputLabel.setText(value);
			calc.firstDigitButtonClicked();
		} else
			inputLabel.setText(inputLabel.getText() + value);

	}

	@FXML
	void clickDot(ActionEvent event) {
		if (calc.isOperationBefore()) {
			inputLabel.setText("0.");
			calc.dotButtonClicked();
		} else if (!calc.isDotBefore()) {
			inputLabel.setText(inputLabel.getText() + ".");
			calc.dotButtonClicked();
		}

	}

	@FXML
	public void clickOperation(ActionEvent event) {
		String value = getButtonValue(event);
		if (calc.isOperationBefore() && operationSubjectLabel.getText().length()!=0) 
				operationSubjectLabel.setText(operationSubjectLabel.getText().substring(0, operationSubjectLabel.getText().length()-1) + value);
		else {
			calc.operationButtonClicked();
			setResult();
			operationSubjectLabel.setText(operationSubjectLabel.getText() + value);
		}

	}

	@FXML
	public void clickOneArgumentOperation(ActionEvent event) {
		
		String value = getButtonValue(event);
		calc.operationButtonClicked();
		inputLabel.setText(calc.functionName(value) + "(" + inputLabel.getText() + ")");
	}

	@FXML
	public void clickEquals(ActionEvent event) {
		calc.operationButtonClicked();
		setResult();
		operationSubjectLabel.setText("");
	}

	@FXML
	public void clickC() {
		inputLabel.setText("0");
		operationSubjectLabel.setText("");
		calc.operationButtonClicked();
	}

	@FXML
	public void clickCE() {
		if (!calc.isOperationBefore())
			inputLabel.setText(inputLabel.getText().substring(0, inputLabel.getText().length() - 1));
		if (inputLabel.getText().length() == 0) {
			inputLabel.setText("0");
			calc.operationButtonClicked();
		}

	}

	@FXML
	public void clickSign() {
		calc.operationButtonClicked();
		if (!calc.isNegative(inputLabel.getText()))
			inputLabel.setText("(-" + inputLabel.getText() + ")");
		else
			inputLabel.setText("(-(" + inputLabel.getText() + "))");

	}

}
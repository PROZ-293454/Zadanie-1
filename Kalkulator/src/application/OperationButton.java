package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OperationButton extends MyButton {

	OperationButton(String operation) {
		super(operation);
	}

	@Override
	protected void clickButton(Label inputLabel, Label operationSubjectLabel) {
		Keyboard.setWasOperationPreviously(true);

		operationSubjectLabel.setText(operationSubjectLabel.getText() + inputLabel.getText());
		inputLabel.setText(JshellCalculator.calculate(operationSubjectLabel.getText()));
		operationSubjectLabel.setText(operationSubjectLabel.getText() + value);
	}
}

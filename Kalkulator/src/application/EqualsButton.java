package application;

import javafx.scene.control.Label;

public class EqualsButton extends MyButton {

	EqualsButton(Label inputLabel, Label operationSubjectLabel) {
		super(Character.toString('='));
	}

	@Override
	protected void clickButton(Label inputLabel, Label operationSubjectLabel) {
		Keyboard.setWasOperationPreviously(true);
		operationSubjectLabel.setText(operationSubjectLabel.getText() + inputLabel.getText());
		String value = JshellCalculator.calculate(operationSubjectLabel.getText());
		if (value != null) {
			inputLabel.setText(value);
			operationSubjectLabel.setText("");
		} else
			inputLabel.setText("ERROR");

	}

}

package application;

import javafx.scene.control.Label;

public class DigitButton extends MyButton {

	DigitButton(String digit) {
		super(digit);
	}

	@Override
	protected void clickButton(Label inputLabel, Label operationSubjectLabel) {
		if (Keyboard.WasOperationPreviously()) {

			if (value == ".")
				inputLabel.setText("0" + value);
			else
				inputLabel.setText(value);

			Keyboard.setWasOperationPreviously(false);
		} else
			inputLabel.setText(inputLabel.getText() + value);

	}
}

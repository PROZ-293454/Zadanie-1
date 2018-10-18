package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DigitButton extends Button {

	private char digit;

	DigitButton(char digit, Label inputLabel) {
		super(Character.toString(digit));
		this.digit = digit;
		this.setOnAction(e -> clickButton(inputLabel));
	}

	private void clickButton(Label inputLabel) {
		if (Keyboard.WasOperationPreviously()) {
			if (digit=='.') inputLabel.setText("0"+digit);
			else inputLabel.setText(Character.toString(digit));
			Keyboard.setWasOperationPreviously(false);
		}
		else inputLabel.setText(inputLabel.getText() + digit);
	}
}

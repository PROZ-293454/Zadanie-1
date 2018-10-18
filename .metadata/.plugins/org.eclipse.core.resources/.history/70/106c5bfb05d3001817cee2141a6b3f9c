package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EqualsButton extends Button {
	
	EqualsButton(Label inputLabel, Label operationSubjectLabel) {
		super(Character.toString('='));
		this.setOnAction( e -> clickButton(inputLabel, operationSubjectLabel));	
	}
	

	private void clickButton(Label inputLabel, Label operationSubjectLabel) {
		Keyboard.setWasOperationPreviously(true);
		operationSubjectLabel.setText(operationSubjectLabel.getText()+inputLabel.getText());
		String value = JshellCalculator.calculate(operationSubjectLabel.getText());
		if(value!=null) {
			inputLabel.setText(value);
			operationSubjectLabel.setText("");
		}
		else inputLabel.setText("ERROR");
		
	}
	
	
}

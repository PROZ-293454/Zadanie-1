package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OperationButton extends Button {
private String operation;
	
	OperationButton(String value, Label inputLabel, Label operationSubjectLabel) {
		super(value);
		this.operation=value;
		this.setOnAction( e -> clickButton(inputLabel, operationSubjectLabel));
	}
	

	private void clickButton(Label inputLabel, Label operationSubjectLabel) {
		Keyboard.setWasOperationPreviously(true);
		if (operation=="!") {	
			inputLabel.setText("fact("+inputLabel.getText()+")"); 
		}
		else if (operation=="x\u00b2") {
			inputLabel.setText("sqr("+inputLabel.getText()+")"); 
		}
		else if (operation=="\u221a") {
			inputLabel.setText("sqrt("+inputLabel.getText()+")"); 
		}
		else {
			operationSubjectLabel.setText(operationSubjectLabel.getText()+inputLabel.getText());
			inputLabel.setText(JshellCalculator.calculate(operationSubjectLabel.getText()));
			operationSubjectLabel.setText(operationSubjectLabel.getText()+operation);
		}
	}	
}

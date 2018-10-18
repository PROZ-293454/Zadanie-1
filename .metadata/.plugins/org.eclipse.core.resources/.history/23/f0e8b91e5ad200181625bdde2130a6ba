package application;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Keyboard {

	//private ValueButton[] valueButtons;
	//private EqualsButton equalsButton;
	private Button[] buttons;
	
	private final String[] VALUES = {"+", "-", "*", "/", "!", "x\u00b2", "\u221a"};
	private static boolean wasOperationPreviously=true;


	public static boolean WasOperationPreviously() {
		return wasOperationPreviously;
	}


	public static void setWasOperationPreviously(boolean wasOperationPreviously) {
		Keyboard.wasOperationPreviously = wasOperationPreviously;
	}


	Keyboard(Label inputLabel, Label operationSubjectLabel) {
		
		//valueButtons = new ValueButton[VALUES.length];
		//equalsButton = new EqualsButton(inputLabel);
		buttons = new Button[11+VALUES.length+1];
		int i=0;
		for (i=0; i<10; i++) {
			buttons[i] = new DigitButton((char)(i+'0'), inputLabel);
		}
		buttons[i++] = new DigitButton('.', inputLabel);
		for (String btnValue : VALUES) {
			buttons[i++] = new OperationButton(btnValue, inputLabel, operationSubjectLabel);
		}
		buttons[i] = new EqualsButton(inputLabel, operationSubjectLabel);
		
		for (Button btn : buttons) {
			btn.setPrefSize(60, 60);
			btn.setStyle("-fx-font: 24 arial;");
		}
		
	}
	
	
	public Button[] getButtons() {
		return buttons;
	}
	
	public void setInGridPane(GridPane gridPane) {
		
	}

	
	
	
}

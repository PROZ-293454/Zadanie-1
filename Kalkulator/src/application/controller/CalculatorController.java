package application.controller;

import application.model.JShellCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Klasa bêd¹ca kontrolerem dla widoku zapisanego w pliku CalculatorData.fxml
 * @author Marcin Hanas 293454
 */
public class CalculatorController {

	@FXML
	private Label inputLabel;
	@FXML
	private Label operationSubjectLabel;

	private JShellCalculator calc = new JShellCalculator();

	/**
	 * Metoda s³u¿¹ca do pozyskiwania tekstu naciœniêtego przycisku
	 * @param event - zmienna zawieraj¹ca informacje o naciœniêtym przycisku
	 * @return tekst, który wyœwietla siê na naciœniêtym przycisku
	 */
	private String getButtonValue(ActionEvent event) {
		Button clickedButton = (Button) event.getTarget();
		String value = clickedButton.getText();
		return value;
	}

	/**
	 * Metoda, która oblicza rozwi¹zanie wyra¿enia arytm. zawarte w operationSubjectLabel,
	 * i ustawia jego wynik w inputLabel, b¹dz gdy nie uda³o siê obliczyæ rozwi¹zania, 
	 * wywo³uje komunikat o b³êdzie
	 * @throws ArithmeticException - metoda zwraca wyj¹tek, gdy z³apa³a wyj¹tek ArithmeticException
	 * rzucony przez metodê calculate klasy JShellCalculator - nie uda³o siê obliczyæ rozwi¹zania
	 */
	private void setResult() throws ArithmeticException {

		operationSubjectLabel.setText(operationSubjectLabel.getText() + inputLabel.getText());
		try {
			String value = calc.calculate(operationSubjectLabel.getText());
			inputLabel.setText(value);
			
		} catch (ArithmeticException exception) {
			inputLabel.setText("ERROR");
			showAlert("Wykonujesz dzia³ania niedozwolone");
			clickC();
			throw exception;
		}
	}

	/**
	 * Metoda wyœwietla komunikat o b³êdzie
	 * @param contentText - tekst wyœwietlany w komunikacie, treœæ b³êdu
	 */
	private void showAlert(String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B³¹d dzia³ania kalkulatora");
		alert.setHeaderText(null);
		alert.setContentText(contentText);

		alert.showAndWait();

	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku cyfry.
	 * Dopisuje cyfrê do inputLabel.
	 * @param event - obiekt zawieraj¹cy informacjê, który przycisk zosta³ wciœniêty
	 */
	@FXML
	public void clickDigit(ActionEvent event) {
		String value = getButtonValue(event);

		if (calc.isOperationBefore()) {
			inputLabel.setText(value);
			if (!value.equals("0")) calc.firstDigitButtonClicked();
		} else
			inputLabel.setText(inputLabel.getText() + value);

	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku kropki.
	 * Dopisuje kropkê (b¹dz "0.") do inputLabel.
	 */
	@FXML
	void clickDot() {
		if (calc.isOperationBefore()) {
			inputLabel.setText("0.");
			calc.dotButtonClicked();
		} else if (!calc.isDotBefore()) {
			inputLabel.setText(inputLabel.getText() + ".");
			calc.dotButtonClicked();
		}

	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku operacji: +, -, * lub /.
	 * oblicza wynik operacji z operationSubjectLabel oraz dopisuje znak operacji do operationSubjectLabel.
	 * @param event - obiekt zawieraj¹cy informacjê, który przycisk zosta³ wciœniêty
	 */
	@FXML
	public void clickOperation(ActionEvent event) {
		String value = getButtonValue(event);
		if (calc.isOperationBefore() && operationSubjectLabel.getText().length() != 0)
			operationSubjectLabel.setText(
					operationSubjectLabel.getText().substring(0, operationSubjectLabel.getText().length() - 1) + value);
		else {
			try {
				calc.operationButtonClicked();
				setResult();
				operationSubjectLabel.setText(operationSubjectLabel.getText() + value);

			} catch (ArithmeticException exception) {
				operationSubjectLabel.setText("");
			}
		}

	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku operacji jednoargumentowej: silni, kwadratu lub pierwiastka kw.
	 * Dopisuje nazwê funkcji symbolizuj¹c¹ operacjê do inputLabel.
	 * @param event - obiekt zawieraj¹cy informacjê, który przycisk zosta³ wciœniêty
	 */
	@FXML
	public void clickOneArgumentOperation(ActionEvent event) {

		String value = getButtonValue(event);
		calc.operationButtonClicked();
		inputLabel.setText(calc.functionName(value) + "(" + inputLabel.getText() + ")");
	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku operacji: =.
	 * Oblicza wynik operacji z operationSubjectLabel i przepisuje go do inputLabel.
	 */
	@FXML
	public void clickEquals() {
		calc.operationButtonClicked();
		setResult();
		operationSubjectLabel.setText("");
	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku operacji: C.
	 * Usuwa tekst z obu pól operationSubjectLabel i inputLabel.
	 */
	@FXML
	public void clickC() {
		inputLabel.setText("0");
		operationSubjectLabel.setText("");
		calc.operationButtonClicked();
	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku operacji: CE.
	 * Usuwa ostatni znak z pola inputLabel.
	 */
	@FXML
	public void clickCE() {
		if (!calc.isOperationBefore())
			inputLabel.setText(inputLabel.getText().substring(0, inputLabel.getText().length() - 1));
		if (inputLabel.getText().length() == 0) {
			inputLabel.setText("0");
			calc.operationButtonClicked();
		}

	}

	/**
	 * Metoda wywo³ywana po wciœniêciu przycisku operacji: +/-
	 * Ustawia znak - przed liczb¹ w polu inputLabel.
	 */
	@FXML
	public void clickSign() {
		calc.operationButtonClicked();
		if (!calc.isNegative(inputLabel.getText()))
			inputLabel.setText("(-" + inputLabel.getText() + ")");
		else
			inputLabel.setText("(-(" + inputLabel.getText() + "))");

	}

}

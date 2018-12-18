package calculator.controller;

import calculator.model.JShellCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Klasa bedaca kontrolerem dla widoku zapisanego w pliku CalculatorData.fxml
 * 
 * @author Marcin Hanas 293454
 */
public class CalculatorController {

	@FXML
	private Label inputLabel;
	@FXML
	private Label operationSubjectLabel;

	private JShellCalculator calc = new JShellCalculator();

	/**
	 * Metoda sluzaca do pozyskiwania tekstu nacisnietego przycisku
	 * 
	 * @param event - zmienna zawierajaca informacje o nacisnietym przycisku
	 * @return tekst, ktory wyswietla sie na nacisnietym przycisku
	 */
	private String getButtonValue(ActionEvent event) {
		Button clickedButton = (Button) event.getTarget();
		String value = clickedButton.getText();
		return value;
	}

	/**
	 * Metoda, ktora oblicza rozwiazanie wyrazenia arytm. zawarte w
	 * operationSubjectLabel, i ustawia jego wynik w inputLabel, badz gdy nie udalo
	 * sie obliczyc rozwiazania, wywoluje komunikat o bledzie
	 * 
	 * @throws ArithmeticException - metoda zwraca wyjatek, gdy zlapala wyjatek
	 *                             ArithmeticException rzucony przez metode
	 *                             calculate klasy JShellCalculator - nie udalo sie
	 *                             obliczyc rozwiazania
	 */
	private void setResult() throws ArithmeticException {

		operationSubjectLabel.setText(operationSubjectLabel.getText() + inputLabel.getText());
		try {
			String value = calc.calculate(operationSubjectLabel.getText());
			inputLabel.setText(value);

		} catch (ArithmeticException exception) {
			inputLabel.setText("ERROR");
			showAlert("Wykonujesz dzialania niedozwolone");
			clickC();
			throw exception;
		}
	}

	/**
	 * Metoda wyswietla komunikat o bledzie
	 * 
	 * @param contentText - tekst wyswietlany w komunikacie, tresc bledu
	 */
	private void showAlert(String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Blad dzialania kalkulatora");
		alert.setHeaderText(null);
		alert.setContentText(contentText);

		alert.showAndWait();

	}

	/**
	 * Metoda wywolywana po wcisnieciu przycisku cyfry. Dopisuje cyfre do
	 * inputLabel.
	 * 
	 * @param event - obiekt zawierajacy informacje, ktory przycisk zostal wcisniety
	 */
	@FXML
	public void clickDigit(ActionEvent event) {
		String value = getButtonValue(event);

		if (calc.isOperationBefore()) {
			inputLabel.setText(value);
			if (!value.equals("0"))
				calc.firstDigitButtonClicked();
		} else
			inputLabel.setText(inputLabel.getText() + value);

	}

	/**
	 * Metoda wywolywana po wcisnieciu przycisku kropki. Dopisuje kropke (badz "0.")
	 * do inputLabel.
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
	 * Metoda wywolywana po wcisnieciu przycisku operacji: +, -, * lub /. oblicza
	 * wynik operacji z operationSubjectLabel oraz dopisuje znak operacji do
	 * operationSubjectLabel.
	 * 
	 * @param event - obiekt zawierajacy informacje, ktory przycisk zostal wcisniety
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
	 * Metoda wywolywana po wcisnieciu przycisku operacji jednoargumentowej: silni,
	 * kwadratu lub pierwiastka kw. Dopisuje nazwe funkcji symbolizujaca operacje do
	 * inputLabel.
	 * 
	 * @param event - obiekt zawierajacy informacje, ktory przycisk zostal wcisniety
	 */
	@FXML
	public void clickOneArgumentOperation(ActionEvent event) {

		String value = getButtonValue(event);
		calc.operationButtonClicked();
		inputLabel.setText(calc.functionName(value) + "(" + inputLabel.getText() + ")");
	}

	/**
	 * Metoda wywolywana po wcisnieciu przycisku operacji: =. Oblicza wynik operacji
	 * z operationSubjectLabel i przepisuje go do inputLabel.
	 */
	@FXML
	public void clickEquals() {
		calc.operationButtonClicked();
		setResult();
		operationSubjectLabel.setText("");
	}

	/**
	 * Metoda wywolywana po wcisnieciu przycisku operacji: C. Usuwa tekst z obu pol
	 * operationSubjectLabel i inputLabel.
	 */
	@FXML
	public void clickC() {
		inputLabel.setText("0");
		operationSubjectLabel.setText("");
		calc.operationButtonClicked();
	}

	/**
	 * Metoda wywolywana po wcisnieciu przycisku operacji: CE. Usuwa ostatni znak z
	 * pola inputLabel.
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
	 * Metoda wywolywana po wcisnieciu przycisku operacji: +/- Ustawia znak - przed
	 * liczba w polu inputLabel.
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

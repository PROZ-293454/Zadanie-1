package application.controller;

import application.model.JShellCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Klasa b�d�ca kontrolerem dla widoku zapisanego w pliku CalculatorData.fxml
 * @author Marcin Hanas 293454
 */
public class CalculatorController {

	@FXML
	private Label inputLabel;
	@FXML
	private Label operationSubjectLabel;

	private JShellCalculator calc = new JShellCalculator();

	/**
	 * Metoda s�u��ca do pozyskiwania tekstu naci�ni�tego przycisku
	 * @param event - zmienna zawieraj�ca informacje o naci�ni�tym przycisku
	 * @return tekst, kt�ry wy�wietla si� na naci�ni�tym przycisku
	 */
	private String getButtonValue(ActionEvent event) {
		Button clickedButton = (Button) event.getTarget();
		String value = clickedButton.getText();
		return value;
	}

	/**
	 * Metoda, kt�ra oblicza rozwi�zanie wyra�enia arytm. zawarte w operationSubjectLabel,
	 * i ustawia jego wynik w inputLabel, b�dz gdy nie uda�o si� obliczy� rozwi�zania, 
	 * wywo�uje komunikat o b��dzie
	 * @throws ArithmeticException - metoda zwraca wyj�tek, gdy z�apa�a wyj�tek ArithmeticException
	 * rzucony przez metod� calculate klasy JShellCalculator - nie uda�o si� obliczy� rozwi�zania
	 */
	private void setResult() throws ArithmeticException {

		operationSubjectLabel.setText(operationSubjectLabel.getText() + inputLabel.getText());
		try {
			String value = calc.calculate(operationSubjectLabel.getText());
			inputLabel.setText(value);
			
		} catch (ArithmeticException exception) {
			inputLabel.setText("ERROR");
			showAlert("Wykonujesz dzia�ania niedozwolone");
			clickC();
			throw exception;
		}
	}

	/**
	 * Metoda wy�wietla komunikat o b��dzie
	 * @param contentText - tekst wy�wietlany w komunikacie, tre�� b��du
	 */
	private void showAlert(String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B��d dzia�ania kalkulatora");
		alert.setHeaderText(null);
		alert.setContentText(contentText);

		alert.showAndWait();

	}

	/**
	 * Metoda wywo�ywana po wci�ni�ciu przycisku cyfry.
	 * Dopisuje cyfr� do inputLabel.
	 * @param event - obiekt zawieraj�cy informacj�, kt�ry przycisk zosta� wci�ni�ty
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
	 * Metoda wywo�ywana po wci�ni�ciu przycisku kropki.
	 * Dopisuje kropk� (b�dz "0.") do inputLabel.
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
	 * Metoda wywo�ywana po wci�ni�ciu przycisku operacji: +, -, * lub /.
	 * oblicza wynik operacji z operationSubjectLabel oraz dopisuje znak operacji do operationSubjectLabel.
	 * @param event - obiekt zawieraj�cy informacj�, kt�ry przycisk zosta� wci�ni�ty
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
	 * Metoda wywo�ywana po wci�ni�ciu przycisku operacji jednoargumentowej: silni, kwadratu lub pierwiastka kw.
	 * Dopisuje nazw� funkcji symbolizuj�c� operacj� do inputLabel.
	 * @param event - obiekt zawieraj�cy informacj�, kt�ry przycisk zosta� wci�ni�ty
	 */
	@FXML
	public void clickOneArgumentOperation(ActionEvent event) {

		String value = getButtonValue(event);
		calc.operationButtonClicked();
		inputLabel.setText(calc.functionName(value) + "(" + inputLabel.getText() + ")");
	}

	/**
	 * Metoda wywo�ywana po wci�ni�ciu przycisku operacji: =.
	 * Oblicza wynik operacji z operationSubjectLabel i przepisuje go do inputLabel.
	 */
	@FXML
	public void clickEquals() {
		calc.operationButtonClicked();
		setResult();
		operationSubjectLabel.setText("");
	}

	/**
	 * Metoda wywo�ywana po wci�ni�ciu przycisku operacji: C.
	 * Usuwa tekst z obu p�l operationSubjectLabel i inputLabel.
	 */
	@FXML
	public void clickC() {
		inputLabel.setText("0");
		operationSubjectLabel.setText("");
		calc.operationButtonClicked();
	}

	/**
	 * Metoda wywo�ywana po wci�ni�ciu przycisku operacji: CE.
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
	 * Metoda wywo�ywana po wci�ni�ciu przycisku operacji: +/-
	 * Ustawia znak - przed liczb� w polu inputLabel.
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

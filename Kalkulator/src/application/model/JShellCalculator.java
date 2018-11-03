package application.model;

import java.io.EOFException;
import java.util.EmptyStackException;
import java.util.List;

import jdk.jshell.*;

/**
 * Metoda b�d�ca implementacj� modelu kalkulatora.
 * Oblicza ona wynik operacji arytmetycznych w postaci Stringa,
 * oraz ustawia warto�ci logiczne p�l dotBefore i operationBefore,
 * potrzebnych do odpowiedniego dzia�ania kalkulatora.
 * @author Marcin Hanas 293454
 */
public class JShellCalculator {

	private final JShell jshell = JShell.create();
	private boolean dotBefore = false;
	private boolean operationBefore = true;
	
	/**
	 * Metoda sprawdzaj�ca czy podczas podawania cyfr liczby, zosta� ju� u�yty znak ".".
	 * @return warto�� zmiennej dotBefore.
	 */
	public boolean isDotBefore() {
		return dotBefore;
	}

	/**
	 * Metoda sprawdzaj�ca czy poprzednio wci�ni�ty przycisk by� operacj�.
	 * @return warto�� zmiennej operationBefore.
	 */
	public boolean isOperationBefore() {
		return operationBefore;
	}
	
	/**
	 * Metoda wywo�ywana, gdy wci�ni�to przycisk operacji.
	 * Ustawia warto�ci zmiennych dotBefore i operationBefore.
	 */
	public void operationButtonClicked() {
		dotBefore = false;
		operationBefore = true;
	}
	
	/**
	 * Metoda wywo�ywana, gdy wci�ni�to przycisk ".".
	 * Ustawia warto�ci zmiennych dotBefore i operationBefore.
	 */
	public void dotButtonClicked() {
		dotBefore = true;
		operationBefore = false;
	}
	
	/**
	 * Metoda wywo�ywana, gdy rozpocz�to podawanie nowej liczby
	 * Ustawia warto�ci zmiennych operationBefore.
	 */
	public void firstDigitButtonClicked() {
		operationBefore = false;
	}
	
	/**
	 * Metoda s�u��ca do otrzymania nazwy funkcji dla danego tekstu na przycisku operacji.
	 * @param buttonValue - tekst na przycisku operacji.
	 * @return nazwa funkcji odpowiadaj�ca danemu buttonValue.
	 */
	public String functionName(String buttonValue) {
		switch (buttonValue) {
		case "!":
			return "fact";
		case "x\u00b2":
			return "sqr";
		case "\u221a":
			return "sqrt";
		case "\u00b1":
			return "-";
		}
		return null;
	}

	/**
	 * Konstruktor domy�lny klasy.
	 * Wprowadza do obiektu jshell funkcje odpowiednich operacji.
	 */
	public JShellCalculator() {
		try {
			jshell.eval("private double fact(double x) {if(x%1 != 0.0) return Double.NaN; else if(x==0) return 1; else return x*fact(x-1); }");
			jshell.eval("private double sqr(double x) { return x*x; }");
			jshell.eval("private double sqrt(double x) { return Math.sqrt(x); }");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda sprawdza, czy pierwszym znakiem Stringa jest "-"
	 * @param input - sprawdzany �a�cuch znak�w
	 * @return true, je�li pierwszym znakiem jest "-", false w przeciwnym wypadku.
	 */
	public boolean isNegative(String input) {
		return input.substring(0, 1).equals("-");
	}

	/**
	 * Metoda oblicza wynik wyra�enia arytmetycznego podanego na wej�ciu.
	 * @param input - �a�cuch znak�w zawieraj�cy wyra�enie arytmetyczne
	 * @return wynik wyra�enia arytmetycznego (a w przypadku b��du - String null),
	 * @throws ArithmeticException je�li wynik wyra�enie arytmetycznego nie jest liczb�
	 * (nie uda�o si� go obliczy�, jest niesko�czono�ci� ("Infinity") b�dz nie jest liczb� ("NaN").
	 */
	public String calculate(String input) throws ArithmeticException {

		List<SnippetEvent> events = jshell.eval(input);
		for (SnippetEvent e : events) {
			if (e.causeSnippet() == null) {
				if (e.value() == null || e.value().equals("Infinity") || e.value().equals("NaN"))
					throw new ArithmeticException();
				else
					return e.value();
			}
		}

		return null;
	}
}

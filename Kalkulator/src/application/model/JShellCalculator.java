package application.model;

import java.util.List;

import jdk.jshell.*;

/**
 * Metoda bedaca implementacja modelu kalkulatora. Oblicza ona wynik operacji
 * arytmetycznych w postaci Stringa, oraz ustawia wartosci logiczne pol
 * dotBefore i operationBefore, potrzebnych do odpowiedniego dzialania
 * kalkulatora.
 * 
 * @author Marcin Hanas 293454
 */
public class JShellCalculator {

	private final JShell jshell = JShell.create();
	private boolean dotBefore = false;
	private boolean operationBefore = true;

	/**
	 * Metoda sprawdzajaca czy podczas podawania cyfr liczby, zostal juz uzyty znak
	 * ".".
	 * 
	 * @return wartosc zmiennej dotBefore.
	 */
	public boolean isDotBefore() {
		return dotBefore;
	}

	/**
	 * Metoda sprawdzajaca czy poprzednio wcisniety przycisk byl operacja.
	 * 
	 * @return wartosc zmiennej operationBefore.
	 */
	public boolean isOperationBefore() {
		return operationBefore;
	}

	/**
	 * Metoda wywolywana, gdy wcisnieto przycisk operacji. Ustawia wartosci
	 * zmiennych dotBefore i operationBefore.
	 */
	public void operationButtonClicked() {
		dotBefore = false;
		operationBefore = true;
	}

	/**
	 * Metoda wywolywana, gdy wcisnieto przycisk ".". Ustawia wartosci zmiennych
	 * dotBefore i operationBefore.
	 */
	public void dotButtonClicked() {
		dotBefore = true;
		operationBefore = false;
	}

	/**
	 * Metoda wywolywana, gdy rozpoczeto podawanie nowej liczby Ustawia wartosci
	 * zmiennych operationBefore.
	 */
	public void firstDigitButtonClicked() {
		operationBefore = false;
	}

	/**
	 * Metoda sluzaca do otrzymania nazwy funkcji dla danego tekstu na przycisku
	 * operacji.
	 * 
	 * @param buttonValue - tekst na przycisku operacji.
	 * @return nazwa funkcji odpowiadajaca danemu buttonValue.
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
	 * Konstruktor domyslny klasy. Wprowadza do obiektu jshell funkcje odpowiednich
	 * operacji.
	 */
	public JShellCalculator() {
		try {
			jshell.eval(
					"private double fact(double x) {if(x%1 != 0.0) return Double.NaN; else if(x==0) return 1; else return x*fact(x-1); }");
			jshell.eval("private double sqr(double x) { return x*x; }");
			jshell.eval("private double sqrt(double x) { return Math.sqrt(x); }");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda sprawdza, czy pierwszym znakiem Stringa jest "-"
	 * 
	 * @param input - sprawdzany lancuch znakow
	 * @return true, jesli pierwszym znakiem jest "-", false w przeciwnym wypadku.
	 */
	public boolean isNegative(String input) {
		return input.substring(0, 1).equals("-");
	}

	/**
	 * Metoda oblicza wynik wyrazenia arytmetycznego podanego na wejsciu.
	 * 
	 * @param input - lancuch znakow zawierajacy wyrazenie arytmetyczne
	 * @return wynik wyrazenia arytmetycznego (a w przypadku bledu - String null),
	 * @throws ArithmeticException jesli wynik wyrazenie arytmetycznego nie jest
	 *                             liczba (nie udalo sie go obliczyc, jest
	 *                             nieskonczonoscia ("Infinity") badz nie jest
	 *                             liczba ("NaN").
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

package application.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasa rozszerzajaca klase Application, reprezentujaca graficzny interfejs
 * programu.
 * 
 * @author Marcin Hanas 293454
 */
public class MainWindow extends Application {

	/**
	 * Metoda tworz¹ca glowne okno programu.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/CalculatorData.fxml"));
			Scene scene = new Scene(root, 280, 540);
			setStage(primaryStage, 280, 540, "Kalkulator", scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda startujaca dzialanie programu, uruchamiana przy starcie.
	 * 
	 * @param args - argumenty wiersza polecen.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Metoda ustawiaj¹ca parametry okna programu.
	 * 
	 * @param stage     - zmienna reprezentujaca okno programu
	 * @param minWidth  - minimalna szerokosc okna
	 * @param minHeight - minimalna wysokosc okna
	 * @param title     - tytul okna
	 * @param scene     - zawartosc wewnatrz okna programu.
	 */
	private void setStage(Stage stage, int minWidth, int minHeight, String title, Scene scene) {
		stage.setMinWidth(280);
		stage.setMinHeight(540);
		stage.setTitle("Kalkulator");
		stage.setScene(scene);
	}
}

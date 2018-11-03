package application.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasa rozszerzaj�ca klas� Application, reprezentuj�ca graficzny interfejs programu.
 * @author Marcin Hanas 293454
 */
public class MainWindow extends Application {

	/**
	 * Metoda tworz�ca g��wne okno programu.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("CalculatorData.fxml"));
			Scene scene = new Scene(root, 280, 540);
			setStage(primaryStage, 280, 540, "Kalkulator", scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda startuj�ca dzia�anie programu, uruchamiana przy starcie.
	 * @param args - argumenty wiersza polece�.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Metoda ustawiaj�ca parametry okna programu.
	 * @param stage - zmienna reprezentuj�ca okno programu
	 * @param minWidth - minimalna szeroko�� okna
	 * @param minHeight - minimalna wysoko�� okna
	 * @param title - tytu� okna
	 * @param scene - zawarto�� wewn�trz okna programu.
	 */
	private void setStage(Stage stage, int minWidth, int minHeight, String title, Scene scene) {
		stage.setMinWidth(280);
		stage.setMinHeight(540);
		stage.setTitle("Kalkulator");
		stage.setScene(scene);
	}
}

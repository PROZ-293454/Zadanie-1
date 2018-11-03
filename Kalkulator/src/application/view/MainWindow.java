package application.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasa rozszerzaj¹ca klasê Application, reprezentuj¹ca graficzny interfejs programu.
 * @author Marcin Hanas 293454
 */
public class MainWindow extends Application {

	/**
	 * Metoda tworz¹ca g³ówne okno programu.
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
	 * Metoda startuj¹ca dzia³anie programu, uruchamiana przy starcie.
	 * @param args - argumenty wiersza poleceñ.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Metoda ustawiaj¹ca parametry okna programu.
	 * @param stage - zmienna reprezentuj¹ca okno programu
	 * @param minWidth - minimalna szerokoœæ okna
	 * @param minHeight - minimalna wysokoœæ okna
	 * @param title - tytu³ okna
	 * @param scene - zawartoœæ wewn¹trz okna programu.
	 */
	private void setStage(Stage stage, int minWidth, int minHeight, String title, Scene scene) {
		stage.setMinWidth(280);
		stage.setMinHeight(540);
		stage.setTitle("Kalkulator");
		stage.setScene(scene);
	}
}

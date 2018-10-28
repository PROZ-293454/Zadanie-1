package application.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {

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

	public static void main(String[] args) {
		launch(args);
	}

	private void setStage(Stage stage, int minWidth, int minHeight, String title, Scene scene) {
		stage.setMinWidth(280);
		stage.setMinHeight(540);
		stage.setTitle("Kalkulator");
		stage.setScene(scene);
	}
}

package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane gridPane = new GridPane();
			Scene scene = new Scene(gridPane);
			Label inputLabel = new Label("");
			Label operationSubjectLabel = new Label("");
			inputLabel.setStyle("-fx-font: 36 arial;");
			operationSubjectLabel.setStyle("-fx-font: 20 arial;");
			Keyboard keyboard = new Keyboard(inputLabel, operationSubjectLabel);

			gridPane.setAlignment(Pos.CENTER);
			gridPane.add(operationSubjectLabel, 0, 0, 11, 1);
			gridPane.add(inputLabel, 0, 1, 11, 1);
			gridPane.addRow(2, keyboard.getButtons()[11], keyboard.getButtons()[12], keyboard.getButtons()[13], keyboard.getButtons()[14]);
			gridPane.addRow(3, keyboard.getButtons()[7], keyboard.getButtons()[8], keyboard.getButtons()[9], keyboard.getButtons()[15]);
			gridPane.addRow(4, keyboard.getButtons()[4], keyboard.getButtons()[5], keyboard.getButtons()[6], keyboard.getButtons()[16]);
			gridPane.addRow(5, keyboard.getButtons()[1], keyboard.getButtons()[2], keyboard.getButtons()[3], keyboard.getButtons()[17]);
			gridPane.addRow(6, keyboard.getButtons()[10], keyboard.getButtons()[0], keyboard.getButtons()[18]);
		
			//gridPane.add(keyboard.getEqualsButton(), 0, 2);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
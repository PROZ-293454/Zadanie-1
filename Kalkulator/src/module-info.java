
/**
 * @author Marcin Hanas
 *
 */
module calculator {
	
	requires transitive javafx.controls;
	requires transitive javafx.base;
	requires jdk.jshell;
	requires javafx.graphics;
	requires javafx.fxml;
	exports calculator.view;
	exports calculator.controller to application.view;
	exports calculator.model to application.controller;
	opens calculator.controller to javafx.fxml;
}


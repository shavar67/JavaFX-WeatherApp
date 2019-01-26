package com.shavar.weather.sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("weather.fxml"));

	loader.setControllerFactory(t -> new Controller(new MockWeatherProvider()));
	Parent root = loader.load();

	primaryStage.setTitle("Weather");
	primaryStage.setResizable(false);
	primaryStage.setScene(new Scene(root));
	primaryStage.initStyle(StageStyle.UTILITY);

	primaryStage.show();
	
	primaryStage.setOnCloseRequest(t -> {
	    Platform.exit();
	});
    }

    public static void main(String[] args) {

	launch(args);
    }
}

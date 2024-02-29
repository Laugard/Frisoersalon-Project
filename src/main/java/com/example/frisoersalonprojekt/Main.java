package com.example.frisoersalonprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {


    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(Main.class.getResource("Startside.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("StyleSheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Monikas Frisørsalon");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);

    }

    public static void main(String[] args) {
        launch();
    }
}
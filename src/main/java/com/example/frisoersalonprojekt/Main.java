package com.example.frisoersalonprojekt;

import com.example.frisoersalonprojekt.Utils.UseCase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    private static Stage stg;
    private UseCase useCase;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Startside.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Monikas Frisørsalon");

        // Set the width and height of the stage
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);

        primaryStage.setScene(scene);
        primaryStage.show();

        useCase = new UseCase(); // Antager at UseCase kan instantieres uden fejl
        opdaterTidsbestillinger();
        rydOpIDatabase();
    }

    private void opdaterTidsbestillinger() throws SQLException {
        useCase.opdaterTidsbestillingerTilAfholdt();
    }

    private void rydOpIDatabase() throws SQLException {
        useCase.sletGamleTidsbestillinger();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

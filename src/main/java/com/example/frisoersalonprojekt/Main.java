package com.example.frisoersalonprojekt;

import com.example.frisoersalonprojekt.Utils.DbSql;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Startside.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Monikas Frisørsalon");

        // Set the width and height of the stage
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialiser DbSql og opdater tidsbestillinger til 'Afholdt'
        opdaterTidsbestillinger();
        rydOpIDatabase();
    }

    private void opdaterTidsbestillinger() {
        try {
            // Antag at du har en constructor i DbSql der ikke kræver argumenter
            DbSql dbSql = new DbSql();
            dbSql.opdaterTidsbestillingerTilAfholdt();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fejl ved opdatering af tidsbestillinger: " + e.getMessage());
        }
    }
    private void rydOpIDatabase() {
        try {
            DbSql dbSql = new DbSql();
            dbSql.opdaterTidsbestillingerTilAfholdt();
            dbSql.sletGamleTidsbestillinger(); // Sletter gamle tidsbestillinger
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fejl ved rydning af database: " + e.getMessage());
        }
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

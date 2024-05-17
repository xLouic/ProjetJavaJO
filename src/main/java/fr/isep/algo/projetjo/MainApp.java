package fr.isep.algo.projetjo;

import fr.isep.algo.projetjo.controller.AthleteManagement;
import fr.isep.algo.projetjo.controller.EventManagement;
import fr.isep.algo.projetjo.controller.ResultManagement;
import fr.isep.algo.projetjo.controller.SportManagement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestion des Jeux Olympiques");

        TabPane tabPane = new TabPane();

        Tab athletesTab = new Tab("Gestion des Athlètes", new AthleteManagement().getLayout());
        Tab sportsTab = new Tab("Gestion des Disciplines Sportives", new SportManagement().getLayout());
        Tab eventsTab = new Tab("Gestion des Événements", new EventManagement().getLayout());
        Tab resultsTab = new Tab("Gestion des Résultats", new ResultManagement().getLayout());

        athletesTab.setClosable(false);
        sportsTab.setClosable(false);
        eventsTab.setClosable(false);
        resultsTab.setClosable(false);

        tabPane.getTabs().addAll(athletesTab, sportsTab, eventsTab, resultsTab);

        BorderPane root = new BorderPane();
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

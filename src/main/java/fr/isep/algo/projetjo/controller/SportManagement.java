package fr.isep.algo.projetjo.controller;

import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SportManagement {
    private VBox layout = new VBox(10);

    public SportManagement() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextField sportNameField = new TextField();

        gridPane.add(new Label("Nom de la discipline:"), 0, 0);
        gridPane.add(sportNameField, 1, 0);

        Button addButton = new Button("Ajouter Discipline");
        addButton.setOnAction(event -> {
            // Logique pour ajouter une discipline à la base de données
        });
        gridPane.add(addButton, 1, 1);

        layout.getChildren().add(gridPane);
    }

    public VBox getLayout() {
        return layout;
    }
}

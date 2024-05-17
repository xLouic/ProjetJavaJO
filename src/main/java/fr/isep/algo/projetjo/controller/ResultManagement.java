package fr.isep.algo.projetjo.controller;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ResultManagement {
    private VBox layout = new VBox(10);

    public ResultManagement() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        ComboBox<String> eventComboBox = new ComboBox<>();
        eventComboBox.getItems().addAll("100m hommes", "200m femmes");  // Ajoutez les événements ici
        TextField resultField = new TextField();

        gridPane.add(new Label("Événement:"), 0, 0);
        gridPane.add(eventComboBox, 1, 0);
        gridPane.add(new Label("Résultat:"), 0, 1);
        gridPane.add(resultField, 1, 1);

        Button saveButton = new Button("Enregistrer Résultat");
        saveButton.setOnAction(event -> {
            // Logique pour enregistrer les résultats dans la base de données
        });
        gridPane.add(saveButton, 1, 2);

        layout.getChildren().add(gridPane);
    }

    public VBox getLayout() {
        return layout;
    }
}

package fr.isep.algo.projetjo.controller;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EventManagement {
    private VBox layout = new VBox(10);

    public EventManagement() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextField eventNameField = new TextField();
        DatePicker eventDate = new DatePicker();
        ComboBox<String> sportComboBox = new ComboBox<>();
        sportComboBox.getItems().addAll("Natation", "Course", "Cyclisme");  // Ajoutez les sports ici

        gridPane.add(new Label("Nom de l'événement:"), 0, 0);
        gridPane.add(eventNameField, 1, 0);
        gridPane.add(new Label("Date de l'événement:"), 0, 1);
        gridPane.add(eventDate, 1, 1);
        gridPane.add(new Label("Discipline:"), 0, 2);
        gridPane.add(sportComboBox, 1, 2);

        Button addButton = new Button("Ajouter Événement");
        addButton.setOnAction(event -> {
            // Logique pour ajouter un événement à la base de données
        });
        gridPane.add(addButton, 1, 3);

        layout.getChildren().add(gridPane);
    }

    public VBox getLayout() {
        return layout;
    }
}

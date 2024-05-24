package fr.isep.algo.projetjo.controller;

import fr.isep.algo.projetjo.model.Athlete;
import fr.isep.algo.projetjo.model.SportEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SportManagement {
    private VBox layout = new VBox(10);
    private ObservableList<SportEvent> events = FXCollections.observableArrayList();
    private ObservableList<Athlete> athletes = FXCollections.observableArrayList();

    public SportManagement() {
        // Populate athletes list for example


        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextField categoryNameField = new TextField();
        TextField disciplineNameField = new TextField();
        ComboBox<Athlete> athleteComboBox = new ComboBox<>(athletes);

        gridPane.add(new Label("Discipline:"), 0, 0);
        gridPane.add(categoryNameField, 1, 0);
        gridPane.add(new Label("Epreuve:"), 0, 1);
        gridPane.add(disciplineNameField, 1, 1);
        gridPane.add(new Label("Sélectionner Athlète:"), 0, 2);
        gridPane.add(athleteComboBox, 1, 2);

        Button addButton = new Button("Ajouter Événement");
        addButton.setOnAction(event -> {
            SportEvent newEvent = new SportEvent(categoryNameField.getText(), disciplineNameField.getText(), athleteComboBox.getSelectionModel().getSelectedItem().getName());
            events.add(newEvent);
            categoryNameField.clear();
            disciplineNameField.clear();
            athleteComboBox.getSelectionModel().clearSelection();
        });
        gridPane.add(addButton, 1, 3);

        layout.getChildren().addAll(gridPane, setupSearchField(), createEventTable());
    }

    private TableView<SportEvent> createEventTable() {
        TableView<SportEvent> table = new TableView<>();
        table.setEditable(true);

        // Set up table columns as before, including editing features

        return table;
    }

    private Node setupSearchField() {
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher...");
        // Setup filtering as before

        return searchField;
    }

    public VBox getLayout() {
        return layout;
    }
}

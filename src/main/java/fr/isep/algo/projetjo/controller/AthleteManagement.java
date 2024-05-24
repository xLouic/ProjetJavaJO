package fr.isep.algo.projetjo.controller;


import fr.isep.algo.projetjo.model.Athlete;  // Import the Athlete class from its package
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AthleteManagement {
    private VBox layout = new VBox(10);

    public AthleteManagement() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Set up input fields
        TextField idInput = new TextField();
        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        TextField ageInput = new TextField();
        TextField countryInput = new TextField();
        Button addButton = new Button("Ajouter Athlète");

        // Set up grid pane
        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(idInput, 1, 0);
        gridPane.add(new Label("Prénom:"), 0, 1);
        gridPane.add(firstNameInput, 1, 1);
        gridPane.add(new Label("Nom:"), 0, 2);
        gridPane.add(lastNameInput, 1, 2);
        gridPane.add(new Label("Âge:"), 0, 3);
        gridPane.add(ageInput, 1, 3);
        gridPane.add(new Label("Pays:"), 0, 4);
        gridPane.add(countryInput, 1, 4);
        gridPane.add(addButton, 1, 5);

        // Set up the table
        TableView<Athlete> table = new TableView<>();
        ObservableList<Athlete> masterData = FXCollections.observableArrayList();

        // Configure table columns
        setupTableColumns(table);

        // Add components to layout
        layout.getChildren().addAll(gridPane, setupSearchField(table, masterData), table);

        addButton.setOnAction(event -> {
            int id = Integer.parseInt(idInput.getText());
            int age = Integer.parseInt(ageInput.getText());
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            String country = countryInput.getText();
            Athlete newAthlete = new Athlete(id, firstName, lastName, age, country);
            masterData.add(newAthlete);
            clearFields(idInput, firstNameInput, lastNameInput, ageInput, countryInput);
        });
    }

    private void setupTableColumns(TableView<Athlete> table) {
        TableColumn<Athlete, Number> idColumn = new TableColumn<>("Numéro");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TableColumn<Athlete, String> firstNameColumn = new TableColumn<>("Prénom");
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<Athlete, String> lastNameColumn = new TableColumn<>("Nom");
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<Athlete, Number> ageColumn = new TableColumn<>("Âge");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        TableColumn<Athlete, String> countryColumn = new TableColumn<>("Pays");
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        table.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, ageColumn, countryColumn);
    }

    private TextField setupSearchField(TableView<Athlete> table, ObservableList<Athlete> masterData) {
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher par numéro, nom, prénom, âge, pays...");
        FilteredList<Athlete> filteredData = new FilteredList<>(masterData, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(athlete -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return athlete.getFirstName().toLowerCase().contains(lowerCaseFilter)
                        || athlete.getLastName().toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(athlete.getId()).contains(lowerCaseFilter)
                        || athlete.getCountry().toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(athlete.getAge()).contains(lowerCaseFilter);
            });
        });
        SortedList<Athlete> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        return searchField;
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public VBox getLayout() {
        return layout;
    }
}

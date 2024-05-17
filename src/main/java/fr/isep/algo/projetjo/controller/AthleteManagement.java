package fr.isep.algo.projetjo.controller;

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

        // Champs pour ajouter un nouvel athlète
        TextField idInput = new TextField();
        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        TextField ageInput = new TextField();
        TextField countryInput = new TextField();
        Button addButton = new Button("Ajouter Athlète");

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

        // Tableau pour afficher les athlètes
        TableView<Athlete> table = new TableView<>();
        ObservableList<Athlete> masterData = FXCollections.observableArrayList();

        // Colonnes pour le tableau
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
        table.setItems(masterData);

        // Barre de recherche
        TextField searchField = new TextField();
        searchField.setPromptText("Rechercher par numéro, nom, prénom, âge, pays...");

        // Ajouter un menu contextuel sur les lignes du tableau
        table.setRowFactory(tv -> {
            TableRow<Athlete> row = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();

            MenuItem editItem = new MenuItem("Modifier");
            editItem.setOnAction(event -> {
                // Logique pour la modification d'un athlète
                Athlete selectedAthlete = row.getItem();
                if (selectedAthlete != null) {
                    firstNameInput.setText(selectedAthlete.getFirstName());
                    lastNameInput.setText(selectedAthlete.getLastName());
                    idInput.setText(Integer.toString(selectedAthlete.getId()));
                    ageInput.setText(Integer.toString(selectedAthlete.getAge()));
                    countryInput.setText(selectedAthlete.getCountry());
                }
            });

            MenuItem deleteItem = new MenuItem("Supprimer");
            deleteItem.setOnAction(event -> {
                // Logique pour supprimer un athlète
                masterData.remove(row.getItem());
            });

            contextMenu.getItems().addAll(editItem, deleteItem);

            // Seulement afficher le context menu pour les lignes non-nulles.
            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });

        // Filtrer les données
        FilteredList<Athlete> filteredData = new FilteredList<>(masterData, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(athlete -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (athlete.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (athlete.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(athlete.getId()).contains(lowerCaseFilter)) {
                    return true;
                } else if (athlete.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return String.valueOf(athlete.getAge()).contains(lowerCaseFilter);
            });
        });

        SortedList<Athlete> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

        addButton.setOnAction(event -> {
            int id = Integer.parseInt(idInput.getText());
            int age = Integer.parseInt(ageInput.getText());
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            String country = countryInput.getText();
            Athlete newAthlete = new Athlete(id, firstName, lastName, age, country);
            masterData.add(newAthlete);
            idInput.clear();
            firstNameInput.clear();
            lastNameInput.clear();
            ageInput.clear();
            countryInput.clear();
        });

        layout.getChildren().addAll(gridPane, searchField, table);
    }

    public VBox getLayout() {
        return layout;
    }
}

class Athlete {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleStringProperty country = new SimpleStringProperty();

    public Athlete(int id, String firstName, String lastName, int age, String country) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.age.set(age);
        this.country.set(country);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public int getId() {
        return id.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public int getAge() {
        return age.get();
    }

    public String getCountry() {
        return country.get();
    }
}

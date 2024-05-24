package fr.isep.algo.projetjo.model;  // Adjust the package name based on your project structure

// Adjust the package name based on your project structure

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Athlete {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleStringProperty country = new SimpleStringProperty();
    private Object name;

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

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }
}

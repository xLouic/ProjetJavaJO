package fr.isep.algo.projetjo.model;

import javafx.beans.property.SimpleStringProperty;

public class SportEvent {
    private SimpleStringProperty category = new SimpleStringProperty();
    private SimpleStringProperty discipline = new SimpleStringProperty();

    public SportEvent(String category, String discipline, Object name) {
        this.category.set(category);
        this.discipline.set(discipline);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public String getDiscipline() {
        return discipline.get();
    }

    public void setDiscipline(String discipline) {
        this.discipline.set(discipline);
    }

    public SimpleStringProperty disciplineProperty() {
        return discipline;
    }
}

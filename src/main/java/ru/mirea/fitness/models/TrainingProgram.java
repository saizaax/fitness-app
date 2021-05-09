package ru.mirea.fitness.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String title, description;
    private int hours, trainings;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getTrainings() {
        return trainings;
    }

    public void setTrainings(int trainings) {
        this.trainings = trainings;
    }
}

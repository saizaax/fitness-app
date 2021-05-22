package ru.mirea.fitness.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mirea.fitness.models.TrainingProgram;
import ru.mirea.fitness.models.User;

public interface TrainingProgramRepository extends CrudRepository<TrainingProgram, Long> {

    @Query("SELECT u FROM TrainingProgram u WHERE u.Id = :Id")
    public TrainingProgram getTrainingProgramById(@Param("Id") Long Id);

    @Query("SELECT u FROM TrainingProgram u WHERE u.title = :title")
    public TrainingProgram getTrainingProgramByTitle(@Param("title") String title);
}

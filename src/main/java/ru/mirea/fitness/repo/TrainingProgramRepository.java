package ru.mirea.fitness.repo;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.fitness.models.TrainingProgram;

public interface TrainingProgramRepository extends CrudRepository<TrainingProgram, Long> {

}

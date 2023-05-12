package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {
}
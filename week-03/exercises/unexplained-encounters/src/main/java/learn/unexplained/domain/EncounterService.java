package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepository;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;
import java.util.Objects;

public class EncounterService {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<Encounter> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        return repository.findByType(type);
    }

    public EncounterResult add(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);
        if (!result.isSuccess()) {
            return result;
        }

        List<Encounter> encounters = repository.findAll();
        for (Encounter e : encounters) {
            if (Objects.equals(encounter.getWhen(), e.getWhen())
                    && Objects.equals(encounter.getType(), e.getType())
                    && Objects.equals(encounter.getDescription(), e.getDescription())) {
                result.addErrorMessage("duplicate encounter is not allowed");
                return result;
            }
        }

        encounter = repository.add(encounter);
        result.setPayload(encounter);
        return result;
    }

    public EncounterResult update(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);

        if (!result.isSuccess()) {
            return result;
        }
        Encounter existing = repository.findById(encounter.getEncounterId());
        if (existing == null) {
            result.addErrorMessage("Encounter Id " + encounter.getEncounterId() + " not found");
            return result;
        }

        if (!existing.getType().equals(encounter.getType())) {
            result.addErrorMessage("Cannot update type.");
            return result;
        }

        boolean success = repository.update(encounter);
        if (!success) {
            result.addErrorMessage("Could not find Encounter Id " + encounter.getEncounterId());
        }

        return result;
    }

    public EncounterResult deleteById(int encounterId) throws DataAccessException {
        EncounterResult result = new EncounterResult();
        if (!repository.deleteById(encounterId)) {
            result.addErrorMessage("Encounter id " + encounterId + " was not found.");
        }
        return result;

    }

    private EncounterResult validate(Encounter encounter) {

        EncounterResult result = new EncounterResult();
        if (encounter == null) {
            result.addErrorMessage("encounter cannot be null");
            return result;
        }

        if (encounter.getWhen() == null || encounter.getWhen().trim().length() == 0) {
            result.addErrorMessage("when is required");
        }

        if (encounter.getDescription() == null || encounter.getDescription().trim().length() == 0) {
            result.addErrorMessage("description is required");
        }

        if (encounter.getOccurrences() <= 0) {
            result.addErrorMessage("occurrences must be greater than 0");
        }

        return result;
    }
}

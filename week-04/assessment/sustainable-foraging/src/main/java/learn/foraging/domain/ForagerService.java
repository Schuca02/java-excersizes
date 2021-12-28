package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);

        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(repository.add(forager));
        return result;
    }

    private Result<Forager> validate(Forager forager) {

        Result<Forager> result = validateNull(forager);

        if (!result.isSuccess()) {
            return result;
        }

        validateState(forager, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateDuplicate(forager, result);

        return result;
    }

    private void validateDuplicate(Forager forager, Result<Forager> result) {
        repository.findAll().forEach(f -> {
            if (f.equals(forager)) {
                result.addErrorMessage("Forager is a duplicate");
            }
        });
    }


    private Result<Forager> validateNull(Forager forager) {

        Result<Forager> result = new Result<>();

//is blank = add that
        if (forager.getFirstName() == null) {
            result.addErrorMessage("Must Enter A First Name");
        }

        if (forager.getLastName() == null) {
            result.addErrorMessage("Must Enter A Last Name");
        }

        if (forager.getState() == null) {
            result.addErrorMessage("Must Enter A State");
        }
        return result;
    }

    private void validateState(Forager forager, Result<Forager> result) {
      if(Arrays.stream(UnitedStates.values()).noneMatch(unitedStates -> unitedStates.name().equals(forager.getState()))){
          result.addErrorMessage("Enter A Valid State.");
      }
    }
}

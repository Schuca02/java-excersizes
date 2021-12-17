package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepositoryDouble;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterServiceTest {

    EncounterService service = new EncounterService(new EncounterRepositoryDouble());

    @Test
    void shouldFindByType() throws DataAccessException {
        List<Encounter> encounters = service.findByType(EncounterType.CREATURE);
        assertNotNull(encounters);
        assertEquals(1, encounters.size());
    }

    @Test
    void shouldNotAddNull() throws DataAccessException {
        EncounterResult expected = makeResult("encounter cannot be null");
        EncounterResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyWhen() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, " ", "test desc", 1);
        EncounterResult expected = makeResult("when is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddEmptyDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "  ", 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDescription() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", null, 1);
        EncounterResult expected = makeResult("description is required");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddZeroOccurrences() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 0);
        EncounterResult expected = makeResult("occurrences must be greater than 0");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicate() throws DataAccessException {
        Encounter encounter = new Encounter(2, EncounterType.CREATURE, "2021-12-23", "Scary bigfoot", 2);
        EncounterResult expected = makeResult("duplicate encounter is not allowed");
        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter(0, EncounterType.CREATURE, "2/2/2019", "test description", 1);
        EncounterResult expected = new EncounterResult();
        expected.setPayload(encounter);

        EncounterResult actual = service.add(encounter);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        EncounterResult result = service.update(new Encounter(4, EncounterType.VISION, "2020-02-05", "Little Pirate Ghost", 1));
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateType() throws DataAccessException {
        EncounterResult result = service.update(new Encounter(4, EncounterType.SOUND, "2020-02-05", "Little Pirate Ghost", 1));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateNotValid() throws DataAccessException {
        EncounterResult result = service.update(new Encounter(20, EncounterType.SOUND, "2020-02-05", "Little Pirate Ghost", 1));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        EncounterResult result = service.deleteById(14);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteById() throws DataAccessException{
        EncounterResult result = service.deleteById(3);
        assertFalse(result.isSuccess());
    }

    private EncounterResult makeResult(String message) {
        EncounterResult result = new EncounterResult();
        result.addErrorMessage(message);
        return result;
    }
}
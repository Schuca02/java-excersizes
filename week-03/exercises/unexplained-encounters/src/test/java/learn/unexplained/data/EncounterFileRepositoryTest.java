package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

//    static final String SEED_PATH = "./data/encounters-seed.csv";
    static final String TEST_PATH = "./data/encounters-test.csv";
    final Encounter[] testEncounters = new Encounter[]{
            new Encounter(1, EncounterType.UFO, "2020-01-01", "short test #1", 1),
            new Encounter(2, EncounterType.CREATURE, "2020-02-01", "short test #2", 1),
            new Encounter(3, EncounterType.SOUND, "2020-03-01", "short test #3", 1)
    };

    EncounterRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws DataAccessException {
        for (Encounter e : repository.findAll()) {
            repository.deleteById(e.getEncounterId());
        }

        for (Encounter e : testEncounters) {
            repository.add(e);
        }
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> encounters = repository.findAll();
        Encounter[] actual = encounters.toArray(new Encounter[encounters.size()]);
        assertArrayEquals(testEncounters, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void shouldUpdateExisting() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(3);
        encounter.setType(EncounterType.SOUND);
        encounter.setWhen("2021-05-05");
        encounter.setDescription("Little Boy");
        encounter.setOccurrences(2);

        boolean success = repository.update(encounter);

        assertTrue(success);

        Encounter actual = repository.findById(3);
        assertNotNull(actual);
        assertEquals("Little Boy", actual.getDescription());
        assertEquals("2021-05-05", actual.getWhen());
    }

    @Test
    void shouldNotUpdateExisting() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setEncounterId(1000);

        boolean actual = repository.update(encounter);
        assertFalse(actual);

    }

    @Test
    void shouldDelete() throws DataAccessException {
        boolean actual = repository.deleteById(2);
        assertTrue(actual);

        Encounter e = repository.findById(2);
        assertNull(e);
    }

    @Test
    void shouldNotDelete() throws DataAccessException {
        boolean actual = repository.deleteById(112304);
        assertFalse(actual);

    }

}
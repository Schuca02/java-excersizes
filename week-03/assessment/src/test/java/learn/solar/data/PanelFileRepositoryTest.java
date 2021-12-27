package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PanelFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/solar-panel-seed.csv";
    static final String TEST_FILE_PATH = "./data/solar-panel-test.csv";

    PanelFileRepository repository = new PanelFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }


    @Test
    void findBySection() throws DataAccessException {
        List<Panel> actual = repository.findBySection("Main");
        assertEquals(2, actual.size());
        actual = repository.findBySection("Upper Hill");
        assertEquals(1, actual.size());
    }

    @Test
    void findByKey() throws DataAccessException {
        Panel panel = repository.findByKey("Main", 3, 4);
        assertNotNull(panel);
        assertEquals(2009, panel.getYearInstalled());

        panel = repository.findByKey("High Hills", 69, 65);
        assertNull(panel);
    }

    @Test
    void add() throws DataAccessException {
        Panel panel = new Panel();
        panel.setSection("Lower Hill");
        panel.setRow(1);
        panel.setColumn(1);
        panel.setMaterial(Material.CIGS);
        panel.setYearInstalled(2009);
        panel.setTracking(true);

        Panel actual = repository.add(panel);
        assertNotNull(actual);
        assertEquals("Lower Hill", actual.getSection());
    }

    @Test
    void shouldUpdateExisting() throws DataAccessException {
        Panel panel = new Panel();
        panel.setSection("Upper Hill");
        panel.setRow(1);
        panel.setColumn(1);
        panel.setMaterial(Material.CIGS);
        panel.setYearInstalled(2019);
        panel.setTracking(false);

        boolean success = repository.update(panel);
        assertTrue(success);

        Panel actual = repository.findByKey("Upper Hill", 1, 1);
        assertNotNull(actual);
        assertEquals(1, actual.getColumn());
        assertEquals(2019, actual.getYearInstalled());
        assertFalse(actual.isTracking());

    }

    @Test
    void shouldNotUpdateEmpty() throws DataAccessException {
        Panel panel = new Panel();
        panel.setSection("Test Plains");

        boolean actual = repository.update(panel);
        assertFalse(actual);

    }

    @Test
    void deleteByKey() throws DataAccessException {
        boolean actual = repository.deleteByKey("Main", 3, 5);
        assertTrue(actual);

        Panel p = repository.findByKey("Main", 3, 5);
        assertNull(p);
    }


    @Test
    void shouldNotDeleteExisting() throws DataAccessException{
        boolean actual = repository.deleteByKey("Test Plains", 1,  2);
        assertFalse(actual);

    }
}



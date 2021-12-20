package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepositoryDouble;
import learn.solar.models.Material;
import learn.solar.models.Panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {

    PanelService service;

    @BeforeEach
    void setup() {
        PanelRepositoryDouble repository = new PanelRepositoryDouble();
        service = new PanelService(repository);
    }

    @Test
    void shouldFindBySection() throws DataAccessException {
        List<Panel> panels = service.findBySection("Main");
        assertNotNull(panels);
        assertEquals(2, panels.size());
    }

    @Test
    void shouldFindByKey() throws DataAccessException {
        Panel panel = service.findByKey("Main", 1, 1);
        assertNotNull(panel);
    }

    @Test
    void shouldNotFindByKeyOB() throws DataAccessException {
        Panel panel = service.findByKey("Main", -1, 12);
        assertNull(panel);
    }

    @Test
    void shouldAddPanel() throws DataAccessException {
        Panel panel = new Panel();
        panel.setSection("Lower Hill");
        panel.setRow(3);
        panel.setColumn(3);
        panel.setMaterial(Material.MONO_SI);
        panel.setYearInstalled(2020);
        panel.setTracking(true);

        PanelResult result = service.add(panel);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddNullPanel() throws DataAccessException {
        PanelResult result = service.add(null);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldNotAddDuplicate() throws DataAccessException {
        Panel panel = new Panel("Main", 1, 2, Material.POLY_SI, 2019, true);
        PanelResult result = service.add(panel);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelWithFutureDate() throws DataAccessException {
        PanelResult result = service.add(
                new Panel("Upper Hill", 25, 25, Material.CDTE, 2023, true));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddPanelOutOfRange() throws DataAccessException {
        PanelResult result = service.add(
                new Panel("Main", 256, 23, Material.MONO_SI, 2020, true));
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddEmptyContent() throws DataAccessException {
        Panel panel = new Panel();
        panel.setSection("  ");

        PanelResult result = service.add(panel);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());

    }

    @Test
    void shouldUpdate() throws DataAccessException {
        boolean result = service.update(
                new Panel("Main", 1, 2, Material.MONO_SI, 2012, true));
        assertTrue(result);
    }

    @Test
    void shouldNotUpdate() throws DataAccessException{
        boolean result = service.update(
                new Panel("Main", 2, 2, Material.MONO_SI, 2012, true));
        assertFalse(result);
    }


    @Test
    void shouldNotUpdateEmptySection() throws DataAccessException {
        boolean result = service.update(
                new Panel(" ", 0, 23435, Material.MONO_SI, 2020, true));
        assertFalse(result);

    }

    @Test
    void shouldDeleteByKey() throws DataAccessException {
        PanelResult result = service.deleteByKey("Main", 1, 2);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteEmpty() throws DataAccessException {
        PanelResult result = service.deleteByKey("test", 234, 450932);
        assertFalse(result.isSuccess());
    }
}
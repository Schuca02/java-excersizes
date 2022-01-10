package learn.house.domain;

import learn.house.data.HostRepositoryDouble;
import learn.house.models.Host;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostServiceTest {

    HostService service = new HostService(new HostRepositoryDouble());

    @Test
    void findByState() {
        List<Host> hosts = service.findByState("ID");

        assertEquals(2, hosts.size());
    }

    @Test
    void findByEmail() {
        Host host = service.findByEmail("Thomasnumber1@gmail.com");

        assertNotNull(host);
    }

    @Test
    void findByCity() {
        List<Host> hosts = service.findByCity("Boise");

        assertEquals(1, hosts.size());
    }

    @Test
    void findById() {
        Host host = service.findById("cf6cd63a-028a-4620-9786-60e1d0ce23b7");

        assertNotNull(host);
        assertTrue(host.getLastName().equalsIgnoreCase("Vaisey"));
    }
}
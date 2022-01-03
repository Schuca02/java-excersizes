package learn.house.data;

import learn.house.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


    class HostFileRepositoryTest {

        static final String SEED_PATH = "./data/host-seed.txt";
        static final String TEST_PATH = "./data/host-test.txt";


        HostFileRepository repository = new HostFileRepository(TEST_PATH);

        @BeforeEach
        void setup() throws IOException {
            Path seedPath = Paths.get(SEED_PATH);
            Path testPath = Paths.get(TEST_PATH);
            Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
        }

        @Test
        void findAll() {
            List<Host> hosts=repository.findAll();
            assertEquals(8, hosts.size());
        }

        @Test
        void findByState() {
            List<Host> hosts = repository.findByState("DC");
            assertEquals(1, hosts.size());
        }


        @Test
        void findByEmail() {
            Host host = repository.findByEmail("krhodes1@posterous.com");
            assertEquals(new BigDecimal("368.75"), host.getWeekendRate());
            assertTrue(host.getLastName().equalsIgnoreCase("Rhodes"));
        }

        @Test
        void findsWrongByEmail() {
            Host host = repository.findByEmail("ogecks1@dagondesign.com");
            assertNull(host);
        }
    }
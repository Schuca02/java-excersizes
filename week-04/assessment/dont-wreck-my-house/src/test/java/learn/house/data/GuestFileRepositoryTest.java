package learn.house.data;


import learn.house.models.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {

    static final String SEED_PATH = "./data/guest-seed.txt";
    static final String TEST_PATH = "./data/guest-test.txt";
    static final int NEXT_ID = 4;

    GuestFileRepository repository = new GuestFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findAll() {
        assertTrue(repository.findAll().size() == NEXT_ID - 1);
    }

    @Test
    void findByState() {
        List<Guest> guests = repository.findByState("DC");
        assertEquals(1, guests.size());
    }

    @Test
    void findById() {
        Guest guest = repository.findById(3);
        assertTrue(guest.getFirstName().equalsIgnoreCase("Tremain"));
        assertTrue(guest.getPhoneNumber().equalsIgnoreCase("(313) 2245034"));
    }

    @Test
    void findByEmail() {
        Guest guest = repository.findByEmail("slomas0@mediafire.com");
        assertEquals(1, guest.getId());
        assertTrue(guest.getLastName().equalsIgnoreCase("Lomas"));
    }

    @Test
    void findsWrongByEmail() {
        Guest guest = repository.findByEmail("ogecks1@dagondesign.com");
        assertFalse(guest.getLastName().equalsIgnoreCase("Lomas"));
    }
}
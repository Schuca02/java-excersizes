package learn.house.domain;

import learn.house.data.GuestRepositoryDouble;
import learn.house.models.Guest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceTest {

    GuestService service = new GuestService(new GuestRepositoryDouble());


    @Test
    void findByEmail() {
        Guest guest = service.findByEmail("jeffisawesome@gmail.com");
        assertNotNull(guest);
    }

    @Test
    void findById() {
        Guest guest = service.findById(54);
        assertTrue(guest.getFirstName().equalsIgnoreCase("jeffery"));

    }
}
package learn.house.data;

import learn.house.models.Host;
import learn.house.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {


    static final String SEED_PATH = "./data/reservation-seed.csv";
    static final String TEST_PATH = "./data/house_data_test/cf6cd63a-028a-4620-9786-60e1d0ce23b7.csv";
    static final String TEST_DIR_PATH = "./data/house_data_test/";


    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);
    Host host;

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
        host = new Host("cf6cd63a-028a-4620-9786-60e1d0ce23b7","Vaisey","cvaiseyn@ucsd.edu","(208) 9563557","71 Forest Dale Street","Boise","ID",83732, new BigDecimal (144), new BigDecimal(180));
    }

    @Test
    void shouldFindAllByHost() {
        List<Reservation> reservations = repository.findAllByHost(host);
        assertEquals(20, reservations.size());
    }

    @Test
    void shouldAdd() throws DataException {

        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.of(2022,2,4));
        reservation.setEndDate(LocalDate.of(2022,2,9));
        reservation.setGuestId(61);
        reservation.setTotal(new BigDecimal(1116));

        repository.add(reservation, host);

        assertEquals(21, repository.findAllByHost(host).size());
    }

    @Test
    void shouldUpdate() throws DataException {

        Reservation reservation = new Reservation();
        reservation.setId(17);
        reservation.setStartDate(LocalDate.of(2021, 8,23));
        reservation.setEndDate(LocalDate.of(2021,8,25));
        reservation.setGuestId(697);
        reservation.setTotal(new BigDecimal(288));

        boolean success = repository.update(reservation, host);
        assertTrue(success);

    }

    @Test
    void shouldDeleteById() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(14);

        boolean actual = repository.deleteById(reservation, host);
        assertTrue(actual);

    }

    @Test
    void shouldNotDeleteBadId() throws DataException{
        Reservation reservation = new Reservation();
        reservation.setId(22);
        boolean actual = repository.deleteById(reservation, host);
        assertFalse(actual);
    }
}

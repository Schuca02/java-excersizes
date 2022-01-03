package learn.house.domain;

import learn.house.data.DataException;
import learn.house.data.ReservationRepositoryDouble;
import learn.house.models.Host;
import learn.house.models.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(
            new ReservationRepositoryDouble());

    Host host = new Host("cf6cd63a-028a-4620-9786-60e1d0ce23b7", "Vaisey", "cvaiseyn@ucsd.edu", "(208) 9563557", "71 Forest Dale Street", "Boise", "ID", 83732, new BigDecimal(144), new BigDecimal(180));


    @Test
    void add() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2022, 3, 1));
        reservation.setEndDate(LocalDate.of(2022, 3, 2));
        reservation.setGuestId(126);
        reservation.setTotal(new BigDecimal(144));

        Result<Reservation> result = service.add(reservation, host);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldNotAddPastDates() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2021, 11, 13));
        reservation.setEndDate(LocalDate.of(2021, 11, 14));
        reservation.setGuestId(123);
        reservation.setTotal(new BigDecimal(23));

        Result<Reservation> result = service.add(reservation, host);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }


    @Test
    void shouldNotAddOverlap() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2022, 2, 5));
        reservation.setEndDate(LocalDate.of(2022, 2, 10));
        reservation.setGuestId(123);
        reservation.setTotal(new BigDecimal(100));

        Result<Reservation> result = service.add(reservation, host);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }


    @Test
    void shouldNotAddDuplicate() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setStartDate(LocalDate.of(2022, 2, 4));
        reservation.setEndDate(LocalDate.of(2022, 2, 9));
        reservation.setGuestId(61);
        reservation.setTotal(new BigDecimal(1116));

        Result<Reservation> result = service.add(reservation, host);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
        assertEquals(1, result.getErrorMessages().size());

    }

    @Test
    void shouldNotAddEmpty() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setStartDate(null);
        reservation.setEndDate(LocalDate.of(2022, 3, 4));
        reservation.setGuestId(621);
        reservation.setTotal(null);

        Result<Reservation> result = service.add(reservation, host);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldUpdateOneDay() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setId(1);
        reservation.setStartDate(LocalDate.of(2022, 2, 4));
        reservation.setEndDate(LocalDate.of(2022, 2, 10));
        reservation.setGuestId(61);
        reservation.setTotal(new BigDecimal(1116));

        var result = service.update(reservation, host);

        assertTrue(result.isSuccess());
        assertEquals(LocalDate.of(2022, 2, 10), reservation.getEndDate());

    }

    @Test
    void shouldNotUpdateOverlap() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setId(1);
        reservation.setStartDate(LocalDate.of(2022, 2, 4));
        reservation.setEndDate(LocalDate.of(2022, 2, 12));
        reservation.setGuestId(61);
        reservation.setTotal(new BigDecimal(1116));

        var result = service.update(reservation, host);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldDelete() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setId(1);

        boolean result = service.delete(reservation, host);
        assertTrue(result);
    }

    @Test
    void shouldNotDelete() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setId(3);

        boolean result = service.delete(reservation, host);
        assertFalse(result);

    }

    @Test
    void shouldFindAllByHost() throws DataException{
        List<Reservation> reservations = service.findAllByHost(host);

        assertEquals(2, reservations.size());
    }

    @Test
    void shouldCalculateTotal(){
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setId(1);
        reservation.setStartDate(LocalDate.of(2022, 2, 3));
        reservation.setEndDate(LocalDate.of(2022, 2, 5));
        reservation.setGuestId(61);
        reservation.setTotal(service.calculateTotal(reservation, host));

        assertEquals(new BigDecimal(324), reservation.getTotal());
    }
}

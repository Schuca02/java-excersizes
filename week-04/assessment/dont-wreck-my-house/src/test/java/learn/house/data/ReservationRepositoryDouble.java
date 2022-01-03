package learn.house.data;

import learn.house.models.Host;
import learn.house.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryDouble implements ReservationRepository {

    private final ArrayList<Reservation> reservations = new ArrayList<>();



    public ReservationRepositoryDouble() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setStartDate(LocalDate.of(2022, 2, 4));
        reservation.setEndDate(LocalDate.of(2022, 2, 9));
        reservation.setGuestId(61);
        reservation.setTotal(new BigDecimal(1116));
        reservations.add(reservation);
        reservations.add(new Reservation(LocalDate.of(2022,2,11)
                , LocalDate.of(2022,2,15)
                ,123,new BigDecimal(1234)));
    }

    @Override
    public List<Reservation> findAllByHost(Host host) {
        return reservations;
    }

    @Override
    public Reservation add(Reservation reservation, Host host) throws DataException {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation, Host host) throws DataException {
        return true;
    }

    @Override
    public boolean deleteById(Reservation reservation, Host host) throws DataException {
        return true;
    }
}

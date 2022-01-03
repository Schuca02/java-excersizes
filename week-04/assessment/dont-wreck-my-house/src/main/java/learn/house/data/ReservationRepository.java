package learn.house.data;

import learn.house.models.Host;
import learn.house.models.Reservation;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findAllByHost(Host host);

    Reservation add(Reservation reservation, Host host) throws DataException;

    boolean update(Reservation reservation, Host host) throws DataException;

    boolean deleteById(Reservation reservation, Host host) throws DataException;

}
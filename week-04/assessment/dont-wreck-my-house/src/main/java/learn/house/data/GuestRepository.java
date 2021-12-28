package learn.house.data;

import learn.house.models.Guest;

import java.util.List;

public interface GuestRepository {
    List<Guest> findAll();
    List<Guest> findByState(String stateAbbr);
    Guest findById(int id);
    Guest findByEmail(String email);
}

package learn.house.data;

import learn.house.domain.GuestService;
import learn.house.models.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestRepositoryDouble implements GuestRepository {

    private final ArrayList<Guest> guests = new ArrayList<>();

    public GuestRepositoryDouble(){
        Guest guest = new Guest(54,"Jeffery","Jeffofferson","jeffisawesome@gmail.com","(712) 7322323","IA");
        guests.add(guest);
    }

    @Override
    public List<Guest> findAll() {
        return guests;
    }

    @Override
    public List<Guest> findByState(String stateAbbr) {
        return null;
    }

    @Override
    public Guest findById(int id) {
        return guests.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Guest findByEmail(String email) {
        return findAll().stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}

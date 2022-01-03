package learn.house.domain;

import learn.house.data.GuestRepository;
import learn.house.models.Guest;

import java.util.List;
import java.util.stream.Collectors;

public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public Guest findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Guest findById(int id){
        return repository.findById(id);
    }

}

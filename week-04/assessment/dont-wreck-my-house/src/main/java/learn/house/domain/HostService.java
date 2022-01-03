package learn.house.domain;

import learn.house.data.HostRepository;
import learn.house.models.Host;

import java.util.List;
import java.util.stream.Collectors;

public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public List<Host> findByState(String stateAbbr){return repository.findByState(stateAbbr);}

    public Host findByEmail(String email){
        return repository.findByEmail(email);
    }

    public List<Host> findByCity(String city){
        return repository.findByCity(city);
    }

    public Host findById(String id){
        return repository.findById(id);
    }

}

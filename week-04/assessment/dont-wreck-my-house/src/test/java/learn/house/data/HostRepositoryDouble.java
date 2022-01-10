package learn.house.data;

import learn.house.models.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HostRepositoryDouble implements HostRepository {

    private final ArrayList<Host> hosts = new ArrayList<>();

    public HostRepositoryDouble(){
        Host host = new Host("cf6cd63a-028a-4620-9786-60e1d0ce23b7"
                , "Vaisey"
                , "cvaiseyn@ucsd.edu"
                , "(208) 9563557"
                , "71 Forest Dale Street"
                , "Boise"
                , "ID"
                , 83732
                , new BigDecimal(144)
                , new BigDecimal(180));

        hosts.add(host);
        hosts.add(new Host("asodinvv"
                , "Thomas"
                , "Thomasnumber1@gmail.com"
                , "(205) 2345432"
                , "123 Lane ave"
                , "IdahoTown"
                , "ID"
                , 12345
                , new BigDecimal(123)
                , new BigDecimal(456)));
    }




    @Override
    public List<Host> findAll() {
        return hosts;
    }

    @Override
    public Host findByEmail(String email) {
        return hosts.stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }

    @Override
    public List<Host> findByState(String stateAbbr) {
        return hosts.stream()
                .filter(i -> i.getState().equalsIgnoreCase(stateAbbr))
                .collect(Collectors.toList());
    }

    @Override
    public List<Host> findByCity(String city) {
        return findAll().stream()
                .filter(i -> i.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public Host findById(String id) {
        return hosts.stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }
}

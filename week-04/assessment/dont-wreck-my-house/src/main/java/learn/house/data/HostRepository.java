package learn.house.data;

import learn.house.models.Host;

import java.util.List;

public interface HostRepository {

    List<Host> findAll();

    Host findByEmail(String email);

    List<Host> findByState(String stateAbbr);

}

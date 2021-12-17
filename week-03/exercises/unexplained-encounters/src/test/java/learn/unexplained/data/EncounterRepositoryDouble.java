package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepositoryDouble implements EncounterRepository {

    private ArrayList<Encounter> encounters = new ArrayList<>();

    public EncounterRepositoryDouble() {

        encounters.add(new Encounter(1, EncounterType.UFO, "2020-01-01", "Vision", 1));
        encounters.add(new Encounter(2, EncounterType.CREATURE, "2021-12-23", "Scary bigfoot", 2));
        encounters.add(new Encounter(3, EncounterType.SOUND, "2020-05-05", "Little Boy", 1));
        encounters.add(new Encounter(4, EncounterType.VISION, "2001-07-06", "Pirate Ghost", 1));

    }


    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return new ArrayList<>(encounters);
    }

    @Override
    public Encounter findById(int EncounterId) throws DataAccessException {
        for(Encounter e : encounters){
            if (e.getEncounterId() == EncounterId){
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        ArrayList<Encounter> result = new ArrayList<>();
        for (Encounter e : encounters){
            if (type == e.getType()){
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        encounters.add(encounter);
        return encounter;
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return findById(encounter.getEncounterId()) != null;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return encounterId == 14;
    }
}

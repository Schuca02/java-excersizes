package learn.pets.data;

import com.mysql.cj.jdbc.MysqlDataSource;
import learn.pets.models.Pet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface PetRepository {
    List<Pet> findAll();

    Pet findById(int petId);

    Pet add(Pet pet);

    boolean update(Pet pet);

    boolean deleteById(int petId);
}
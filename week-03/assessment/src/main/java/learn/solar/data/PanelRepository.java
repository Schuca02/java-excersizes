package learn.solar.data;

import learn.solar.models.Panel;

import java.util.List;

public interface PanelRepository {

    List<Panel> findBySection (String section) throws DataAccessException;

    Panel findByKey(String section, int row, int column) throws DataAccessException;

    Panel add(Panel panel) throws DataAccessException;

    boolean update(Panel panel) throws DataAccessException;

    boolean deleteByKey(String section, int row, int column)  throws  DataAccessException;

    List<Panel> findAll() throws DataAccessException;
}

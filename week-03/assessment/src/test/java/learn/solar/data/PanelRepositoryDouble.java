package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;


public class PanelRepositoryDouble implements PanelRepository {

    private ArrayList<Panel> panels = new ArrayList<>();

    public PanelRepositoryDouble() {
        Panel panel = new Panel();
        panel.setSection("Main");
        panel.setRow(1);
        panel.setColumn(1);
        panel.setMaterial(Material.CIGS);
        panels.add(panel);
        panels.add(new Panel("Upper Hill", 2, 3, Material.A_SI, 2009, false));
        panels.add(new Panel("Main", 1, 2, Material.POLY_SI, 2019, true));

    }


    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        for (Panel p : panels){
            if (p.getSection().equals(section)){
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public Panel findByKey(String section, int row, int column) throws DataAccessException {
        for (Panel p : panels) {
            if (p.getSection().equals(section)
                    && p.getRow() == row
                    && p.getColumn() == column) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        return true;
    }

    @Override
    public boolean deleteByKey(String section, int row, int column) throws DataAccessException {
        return true;
    }

    @Override
    public List<Panel> findAll() throws DataAccessException {
        return null;
    }
}

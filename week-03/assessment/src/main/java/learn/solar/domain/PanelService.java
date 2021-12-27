package learn.solar.domain;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.models.Panel;

import java.util.List;


public class PanelService {

    private final PanelRepository repository;

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }


    public PanelResult add(Panel panel) throws DataAccessException {
        PanelResult result = validate(panel);

        if (!result.isSuccess()) {
            return result;
        }

        Panel existing = repository.findByKey(panel.getSection(), panel.getRow(), panel.getColumn());
        if (!(existing == null)) {
            result.addErrorMessage("Duplicate panel exists.");
            return result;
        }

        panel = repository.add(panel);
        result.setPanel(panel);

        return result;

    }

    public PanelResult update(Panel panel) throws DataAccessException {
        PanelResult result = validate(panel);
        if (!(findByKey(panel.getSection(), panel.getRow(), panel.getColumn()) == null)) {
            repository.update(panel);
            return result;
        }else {
            result.addErrorMessage("Panel Doesn't Exist");
        }
        return result;
    }


    public PanelResult deleteByKey(String section, int row, int column) throws DataAccessException {
        PanelResult result = new PanelResult();
        if (section.trim().length() == 0 || row <= 0 || row > 250 || column <= 0 || column > 250) {
            result.addErrorMessage("Panel does not exist.");
            return result;
        }
        if (!repository.deleteByKey(section, row, column)) {
            result.addErrorMessage("Panel at " + section + ": " + row + ", " + column + " was not found.");
        }
        return result;
    }


    public List<Panel> findBySection(String section) throws DataAccessException {
        return repository.findBySection(section);
    }

    public Panel findByKey(String section, int row, int column) throws DataAccessException {
        return repository.findByKey(section, row, column);
    }

    public List<Panel> findAll() throws DataAccessException{
        return repository.findAll();
    }


    private PanelResult validate(Panel panel) {
        PanelResult result = new PanelResult();

        if (panel == null) {
            result.addErrorMessage("Panel cannot be null.");
            return result;
        }
        if (panel.getSection() == null || panel.getSection().trim().length() == 0) {
            result.addErrorMessage("Section is required.");
            return result;
        }
        if (panel.getRow() <= 0 || panel.getRow() > 250) {
            result.addErrorMessage("Valid row number between [0-250] is required.");
            return result;
        }
        if (panel.getColumn() <= 0 || panel.getColumn() > 250) {
            result.addErrorMessage("Valid column number between [0-250] is required.");
        }
        if (panel.getYearInstalled() > 2022) {
            result.addErrorMessage("Cannot install future panel.");
            return result;
        }

        return result;
    }

}


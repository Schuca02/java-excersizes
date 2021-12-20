package learn.solar.ui;

import learn.solar.data.DataAccessException;
import learn.solar.data.PanelRepository;
import learn.solar.domain.PanelService;
import learn.solar.data.PanelFileRepository;
import learn.solar.domain.PanelResult;
import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.util.List;

public class Controller {

    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.printHeader("Welcome to Solar-Farms");
        try {
            runMenu();
        } catch (DataAccessException ex) {
            view.printHeader("Fatal Err: " + ex);
            System.exit(1);
        }
    }

    private void runMenu() throws DataAccessException {
        MenuOptions options;
        do {
            options = view.displayMenuSelect();

            switch (options) {
                case ADD -> addPanel();
                case DISPLAY_SECTION -> displaySections();
//                case UPDATE_PANEL -> updatePanel();
                case DELETE_PANEL -> deletePanel();
                case EXIT -> view.printHeader("Goodbye");
            }
        } while (options != MenuOptions.EXIT);
    }

    private void addPanel() throws DataAccessException {
        view.printHeader(MenuOptions.ADD.getTitle());
        view.displayResult(service.add(view.makePanel()));
    }

    private void displaySections() throws DataAccessException {
        view.printHeader(MenuOptions.DISPLAY_SECTION.getTitle());
        view.displayPanelsSection(service.findBySection(view.readSection()));
    }

    private void updatePanel() throws DataAccessException {
        view.printHeader(MenuOptions.UPDATE_PANEL.getTitle());
        List<Panel> panels = service.findAll();

//        1. What panel do you want to update?
//        1.1 Does it exist? If not, print error.
        // 2. What would you like it to be updated to?
        // 3. Delete old panel, create new panel.
    }

    private void deletePanel() throws DataAccessException {
        view.printHeader(MenuOptions.DELETE_PANEL.getTitle());
        Panel key = view.deletePanel();
        view.displayResult(service.deleteByKey(key.getSection(), key.getRow(), key.getColumn()));

    }

}

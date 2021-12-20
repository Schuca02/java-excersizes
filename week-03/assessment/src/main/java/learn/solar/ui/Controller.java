package learn.solar.ui;

import learn.solar.data.DataAccessException;
import learn.solar.domain.PanelService;
import learn.solar.models.Panel;

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
                case UPDATE_PANEL -> updatePanel();
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
        Panel p = view.update(service.findBySection(view.readSection()));

        view.displayResult(service.update(p));


    }
    private void deletePanel() throws DataAccessException {
        view.printHeader(MenuOptions.DELETE_PANEL.getTitle());
        Panel key = view.deletePanel();
        view.displayResult(service.deleteByKey(key.getSection(), key.getRow(), key.getColumn()));

    }

}

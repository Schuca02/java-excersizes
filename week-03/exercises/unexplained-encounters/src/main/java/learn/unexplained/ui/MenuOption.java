package learn.unexplained.ui;

public enum MenuOption {
    EXIT("Exit"),
    DISPLAY_ALL("Display All Encounters"),
    DISPLAY_BY_TYPE ("Display by Type"),
    UPDATE_ENCOUNTER ("Update Encounter"),
    DELETE_ENCOUNTER("Delete Encounter"),
    ADD("Add An Encounter");

    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}

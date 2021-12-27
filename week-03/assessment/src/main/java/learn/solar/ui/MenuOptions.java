package learn.solar.ui;

public enum MenuOptions {
    ADD("Add Panel"),
    DISPLAY_SECTION("Display Panel Section"),
    UPDATE_PANEL("Update Existing Panel"),
    DELETE_PANEL("Delete Existing Panel"),
    EXIT("Exit Program");

    private final String title;

    MenuOptions(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

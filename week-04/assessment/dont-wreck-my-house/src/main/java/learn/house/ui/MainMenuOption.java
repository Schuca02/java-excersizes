package learn.house.ui;

public enum MainMenuOption {
    EXIT(0, "Exit"),
    VIEW_HOST_RESERVATIONS(1, "View Reservations By Host"),
    MAKE_RESERVATION(2, "Make a Reservation"),
    EDIT_RESERVATION_DATES(3, "Edit Reservation Dates"),
    CANCEL_RESERVATION(4, "Cancel a Reservation");

    private int value;
    private String message;

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }


    private MainMenuOption(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static MainMenuOption fromValue(int value) {
        for (MainMenuOption option : MainMenuOption.values()) {
            if (option.getValue() == value){
                return option;
            }
        }
        return EXIT;
    }
}

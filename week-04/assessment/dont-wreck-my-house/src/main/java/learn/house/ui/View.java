package learn.house.ui;

import learn.house.models.Guest;
import learn.house.models.Reservation;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class View {

    private final ConsoleIO io;


    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = 0;
        int max = 0;

        for (MainMenuOption option : MainMenuOption.values()) {
            io.printf("%s. %s%n", option.getValue(), option.getMessage());
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("%nSelect [%s-%s]: ", min, max);
        return MainMenuOption.fromValue(io.readInt(message, min, max));
    }

    public Reservation chooseReservation(List<Reservation> reservations) {
        int min = 0;
        int max = 0;

        for (Reservation r : reservations) {
            min = Math.min(min, r.getId());
            max = Math.max(max, r.getId());

        }
        int reservationId = io.readInt("\nSelect a reservation Id#: ", min, max);
        Reservation reservation = reservations.stream()
                .filter(i -> i.getId() == reservationId)
                .findFirst()
                .orElse(null);

        if (reservation == null) {
            displayResult(false, String.format("Reservation %s for Guest %s, does not exist.", reservationId, reservation.getGuestId()));
        }
        return reservation;
    }

    public void updateReservation(Reservation reservation) {

        reservation.setStartDate(io.readRequiredLocalDate("Start Date "
                + reservation.getStartDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ": "));

        reservation.setEndDate(io.readRequiredLocalDate("End Date "
                + reservation.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ": "));
    }

    public Reservation makeReservation(Guest guest) {
        Reservation reservation = new Reservation();
        reservation.setGuestId(guest.getId());
        reservation.setStartDate(io.readRequiredLocalDate("Reservation Start Date [MM/dd/yyyy]: "));
        reservation.setEndDate(io.readRequiredLocalDate("Reservation End Date [MM/dd/yyyy]: "));

        return reservation;
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("Error has occurred: ");
        io.println(ex.getMessage());
    }


    public String getHostByEmail() {
        return io.readRequiredString("Enter a Host Email: ");
    }

    public void displaySingleReservation(Reservation reservation) {
        io.printf("Start: %s%nEnd: %s%nTotal: $%s%n",
                reservation.getStartDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                reservation.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                reservation.getTotal());
    }

    public void displayReservations(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            if (reservations.size() == 0) {
                System.out.println("No current reservations for " + reservation.getHost());
            } else {
                io.printf("%s: %s - %s, %s, $%s%n",
                        reservation.getId(),
                        reservation.getStartDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        reservation.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        reservation.getGuestId(),
                        reservation.getTotal());
            }
        }
    }

    public void displayResult(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public boolean displayYouSure() {
        return io.readBoolean("Are you sure [y/n]: ");
    }

    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }

    public String getGuestByEmail() {
        return io.readRequiredString("Enter a Guest Email: ");
    }
}

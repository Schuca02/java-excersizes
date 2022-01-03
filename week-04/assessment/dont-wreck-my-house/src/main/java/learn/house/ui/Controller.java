package learn.house.ui;

import learn.house.data.DataException;
import learn.house.domain.GuestService;
import learn.house.domain.HostService;
import learn.house.domain.ReservationService;
import learn.house.domain.Result;
import learn.house.models.Guest;
import learn.house.models.Host;
import learn.house.models.Reservation;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private final HostService hostService;
    private final GuestService guestService;
    private final ReservationService reservationService;
    private final View view;

    public Controller(HostService hostService, GuestService guestService, ReservationService reservationService, View view) {
        this.hostService = hostService;
        this.guestService = guestService;
        this.reservationService = reservationService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome to Don't Wreck My House");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();

            switch (option) {
                case VIEW_HOST_RESERVATIONS -> viewByHost();
                case MAKE_RESERVATION -> makeReservation();
                case EDIT_RESERVATION_DATES -> editReservation();
                case CANCEL_RESERVATION -> cancelReservation();
            }
        } while (option != MainMenuOption.EXIT);
    }

    private void editReservation() throws DataException {
        view.displayHeader(MainMenuOption.EDIT_RESERVATION_DATES.getMessage());
        String email = view.getHostByEmail();
        Host host = hostService.findByEmail(email);
        if (host == null) {
            System.out.println("\nThat email doesn't match any host.\n");
            return;
        }

        String gmail = view.getGuestByEmail();
        Guest guest = guestService.findByEmail(gmail);
        if (guest == null) {
            System.out.println("\nThat email doesn't match any guests.\n");
            return;
        }

        List<Reservation> reservations = reservationService.findAllByHost(host)
                .stream()
                .filter(i -> i.getGuestId() == guest.getId())
                .filter(i -> i.getEndDate().isAfter(LocalDate.now())).collect(Collectors.toList());

        if (reservations.size() == 0) {
            System.out.printf("%nNo reservations to edit for %s: %s, %s%n", host.getLastName(), host.getCity(), host.getState());
            return;
        }
        String f = String.format("Reservations for %s: %s, %s", host.getLastName(), host.getCity(), host.getState());
        view.displayHeader(f);

        view.displayReservations(reservations);

        Reservation reservation = view.chooseReservation(reservations);
        if (reservation == null){
            return;
        }

        String i = String.format("%nEditing reservation %s", reservation.getId());
        view.displayHeader(i);
        view.updateReservation(reservation);
        reservation.setTotal(reservationService.calculateTotal(reservation, host));

        view.displayHeader("Summary");
        view.displaySingleReservation(reservation);
        reservation.setHost(host);
        boolean youSure = view.displayYouSure();

        if (youSure) {
            Result<Reservation> result = reservationService.update(reservation, host);
            String successMessage = String.format("Reservation %s updated!", reservation.getId());
            String message = result.isSuccess() ? successMessage : String.join(", ", result.getErrorMessages());
            view.displayResult(result.isSuccess(), message);
        } else {
            String failureMessage = String.format("Reservation not updated.");
            view.displayResult(false, failureMessage);
        }
    }

    private void makeReservation() throws DataException {
        view.displayHeader(MainMenuOption.MAKE_RESERVATION.getMessage());
        String email = view.getHostByEmail();
        Host host = hostService.findByEmail(email);
        if (host == null) {
            System.out.println("\nThat email doesn't match any host.\n");
            return;
        }
        String gmail = view.getGuestByEmail();
        Guest guest = guestService.findByEmail(gmail);
        if (guest == null) {
            System.out.println("\nThat email doesn't match any guests.\n");
            return;
        }
        List<Reservation> reservations = reservationService.findAllByHost(host)
                .stream().filter(i -> i.getStartDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Reservation::getEndDate)).collect(Collectors.toList());

        String f = String.format("Reservations for %s: %s, %s%n", host.getLastName(), host.getCity(), host.getState());
        view.displayHeader(f);
        view.displayReservations(reservations);
        Reservation reservation = view.makeReservation(guest);
        reservation.setTotal(reservationService.calculateTotal(reservation, host));
        view.displayHeader("Summary");
        view.displaySingleReservation(reservation);
        reservation.setHost(host);
        boolean youSure = view.displayYouSure();
        if (youSure) {
            Result<Reservation> result = reservationService.add(reservation, host);
            String successMessage = String.format("Reservation %s made!", reservation.getId());
            String message = result.isSuccess() ? successMessage : String.join(", ", result.getErrorMessages());
            view.displayResult(result.isSuccess(), message);
        } else {
            String failureMessage = String.format("No reservation made.");
            view.displayResult(false, failureMessage);

        }
    }


    private void viewByHost() {
        view.displayHeader(MainMenuOption.VIEW_HOST_RESERVATIONS.getMessage());
        String email = view.getHostByEmail();
        Host host = hostService.findByEmail(email);
        if (host == null) {
            System.out.println("\nThat email or host doesn't exist.\n");
            return;
        }
        List<Reservation> reservations = reservationService.findAllByHost(host);
        if (reservations == null || reservations.size() == 0) {
            System.out.printf("No reservations for %s: %s, %s%n", host.getLastName(), host.getCity(), host.getState());

        }
        String f = String.format("Reservations for %s: %s, %s%n", host.getLastName(), host.getCity(), host.getState());
        view.displayHeader(f);
        view.displayReservations(reservations);
        view.enterToContinue();

    }

    private void cancelReservation() throws DataException {
        view.displayHeader(MainMenuOption.CANCEL_RESERVATION.getMessage());
        String email = view.getHostByEmail();
        Host host = hostService.findByEmail(email);
        if (host == null) {
            System.out.println("\nThat email doesn't match any host.\n");
            return;
        }
        String gmail = view.getGuestByEmail();
        Guest guest = guestService.findByEmail(gmail);
        if (guest == null) {
            System.out.println("\nThat email doesn't match any guests.\n");
            return;
        }
        List<Reservation> reservations = reservationService.findAllByHost(host)
                .stream().filter(i -> i.getGuestId() == guest.getId())
                .filter(i -> i.getStartDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
        if (reservations.size() == 0) {
            System.out.printf("No reservations to cancel for %s: %s, %s%n", host.getLastName(), host.getCity(), host.getState());
            return;
        }
        String f = String.format("Reservations for %s: %s, %s", host.getLastName(), host.getCity(), host.getState());
        view.displayHeader(f);
        view.displayReservations(reservations);
        Reservation reservation = view.chooseReservation(reservations);
        String i = String.format("%nReservation %s: %s - %s, %s", reservation.getId(), reservation.getStartDate(), reservation.getEndDate(), reservation.getTotal());
        reservation.setHost(host);
        view.displayHeader(i);
        boolean youSure = view.displayYouSure();
        if (youSure && reservationService.delete(reservation, host)) {
            String successMessage = String.format("Reservation %s deleted", reservation.getId());
            view.displayResult(true, successMessage);
        } else {
            String failureMessage = String.format("Reservation %s kept.", reservation.getId());
            view.displayResult(false, failureMessage);
        }
    }
}

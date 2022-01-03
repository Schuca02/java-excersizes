package learn.house.domain;

import learn.house.data.DataException;
import learn.house.data.ReservationRepository;
import learn.house.models.Host;
import learn.house.models.Reservation;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> findAllByHost(Host host) {
        return repository.findAllByHost(host);
    }

    public BigDecimal calculateTotal(Reservation reservation, Host host) {
        BigDecimal total = BigDecimal.ZERO;

        for (LocalDate d = reservation.getStartDate(); d.isBefore(reservation.getEndDate()); d = d.plusDays(1)) {
            if (d.getDayOfWeek() == (DayOfWeek.FRIDAY) || d.getDayOfWeek() == (DayOfWeek.SATURDAY)) {
                total = total.add(host.getWeekendRate());
            } else {
               total = total.add(host.getStandardRate());
            }
        }
        return total;
    }


    public boolean delete(Reservation reservation, Host host) throws DataException {
        List<Reservation> reservations = repository.findAllByHost(reservation.getHost());
        for (Reservation r : reservations) {
            if (r.getId() == reservation.getId()) {
                return repository.deleteById(reservation, host);
            }
        }
        return false;
    }

    public Result<Reservation> update(Reservation reservation, Host host) throws DataException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        if (repository.update(reservation, host)) {
            result.setPayload(reservation);
            return result;
        }else {
            result.addErrorMessage("Could not find reservation.");
        }return result;
    }


    public Result<Reservation> add(Reservation reservation, Host host) throws DataException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(repository.add(reservation, host));

        return result;
    }

    private Result<Reservation> validate(Reservation reservation) {

        Result<Reservation> result = validateNulls(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        validateDates(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateOverlap(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateDuplicate(reservation);

        return result;
    }

    private void validateDuplicate(Reservation reservation) {
        Result<Reservation> result = new Result<>();
        repository.findAllByHost(reservation.getHost()).forEach(i -> {
            if (i.equals(reservation)) {
                result.addErrorMessage("Reservation Is A Duplicate.");
            }
        });
    }

    private Result<Reservation> validateNulls(Reservation reservation) {
        Result<Reservation> result = new Result<>();

        if (reservation == null) {
            result.addErrorMessage("Nothing to save.");
        }

        if (reservation.getStartDate() == null) {
            result.addErrorMessage("Reservation Start Date Required");
        }

        if (reservation.getEndDate() == null) {
            result.addErrorMessage("Reservation End Date Required");
        }

        if (reservation.getGuestId() == 0) {
            result.addErrorMessage("Reservation Guest Id Required");
        }

        if (reservation.getTotal() == null) {
            result.addErrorMessage("Reservation Must Have Total Amount");
        }
        return result;
    }

    private void validateDates(Reservation reservation, Result<Reservation> result) {
        List<Reservation> reservations = repository.findAllByHost(reservation.getHost());

        if (reservation.getStartDate().isBefore(LocalDate.now())) {
            result.addErrorMessage("Reservation Start Must Be In The Future.");
        }
        if (reservation.getStartDate().isAfter(reservation.getEndDate())) {
            result.addErrorMessage("Reservation Must End After It Starts");
        }
    }

    private void validateOverlap(Reservation reservation, Result<Reservation> result) {
        List<Reservation> reservations = repository.findAllByHost(reservation.getHost());
        for (Reservation r : reservations) {
            if (r.getId() == reservation.getId()) {
                continue;
            }
            if (!(reservation.getStartDate().isAfter(r.getEndDate())
                    || reservation.getEndDate().isBefore(r.getStartDate()))) {
                result.addErrorMessage("Cannot Have Overlapping Reservations.");
            }

        }
    }
}


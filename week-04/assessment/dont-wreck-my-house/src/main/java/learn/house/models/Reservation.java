package learn.house.models;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal total;
    private Host host;
    private int guestId;

    public Reservation(LocalDate startDate, LocalDate endDate, int guestId, BigDecimal total) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestId = guestId;
        this.total = total;
    }

    public Reservation(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGuestId(){return guestId;}

    public void setGuestId(int guestId){this.guestId = guestId;}

    public Host getHost() {return host;}

    public void setHost(Host host) {this.host = host;}

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

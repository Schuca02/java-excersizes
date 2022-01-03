package learn.house.domain;

import learn.house.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class Result<R> {

    private ArrayList<String> messages = new ArrayList<>();
    private Reservation payload;

    public Reservation getPayload() {
        return payload;
    }

    public void setPayload(Reservation payload) {
        this.payload = payload;
    }

    public boolean isSuccess(){
        return messages.size() == 0;
    }

    public List<String> getErrorMessages(){
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message){
        messages.add(message);
    }
}

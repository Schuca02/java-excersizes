package learn.solar.domain;

import learn.solar.models.Panel;

import java.util.List;
import java.util.ArrayList;

public class PanelResult {

    private ArrayList<String> messages = new ArrayList<>();
    private Panel panel;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }


}

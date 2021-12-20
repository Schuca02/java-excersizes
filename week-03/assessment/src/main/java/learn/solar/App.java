package learn.solar;

import learn.solar.data.PanelFileRepository;
import learn.solar.domain.PanelService;
import learn.solar.ui.Controller;
import learn.solar.ui.View;

public class App {
    public static void main(String[] args) {

        PanelFileRepository repository = new PanelFileRepository("./data/solar-panel.csv");

        PanelService service = new PanelService(repository);

        View view = new View();

        Controller controller = new Controller(service, view);

        controller.run();
    }
}

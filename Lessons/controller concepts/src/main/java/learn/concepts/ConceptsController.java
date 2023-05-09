package learn.concepts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConceptsController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello world.";
    }
}
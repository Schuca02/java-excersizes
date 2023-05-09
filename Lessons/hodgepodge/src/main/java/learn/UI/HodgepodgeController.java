package learn.UI;


import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HodgepodgeController {

    static ArrayList<String> todos = new ArrayList<>(List.of("Pet Dog", "Eat Soup", "Homework"));


private int sheepcount = 0;

    @GetMapping("/name")
    public String myName() {
        return "Casey Schultz";
    }

    @GetMapping("/current/time")
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    @GetMapping("/greet/{name}")
    public String greetThem(@PathVariable String name) {
        return "Hello there " + name + "!";
    }

    @GetMapping("/sheep")
    public int getSheepCount() {
        return sheepcount;
    }

    @PutMapping("/sheep")
    public void plusSheep(){
        sheepcount++;
    }

    @PutMapping("/sheep/{amount}")
    public void increaseSheep(@PathVariable int amount){
       for (int i = 0; i < amount; i++){
           plusSheep();
       }

    }

    @PostMapping("/sheep")
    public void somethingSheep(@RequestBody SheepValue value){
        increaseSheep(value.getAmount());
    }

    @DeleteMapping("/sheep")
    public void deleteSheep(){
        sheepcount--;
    }

    @GetMapping("/todo")
    public ArrayList<String> getList(){
        return todos;
    }

    @PutMapping("/todo")
    public void bulkUpload(@RequestBody List<String> items){
        todos.addAll(items);
    }

    @PutMapping("/todo/{item}")
    public void addTodo(@PathVariable String item){
        todos.add(item);
    }

    @DeleteMapping("/todo/{index}")
    public void deleteTodo (@PathVariable int index){
        todos.remove(index);
    }

    @PostMapping("/todo")
    public void updateTodo(@RequestBody ArrayList<String> items){
   todos = items;
    }


}

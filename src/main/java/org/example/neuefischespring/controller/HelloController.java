package org.example.neuefischespring.controller;

import org.example.neuefischespring.model.Message;
import org.example.neuefischespring.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HelloController {

    List<Message> messages = initMessages();

    private List<Message> initMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("A", "John", "Hello!"));
        messages.add(new  Message("B", "Jane", "Heyyy :)"));
        return messages;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloName(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messages;
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable String id) {
        return new Student(id, "John", "Doe", 18);
    }

    @GetMapping("/search")
    public String printQuery(@RequestParam String query) {
        return "Query: " + query;
    }

    @PostMapping("/post")
    public String printPost(@RequestBody Student value) {
        System.out.println("Hello: " + value);
        return "Hello: " + value;
    }

    @PostMapping("/messages")
    public void postMessage(@RequestBody Message value) {
        this.messages.add(new  Message(UUID.randomUUID().toString(), value.name(), value.message()));
    }

    @DeleteMapping("/messages/{id}")
    public void deleteMessage(@PathVariable String id) {
        for(int index = 0; index < this.messages.size(); index++) {
            if(this.messages.get(index).id().equals(id)) {
                this.messages.remove(index);
                break;
            }
        }
    }
}

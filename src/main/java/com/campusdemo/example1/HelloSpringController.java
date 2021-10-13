package com.campusdemo.example1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringController {

    @GetMapping("/")
    public String HelloSpring() {
        System.out.println("My First Spring Application");
        return "My First Spring Application";
    }
}

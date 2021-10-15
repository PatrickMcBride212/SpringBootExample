package com.campusdemo.example1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class HelloSpringController {

    @Autowired
    private HelloSpringService helloSpringService;

    @GetMapping("/")
    public Map<String, ArrayList<String>> HelloSpring() {
        return helloSpringService.getAll();
    }

    @GetMapping("/{username}")
    public ArrayList<String> getComments(@PathVariable String username) {
        return helloSpringService.getComments(username);
    }

}

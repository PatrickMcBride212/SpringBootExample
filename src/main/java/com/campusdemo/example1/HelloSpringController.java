package com.campusdemo.example1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public String postComment(@RequestBody Message req){

        helloSpringService.add(req.getUsername(), req.getComment());

        return "Comment added successfully";
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username){
        helloSpringService.deleteUser(username);

        return "User "+username+" successfully deleted";
    }

    @PutMapping("/")
    public String changeUsername(@RequestBody ChangeUsername usernames){

        helloSpringService.changeUsername(usernames.getOldUsername(), usernames.getNewUsername());

        return "User "+usernames.getOldUsername()+" successfully changed to "+usernames.getNewUsername();
    }


}

package com.campusdemo.example1;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HelloSpringService {

   private Map<String, ArrayList<String>> map = new HashMap<>();

   // Adding username and comments to HashMap
   public void add(String username, String comments) {
        if (map.containsKey(username)) {
            map.get(username).add(comments);
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add(comments);
            map.put(username, list);
        }
   }

   // Return all usernames and comments
   public Map<String, ArrayList<String>> getAll() {
       return map;
   }

   // Return comments for specified usernames
   public ArrayList<String> getComments(String username) {
       if (!map.containsKey(username)) {
           ArrayList<String> error = new ArrayList<>();
           error.add("Username does not exist");
           return error;
       }
       return map.get(username);
   }
}

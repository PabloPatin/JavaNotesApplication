package com.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    private List<User> getAllUsers(){
        return service.getAllUsers();
    }

//  TODO: переделать + назначение в группу
    @PostMapping
    private ResponseEntity<?> addUser(@RequestBody User user){
        System.out.println(user);
        return service.createUser(user);
    }

    @DeleteMapping(path = "{login}")
    private void deleteUser(@PathVariable String login){
        service.deleteUserByName(login);
    }
}

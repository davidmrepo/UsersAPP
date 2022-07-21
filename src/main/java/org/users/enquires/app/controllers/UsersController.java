package org.users.enquires.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.users.enquires.app.exception.ResourceNotFoundException;
import org.users.enquires.app.model.Users;
import org.users.enquires.app.repository.UsersRepository;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @PostMapping
    public Users createUsers(@RequestBody Users user) {
        return usersRepository.save(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> getUserById(@PathVariable long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Exists with id : " + id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<Users> updateUser(@PathVariable long id, @RequestBody Users userDetails) {
        Users updateUser = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not exist with id  " + id));
        updateUser.setFirstname(userDetails.getFirstname());
        updateUser.setLastname(userDetails.getLastname());
        updateUser.setLocation(userDetails.getLocation());
        usersRepository.save(updateUser);
        return ResponseEntity.ok(updateUser);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not exist with id  " + id));
        usersRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

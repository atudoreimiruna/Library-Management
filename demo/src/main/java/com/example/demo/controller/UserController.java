package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;
    @PostMapping("/saveUser")
    @Transactional
    public String saveUser(@RequestBody User user) {
        System.out.println("User save called...");

        User userIn = new User(user.getFirstName(), user.getLastName(), user.getEmail());

        List<Notification> notifications = new ArrayList<>();
        for (Notification notifIn : user.getNotifications()) {
            Notification notif = new Notification(notifIn.getMessage(), notifIn.getTimestamp(), notifIn.getStatus());
            notif.setUser(userIn);
            notifications.add(notif);
        }

        userIn.setNotifications(notifications);

        User userOut = userRepository.save(userIn);
        System.out.println("User out :: " + userOut);

        System.out.println("Saved!!!");
        return "User saved!!!";
    }

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable(name = "id") String id) {
        System.out.println("User get called...");

        User userOut = userRepository.getById(Long.valueOf(id));
        System.out.println("\nUser details :: \n" + userOut);
        System.out.println("\nList of Notifications :: \n" + userOut.getNotifications());

        System.out.println("\nDone!!!");
        return "User fetched...";
    }
}

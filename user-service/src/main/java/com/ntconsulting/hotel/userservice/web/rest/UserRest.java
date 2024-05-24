package com.ntconsulting.hotel.userservice.web.rest;

import com.ntconsulting.hotel.userservice.dto.UserDTO;
import com.ntconsulting.hotel.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRest {

    private final UserService userService;

    @Autowired
    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "save")
    public ResponseEntity<UserDTO> createUser(@RequestHeader("Authorization") String authorization, @RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }


}

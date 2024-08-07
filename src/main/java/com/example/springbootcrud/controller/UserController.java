package com.example.springbootcrud.controller;

import com.example.springbootcrud.domain.UserModel;
import com.example.springbootcrud.exceptionhandler.UserNotFoundException;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("getUsers")
    public ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> allUser=userService.getAllUser();
        System.out.println("All User"+allUser);
        return ResponseEntity.status(HttpStatus.OK).body(allUser);
    }
    @GetMapping("getUser/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable("id") int id){
        UserModel singalUser=userService.getUser(id);
        System.out.println("User details of "+id+" "+singalUser);
        return ResponseEntity.status(HttpStatus.OK).body(singalUser);
    }

    @PostMapping("saveUser")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel userModel){
        UserModel savedUser=userService.saveUser(userModel);
        System.out.println("saved User"+savedUser);
        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable ("id") int id) {
      try {
          boolean r=userService.deleteUser(id);
          System.out.println("UserData Deleted Successfully");
      }catch (Exception e){
          throw new UserNotFoundException();
      }
      return ResponseEntity.status(HttpStatus.OK).body("User Data Deleted Successfully");
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel){
        UserModel updatedUser= userService.updateUser(userModel);
        System.out.println("User Updated details"+updatedUser);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);
    }

}

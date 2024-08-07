package com.example.springbootcrud.service;

import com.example.springbootcrud.domain.UserModel;
import com.example.springbootcrud.exceptionhandler.DuplicateUSerException;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.repository.UserRepo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Transactional
    public UserModel saveUser (UserModel userModel){
        User user=this.populatetoUser(userModel);


        if (userRepo.findByName(user.getName()).isPresent()) {
            System.out.println("User with name " + user.getName() + " already exists.");
            throw new DuplicateUSerException();
        }

        userRepo.save(user);
        UserModel savedUserModel=this.populatetoUserModel(user);
        return savedUserModel;
    }
    public List<UserModel> getAllUser(){
        List<User> users=userRepo.findAll();
        List<UserModel> userModels= new ArrayList<>();
        for(User user:users){
            UserModel userModel=this.populatetoUserModel(user);
            userModels.add(userModel);
        }
        return userModels;
    }

    public UserModel getUser(int id){
        User singleUser=userRepo.findById(id).get();
        UserModel userModel=this.populatetoUserModel(singleUser);
        return userModel;
    }

    @Transactional
    public boolean deleteUser(int id){
        User user=userRepo.findById(id).get();
        userRepo.delete(user);
        return true;
    }

    public UserModel updateUser(UserModel userModel){
        int id=userModel.getId();
        User user=userRepo.findById(id).get();
        user.setName(userModel.getName());
        user.setPincode(userModel.getPincode());
        user.setAddress(userModel.getAddress());
        userRepo.save(user);
        return  userModel;
    }


    public User populatetoUser(UserModel userModel){
        User user=new User();
        user.setId(userModel.getId());
        user.setName(userModel.getName());
        user.setAddress(userModel.getAddress());
        user.setPincode(userModel.getPincode());
        return user;
    }
    public UserModel populatetoUserModel(User user){
        UserModel userModel=new UserModel();
        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setAddress(user.getAddress());
        userModel.setPincode(user.getPincode());
        return userModel;
    }

}

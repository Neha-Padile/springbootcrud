package com.example.springbootcrud.service;

import com.example.springbootcrud.domain.UserModel;

import java.util.List;

public interface UserService {
     UserModel saveUser(UserModel userModel);

     boolean deleteUser(int id);

     UserModel getUser(int id);

     List<UserModel> getAllUser();

     UserModel updateUser(UserModel updateUser);


}

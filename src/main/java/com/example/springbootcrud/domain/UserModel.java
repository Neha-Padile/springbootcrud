package com.example.springbootcrud.domain;

import lombok.Data;

@Data
public class UserModel {

    private int id;
    private String name;
    private String address;
    private int pincode;
}

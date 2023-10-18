package com.example.http.service;

import com.example.http.entity.User;

public interface TypeServise {
    User findUser(String account, String type);
}

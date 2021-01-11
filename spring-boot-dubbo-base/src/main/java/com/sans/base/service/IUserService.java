package com.sans.base.service;


import com.sans.base.dto.User;

import java.util.List;

public interface IUserService {
    List<User> queryList();
    User queryByID(String id);
    void  savaUserInfoToMongo(User user);
}
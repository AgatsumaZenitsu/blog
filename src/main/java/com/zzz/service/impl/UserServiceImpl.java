package com.zzz.service.impl;

import com.zzz.dao.UserRepository;
import com.zzz.pojo.User;
import com.zzz.service.UserService;
import com.zzz.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String userName, String password) {
        User user = userRepository.findByUserNameAndPassword(userName, MD5Utils.code(password));
        return user;
    }
}

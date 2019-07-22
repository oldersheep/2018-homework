package com.xxx.micro.user.service;

import com.xxx.micro.user.domain.User;
import com.xxx.micro.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Description
 * @Date 2019/7/21 21:08
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean save(User user) {

        return userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {

        return userRepository.findAll();
    }
}

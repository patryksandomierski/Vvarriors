package com.oliwa.web;

import com.oliwa.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
    @Autowired
    UserRepository repository;
}

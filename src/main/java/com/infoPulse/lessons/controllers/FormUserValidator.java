package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.daoTools.Dao;
import com.infoPulse.lessons.classesForTable.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FormUserValidator implements Validator {

    @Autowired
    Dao daoUser;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {

        // check in DB that there is no such user and then permit validation
//        boolean isUserExists = daoUser.isEntityExist(User.class, ((User) o).getLogin());
        boolean isUserExists = daoUser.isUserLoginExist(((User) o).getLogin());
//        System.out.println(isUserExists);
        if (isUserExists) {
            errors.rejectValue("login", "error.loginExists");
        }
    }
}

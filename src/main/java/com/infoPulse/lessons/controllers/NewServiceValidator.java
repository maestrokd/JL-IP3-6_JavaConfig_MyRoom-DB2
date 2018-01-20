package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.daoTools.Dao;
import com.infoPulse.lessons.classesForTable.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewServiceValidator implements Validator {

    @Autowired
    Dao dao;


    @Override
    public boolean supports(Class<?> aClass) {
        return Service.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        // check in DB that there is no such user and then permit validation
//        boolean isUserExists = dao.isEntityExist(Service.class, ((Service) o).getName());
        boolean isServiceExists = dao.isServiceExist(((Service) o).getName());
//        System.out.println(isUserExists);
        if (isServiceExists) {
            errors.rejectValue("name", "error.serviceNameExists");
        }
    }
}

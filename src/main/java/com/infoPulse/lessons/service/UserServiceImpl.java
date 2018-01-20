package com.infoPulse.lessons.service;

import com.infoPulse.lessons.daoTools.Dao;
import com.infoPulse.lessons.classesForTable.Customer;
import com.infoPulse.lessons.classesForTable.CustomerStatus;
import com.infoPulse.lessons.classesForTable.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {

    @Autowired
    Dao dao;

    public boolean addCustomer(String userLogin, String customerStatusName, Customer customer) {
        User user = dao.findUserByLogin(userLogin);
        CustomerStatus customerStatus = dao.findCustomerStatusByName(customerStatusName);
        customer.setUser(user);
        customer.setCustomerStatus(customerStatus);
        customerStatus.getCustomerList().add(customer);
        user.getCustomerList().add(customer);

        System.out.println("addCostumer: 1");
//        dao.updateEntity(user);
        dao.addEntity(customer);
        System.out.println("addCostumer: 2");
        return true;
    }

    public boolean deleteCustomer(User user, String customerPhoneNumber) {
        System.out.println(12);
        Customer customer = dao.findCustomerByPhoneNumber(customerPhoneNumber);
        System.out.println(13);
        CustomerStatus customerStatus = customer.getCustomerStatus();
        customerStatus.getCustomerList().remove(customer);
        customer.setCustomerStatus(null);
        user.getCustomerList().remove(customer);
        customer.setUser(null);
//        customer.getCustomerServiceList().clear();
        System.out.println(1);
//        dao.updateEntity(customer);
        dao.updateEntity(user);
        System.out.println(2);
        dao.updateEntity(customerStatus);
        System.out.println(3);
        dao.deleteEntity(customer);
        System.out.println(4);
        return true;
    }

//    @Transactional
    public boolean deleteCustomer2(User user, String customerPhoneNumber) {
//        dao.getEntityManager().getTransaction().begin();
        System.out.println(1);
//        User userTemp = dao.findUserByLogin(user.getLogin());
//        System.out.println(2);
        Customer customerTemp = dao.findCustomerByPhoneNumber(customerPhoneNumber);
        System.out.println(3);

//        System.out.println("S" + userTemp.getCustomerList().size());
//        System.out.println(userTemp.getCustomerList().contains(customerTemp));
//        System.out.println(userTemp.getCustomerList());
//        System.out.println(customerTemp);
//        System.out.println("R" + userTemp.getCustomerList().remove(customerTemp));
//        customerTemp.setUser(null);
//        System.out.println("S" + userTemp.getCustomerList().size());
//        System.out.println(4);

//        CustomerStatus customerStatus = customerTemp.getCustomerStatus();
//        customerStatus.getCustomerList().remove(customerTemp);
//        customerTemp.setCustomerStatus(null);

//        System.out.println(5);
//        dao.updateEntity(userTemp);
//        System.out.println("S" + userTemp.getCustomerList().size());
//        System.out.println(6);
//        dao.updateEntity(customerStatus);
        System.out.println(7);
        dao.getEntityManager().remove(customerTemp);
//        dao.deleteEntity(customerTemp);
        System.out.println(8);
//        dao.getEntityManager().getTransaction().commit();
////        customer.getCustomerServiceList().clear();
////        dao.updateEntity(customer);
//        System.out.println(6);
        return true;
    }

}

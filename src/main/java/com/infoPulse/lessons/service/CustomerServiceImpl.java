package com.infoPulse.lessons.service;

import com.infoPulse.lessons.classesForTable.*;
import com.infoPulse.lessons.daoTools.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl {

    @Autowired
    Dao dao;


    public boolean updateCustomer(String userLogin, String customerStatusName, Customer customer) {
        User user = dao.findUserByLogin(userLogin);
        CustomerStatus customerStatus = dao.findCustomerStatusByName(customerStatusName);
        Customer customerDB = dao.findCustomerByPhoneNumber(customer.getPhoneNumber());

        if (customerDB.getUser() == null && customer.getUser() != null) {
            customerDB.setUser(user);
            user.getCustomerList().add(customerDB);

        }else if ( !customer.getUser().getLogin().equals(customerDB.getUser().getLogin())) {
            System.out.println("If UL");
            User oldUser = customerDB.getUser();
            oldUser.getCustomerList().remove(customerDB);
            customerDB.setUser(user);
            user.getCustomerList().add(customerDB);
            dao.updateEntity(oldUser);
        }

        if (customerDB.getCustomerStatus() == null && customer.getCustomerStatus() != null) {
            customerDB.setCustomerStatus(customerStatus);
            customerStatus.getCustomerList().add(customerDB);
        } else if (!customer.getCustomerStatus().getName().equals(customerDB.getCustomerStatus().getName())) {
            System.out.println("If CuSt");
            CustomerStatus oldCustomerStatus = customerDB.getCustomerStatus();
            oldCustomerStatus.getCustomerList().remove(customerDB);
            customerDB.setCustomerStatus(customerStatus);
            customerStatus.getCustomerList().add(customerDB);
            dao.updateEntity(oldCustomerStatus);
        }


        customerDB.setBalance(customer.getBalance());
        System.out.println("updateCostumer: 1");
        System.out.println(customerDB);
        dao.updateEntity(customerDB);
//        dao.updateEntity(user);
        System.out.println("updateCostumer: 2");

        return true;
    }

    public void addCustomerService(String selectedPhoneNumber, String selectedServiceName) {
        Customer selectedCustomer = dao.findCustomerByPhoneNumber(selectedPhoneNumber);
        com.infoPulse.lessons.classesForTable.Service selectedService = dao.getServiceForName(selectedServiceName);
        ServiceStatus serviceStatus = dao.getServiceStatusForName("disabled");
//        dao.addEntity(selectedCustomer.addService(selectedService, serviceStatus));
        selectedCustomer.addService(selectedService, serviceStatus);
        dao.updateEntity(selectedCustomer);
//        dao.updateEntity(selectedService);
    }

    public void deleteCustomerService(String phoneNumber, String serviceName) {
        System.out.println("/customerservice/{phonenumber}/{servicename}/delete: " + phoneNumber + "|" + serviceName);
        CustomerService customerService = dao.getCustomerServicesByPhoneNumberAndServiceName(phoneNumber, serviceName);

        Customer customer = dao.findCustomerByPhoneNumber(phoneNumber);
        com.infoPulse.lessons.classesForTable.Service service = dao.getServiceForName(serviceName);
        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
        customer.removeService(service);
        dao.updateEntity(customer);
        dao.updateEntity(service);
        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());

        System.out.println("Delete CustomerService id: " + customerService.getId());
//        dao.deleteEntity(customerService);
    }

}

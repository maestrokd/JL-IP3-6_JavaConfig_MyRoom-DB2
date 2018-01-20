package com.infoPulse.lessons.classesForTable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@Entity
@Table(name = "service")
//@XmlRootElement(name = "service")
public class Service {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Id
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "payroll")
    private float payroll;

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer", fetch = FetchType.EAGER)
    private List<CustomerService> customerServiceList = new ArrayList<>();

    public void addCustomer(Customer customer, ServiceStatus serviceStatus) {
        CustomerService customerService = new CustomerService();
        customerService.setServiceStatus(serviceStatus);
        customerService.setCustomer(customer);
        customerService.setService(this);
        customerServiceList.add(customerService);
        customer.getCustomerServiceList().add(customerService);
    }

//    @ManyToMany(mappedBy = "serviceList")
//    private List<Customer> customerList = new ArrayList<>();


    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private Set<Event> eventList = new HashSet<>();

    public void addEvent(Customer customer) {
        Event event = new Event();
        event.setCustomer(customer);
        event.setService(this);
        event.setDate(new Date());
        event.setCost(this.payroll);
        eventList.add(event);
        customer.getEventList().add(event);
    }


    // Constructors
    public Service() {
    }


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 //   @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  //  @XmlElement
    public float getPayroll() {
        return payroll;
    }

    public void setPayroll(float payroll) {
        this.payroll = payroll;
    }

    public List<CustomerService> getCustomerServiceList() {
        return customerServiceList;
    }

    public void setCustomerServiceList(List<CustomerService> customerServiceList) {
        this.customerServiceList = customerServiceList;
    }

    public Set<Event> getEventList() {
        return eventList;
    }

    public void setEventList(Set<Event> eventList) {
        this.eventList = eventList;
    }


    // Methods


    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", payroll=" + payroll +
                '}';
    }
}

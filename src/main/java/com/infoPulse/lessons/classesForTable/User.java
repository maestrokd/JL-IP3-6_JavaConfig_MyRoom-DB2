package com.infoPulse.lessons.classesForTable;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Component
@Entity
@Table(name = "user")
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class User {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "name")
    private String name;

//    @Id
    @Column(name = "login", length = 50)
    private String login;

    @Column
    private String password;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Customer> customerList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "role")
    private List<RoleUser> roleUserList = new ArrayList<>();

    public void addRole(Role role) {
        RoleUser roleUser = new RoleUser();
        roleUser.setUser(this);
        roleUser.setRole(role);
        roleUserList.add(roleUser);
        role.getRoleUserList().add(roleUser);
    }


    // Constructors
    public User() {}


    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int user_id) {
        this.id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<RoleUser> getRoleUserList() {
        return roleUserList;
    }

    public void setRoleUserList(List<RoleUser> roleUserList) {
        this.roleUserList = roleUserList;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

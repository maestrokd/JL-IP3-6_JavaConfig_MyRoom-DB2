package com.infoPulse.lessons.classesForTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 50)
    private String name;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "role")
    private List<RoleUser> roleUserList = new ArrayList<>();

    public void addUser(User user) {
        RoleUser roleUser = new RoleUser();
        roleUser.setUser(user);
        roleUser.setRole(this);
        roleUserList.add(roleUser);
        user.getRoleUserList().add(roleUser);
    }



    // Getter and Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleUser> getRoleUserList() {
        return roleUserList;
    }

    public void setRoleUserList(List<RoleUser> roleUserList) {
        this.roleUserList = roleUserList;
    }
}

package com.training.dat.Wallet.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Role {

    @Id
    private Long id;  // Using a fixed ID for predefined roles

    @NotBlank(message = "Role Name is required")
    @Column(nullable = false, unique = true, length = 50)
    private String roleName;

    // Constructors
    public Role() {}

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

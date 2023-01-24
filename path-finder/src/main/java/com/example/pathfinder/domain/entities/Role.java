package com.example.pathfinder.domain.entities;

import com.example.pathfinder.domain.enums.RoleName;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")

public class Role extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private RoleName role;

    public Role() {
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }
}

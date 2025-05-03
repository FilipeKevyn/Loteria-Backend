package com.project.loteria.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;

    private String name;

    private String email;

    private Double valueTotalInvested;

    private Set<User> friends = new HashSet<>();

    private Set<Pool> pools = new HashSet<>();

    public User(){}
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public Set<Pool> getPools() {
        return pools;
    }

    public Double getValueTotalInvested() {
        return valueTotalInvested;
    }

    public void setValueTotalInvested(Double valueTotalInvested) {
        this.valueTotalInvested = valueTotalInvested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

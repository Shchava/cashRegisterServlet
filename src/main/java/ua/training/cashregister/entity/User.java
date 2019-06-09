package ua.training.cashregister.entity;

import java.util.Objects;

public class User {

    private long id;

    private String username;

    private String email;

    private String passwordHash;

    private Roles role;

    public User() {
    }

    public User(String username, String email, String passwordHash, Roles role) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                email.equals(user.email) &&
                passwordHash.equals(user.passwordHash) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, passwordHash, role);
    }
}

package com.example.zavrsni.entitet;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Navijac implements Serializable {

    @Serial
    private static final long serialVersionUID = 3606278424823590949L;
    private Long id;
    private String username;
    private String password;

    public Navijac(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

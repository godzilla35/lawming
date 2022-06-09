package com.lawming.web.model.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private String email;

    private String role;

    public LoginForm(Long id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}

package com.vmware.store.dto.user;


import java.util.List;

public class JwtData {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtData(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
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

    @Override
    public String toString() {
        return "{" +
                "token : '" + token + '\'' +
                ", type : '" + type + '\'' +
                ", id : " + id +
                ", username : '" + username + '\'' +
                ", email : '" + email + '\'' +
                ", roles : " + roles +
                '}';
    }
}

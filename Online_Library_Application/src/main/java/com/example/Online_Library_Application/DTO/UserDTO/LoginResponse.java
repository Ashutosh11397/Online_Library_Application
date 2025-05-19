package com.example.Online_Library_Application.DTO.UserDTO;

public class LoginResponse {
    private String name;
    private String email;
    private int membershipMonths;
    private String token;

    public LoginResponse(String name, String email, int membershipMonths, String token) {
        this.name = name;
        this.email = email;
        this.membershipMonths = membershipMonths;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMembershipMonths() {
        return membershipMonths;
    }

    public void setMembershipMonths(int membershipMonths) {
        this.membershipMonths = membershipMonths;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}

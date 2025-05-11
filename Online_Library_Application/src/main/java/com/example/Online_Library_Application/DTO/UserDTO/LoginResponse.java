package com.example.Online_Library_Application.DTO.UserDTO;

public class LoginResponse {
    private String name;
    private String email;
    private int membershipMonths;

    public LoginResponse(String name, String email, int membershipMonths) {
        this.name = name;
        this.email = email;
        this.membershipMonths = membershipMonths;
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
}

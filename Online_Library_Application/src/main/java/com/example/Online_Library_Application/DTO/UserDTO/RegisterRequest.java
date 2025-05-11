package com.example.Online_Library_Application.DTO.UserDTO;

import java.time.LocalDate;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private LibraryUserType userType;
    private LocalDate membershipStartDate;
    private Integer membershipMonths;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LibraryUserType getUserType() {
        return userType;
    }

    public void setUserType(LibraryUserType userType) {
        this.userType = userType;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public Integer getMembershipMonths() {
        return membershipMonths;
    }

    public void setMembershipMonths(Integer membershipMonths) {
        this.membershipMonths = membershipMonths;
    }
}

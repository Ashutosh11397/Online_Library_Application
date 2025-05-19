package com.example.Online_Library_Application.DTO.UserDTO;


import com.example.Online_Library_Application.model.LibraryUserType;

import java.time.LocalDate;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LibraryUserType userType;
    private LocalDate membershipStartDate;
    private Integer membershipMonths;


    public UserResponseDTO(Long id, String name, String email, LibraryUserType userType,
                           LocalDate membershipStartDate, Integer membershipMonths) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.membershipStartDate = membershipStartDate;
        this.membershipMonths = membershipMonths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
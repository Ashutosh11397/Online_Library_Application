package com.example.Online_Library_Application.DTO.UserDTO;

import java.time.LocalDate;

public class UpdateMembershipRequest {
    private LocalDate membershipStartDate;
    private Integer membershipMonths;

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

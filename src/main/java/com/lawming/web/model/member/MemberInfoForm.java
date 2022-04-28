package com.lawming.web.model.member;

import com.lawming.domain.member.Member;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.*;

@Data
@Slf4j
public class MemberInfoForm {
    @NotNull
    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @Email
    private String emailAddress;

    private String phoneNumber;

    public MemberInfoForm() {}

    public MemberInfoForm(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.emailAddress = member.getEmailAddress();
        this.phoneNumber = member.getPhoneNumber();
    }
}

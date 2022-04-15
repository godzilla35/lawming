package com.lawming.server.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    private String userId;

    private String password;

    private String userName;

    @Column(unique = true)
    private String emailAddress;

    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;


    @OneToOne(mappedBy = "requestMember", fetch = FetchType.LAZY)
    private Order orders;

    public static Member createMember(String userId,
                                      String userName,
                                      String password,
                                      String emailAddress,
                                      String phoneNumber) {
        Member member = new Member();
        member.setUserId(userId);
        member.setUserName(userName);
        member.setPassword(password);
        member.setEmailAddress(emailAddress);
        member.setPhoneNumber(phoneNumber);
        return member;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Item> getItems() {
        return items;
    }

    public Order getOrders() {
        return orders;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

package com.lawming.domain.member;

import com.lawming.domain.order.Order;
import com.lawming.domain.item.Item;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @Email
    @Column(unique = true)
    private String emailAddress;

    private String phoneNumber;

    private String role;

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
        member.setLoginId(userId);
        member.setName(userName);
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

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
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

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}
}

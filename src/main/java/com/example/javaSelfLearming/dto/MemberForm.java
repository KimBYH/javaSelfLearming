package com.example.javaSelfLearming.dto;

import com.example.javaSelfLearming.entity.Member;

public class MemberForm {

    private String email;
    private String password;

    public MemberForm(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Member toEntity(){
        return new Member(null, email, password);
    }

    public String toString(){
        return "Member = {email : " + email + ", password : " + password + "}";
    }
}

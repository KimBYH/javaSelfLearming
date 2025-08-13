package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.MemberForm;
import com.example.javaSelfLearming.entity.Member;
import com.example.javaSelfLearming.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class memberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/new")
    public String newMemberForm(){
        return "members/join";
    }

    @PostMapping("/create")
    public String joinMember(MemberForm form){
        Member member = form.toEntity();
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());

        return "greetings";
    }

}

package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.MemberForm;
import com.example.javaSelfLearming.entity.Member;
import com.example.javaSelfLearming.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/members")
public class memberController {

    @Autowired
    private MemberRepository memberRepository;
    
    @GetMapping("")
    public String membersIndex(Model model){
        ArrayList<Member> membersList = memberRepository.findAll();
        model.addAttribute("membersList", membersList);
        return "members/index";
    }

    @GetMapping("/new")
    public String newMemberForm(){
        return "members/join";
    }

    @GetMapping("/{id}")
    public String showMember(@PathVariable Long id, Model model){

        Member member = memberRepository.findById(id).orElseThrow(()->null);

        model.addAttribute("member", member);

        return "members/show";
    }

    @PostMapping("/create")
    public String joinMember(MemberForm form){
        log.info(form.toString());

        Member member = form.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

}

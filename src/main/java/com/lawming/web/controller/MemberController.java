package com.lawming.web.controller;

import com.lawming.domain.member.Member;
import com.lawming.domain.repository.MemberRepository;
import com.lawming.web.argumentresolver.Login;
import com.lawming.web.model.member.MemberInfoForm;
import com.lawming.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/myInfo")
    public String getMember(@Login Member loginMember, Model model) {
        if(loginMember == null) {
            // 로그인 되지 않은 사용자에 대한 요청은 짤라야함
        }
        MemberInfoForm memberInfoForm = new MemberInfoForm(loginMember);
        model.addAttribute("memberInfoForm", memberInfoForm);

        return "/form/myInfo";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }

        memberService.join(member);

        return "redirect:/";
    }
}

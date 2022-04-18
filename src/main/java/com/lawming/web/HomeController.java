package com.lawming.web;

import com.lawming.domain.member.Member;
import com.lawming.web.service.MemberService;
import com.lawming.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final SessionManager sessionManager;


    //@GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if(memberId == null ){
            return "home";
        }

        Member loginMember = memberService.findOne(memberId);
        if(loginMember == null ){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";

    }

    //@GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        Member member = (Member)sessionManager.getSession(request);

        if(member == null ){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";

    }

    //@GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        // 처음 들어오는 사용자는 로그인을 할지 안할지 모르기때문에 세션 생성은 false 옵션으로 한다
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "home";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null) {
            return "home";
        }
        
        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";

    }

    @GetMapping("/")
    public String homeLoginV3(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model) {

        if(loginMember == null) {
            log.info("===### homeLoginV3 loginMember null");
            return "home";
        }

        log.info("===### homeLoginV3 loginMember not null");
        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        return "loginHome";

    }

}

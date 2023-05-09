package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @PostMapping({"/member/submit"})
    public String submit(HttpServletRequest request, Member member) throws UnsupportedEncodingException {
        Member dto = memberService.oneMember(request, member);
        if (StringUtils.isNotBlank(dto.getMemId())) {
            return "redirect:/";
        } else {
            return "redirect:/login";
        }

    }

    @GetMapping({"/logout"})
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}

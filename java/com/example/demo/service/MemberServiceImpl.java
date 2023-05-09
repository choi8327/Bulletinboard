package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member oneMember(HttpServletRequest request, Member member) throws UnsupportedEncodingException {
        Member dto = new Member();
        String enabled = "1";
        Optional<Member> optional = memberRepository.findByMemIdAndMemPasswdAndMemEnabled(member.getMemId(), member.getMemPasswd(), enabled);
        if (optional.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("sessId", optional.get().getMemId());
            session.setAttribute("sessName", optional.get().getMemName());
            dto = optional.get();
        }
        return dto;
    }
}

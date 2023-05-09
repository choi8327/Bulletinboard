package com.example.demo.service;

import com.example.demo.entity.Member;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface MemberService {
    Member oneMember(HttpServletRequest request, Member member) throws UnsupportedEncodingException;
}

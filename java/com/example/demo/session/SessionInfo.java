package com.example.demo.session;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Data
public class SessionInfo {
   private String sessId;  // memder_id
   private String sessName;  // member_name

   public SessionInfo(HttpServletRequest request) {
      HttpSession session = request.getSession();

      setSessId((String) session.getAttribute("sessId"));
      setSessName((String) session.getAttribute("sessName"));
   }
}

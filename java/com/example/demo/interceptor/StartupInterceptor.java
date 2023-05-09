package com.example.demo.interceptor;

import com.example.demo.session.SessionInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StartupInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (handler instanceof HandlerMethod) {

            SessionInfo sessionInfo = new SessionInfo(request);

System.out.println(sessionInfo);

            if (StringUtils.isNotBlank(sessionInfo.getSessId())) {
                request.setAttribute("member", sessionInfo);
            }

            if (StringUtils.isBlank(sessionInfo.getSessId())) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            } else {
                if (request.getRequestURI().startsWith(request.getContextPath() + "/login" )) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return true;
                }
            }
        }

        return true;
    }
}

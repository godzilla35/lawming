package com.lawming.web.filter;

import com.lawming.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whiteList = {"/", "/members/add", "/login", "/logout", "/css/*"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("LoginCheckFilter doFilter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try{
            log.info("인증 체크 필터 시작 = {}", requestURI);
            if(isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행");
                HttpSession session = httpServletRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청");

                    // ?redirectURL= 을 붙여서 로그인 이후에 다시 원래 가고자 했던 페이지로 이동하도록 함
                    httpServletResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        }catch (Exception e) {
            throw e;
        }finally {
            log.info("인증 체크 필터 종료 = {}", requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}

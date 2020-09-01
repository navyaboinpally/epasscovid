package com.virtusa.epasscovid19.config;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.virtusa.epasscovid19.models.UserFrontVo;
import com.virtusa.epasscovid19.repos.UserFrontRepository;
import com.virtusa.epasscovid19.services.EmailService;

import lombok.extern.java.Log;

@Log
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserFrontRepository userFrontRepository;

    UserFrontVo userFrontVo;

    @Autowired
    SessionUtils sessionUtils;

    @Autowired
    EmailService emailService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        userFrontVo = userFrontRepository.findByUserNameAndIsDeleted(authentication.getName(), 0);
        authentication.getAuthorities().forEach((a ->
        {
            log.severe(a.getAuthority());

        }));

        HttpSession session = request.getSession();
        session.setAttribute("userFrontVo", userFrontVo);
        session.setAttribute("userName", userFrontVo.getUserName());
        session.setAttribute("name", userFrontVo.getName());
        session.setAttribute("userId", userFrontVo.getUserFrontId());
        session.setAttribute("userRole", userFrontVo.getUserRoleVo().getUserRoleName());
        session.setAttribute("userType",userFrontVo.getUserType());
//        if (userFrontVo.getUserType().equalsIgnoreCase("ADMIN")) {
//            response.sendRedirect("/admin");
//        } else if (userFrontVo.getUserType().equalsIgnoreCase("USER")) 
//        {
//            response.sendRedirect("/user");
//        }
//        
        try {
			emailService.generateOtp();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        response.sendRedirect("/otp");


    }

}

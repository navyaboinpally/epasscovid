package com.virtusa.epasscovid19.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.epasscovid19.models.UserFrontVo;
import com.virtusa.epasscovid19.models.UserRoleVo;
import com.virtusa.epasscovid19.repos.UserFrontRepository;
import com.virtusa.epasscovid19.repos.UserRoleRepository;
import com.virtusa.epasscovid19.services.OtpService;

import lombok.extern.java.Log;

@Controller
@Log
public class LoginController 
{
	@Autowired
	UserFrontRepository userFrontRepository;

    @Autowired
    public OtpService otpService;

    
	@Autowired
	UserRoleRepository userRoleRepository;
	@RequestMapping(value= {"login"})
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		log.info("cooooooooooooooooooooooooool");
		return modelAndView;
	}
	@GetMapping(value = {"","/"})
	public String  home() {
		return "demo";
	}
	
	@GetMapping("/register")
	public String  register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String  registernow	(@ModelAttribute UserFrontVo user) 
	{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		user.setUserType("USER");
		user.setUserName(user.getEmail());
		UserRoleVo role= userRoleRepository.findById(2L).orElse(null);
		user.setUserRoleVo(role);		
		userFrontRepository.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/otp")
	public String  otp() {
		return "common/otp";
	}
	
	
	  @PostMapping(value = "/otp")
      public String validateOtp(@RequestParam("otpnum") int otpnum, ModelMap modelMap,HttpSession session) 
	  {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String username = auth.getName();
	        String userType=session.getAttribute("userType").toString();
	        //Validate the Otp
	        int serverOtp = otpService.getOtp(username);
	        if (otpnum == serverOtp) 
	        {
	            otpService.clearOTP(username);
	            if (userType.equalsIgnoreCase("ADMIN")) 
	            {
	            	return "redirect:/admin";
	            }else if (userType.equalsIgnoreCase("USER")) 
	            {
	            	return "redirect:/user";
	            }            	          
	        }
	            modelMap.addAttribute("msg", "Invalid otp");
	            return "common/otp";	        
	    }

}

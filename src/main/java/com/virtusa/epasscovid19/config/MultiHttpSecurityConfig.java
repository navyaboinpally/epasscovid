package com.virtusa.epasscovid19.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@EnableWebSecurity
public class MultiHttpSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }


    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable();
            http.headers().frameOptions().sameOrigin();
            http.authorizeRequests()
                    .antMatchers("/location/**", "/assets/**", "/image/**", "/img/**","/js/**","/css/**","/fonts/**", "/public/**",
                            "/resources/public/**", "/resources/**", "/register","/")
                    .permitAll().antMatchers("/login").permitAll().antMatchers("/allpermit/**").permitAll()
                    .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .anyRequest().authenticated().and().formLogin().loginPage("/login")
                    .successHandler(customizeAuthenticationSuccessHandler).usernameParameter("userName")
                    .passwordParameter("password").and()
                    .rememberMe()
                    .key("rem-me-key")
                    .rememberMeParameter("remember-me") // it is name of checkbox at login page
                    .authenticationSuccessHandler(customizeAuthenticationSuccessHandler)
                    .rememberMeCookieName("rememberlogin") // it is name of the cookie
                    .tokenValiditySeconds(365 * 24 * 60 * 60) // remember for number of seconds
                    .and().logout().logoutSuccessUrl("/login?logout")
                    .deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true).and().exceptionHandling()
                    .accessDeniedPage("/").and().csrf().disable();

            http.headers().frameOptions().sameOrigin();
            http.sessionManagement().invalidSessionUrl("/login").maximumSessions(100).maxSessionsPreventsLogin(false).expiredUrl("/login?error=expired").sessionRegistry(sessionRegistry());
            http.sessionManagement().sessionFixation().migrateSession()
                    .sessionAuthenticationStrategy(registerSessionAuthStr());
        }

        @Bean
        public SessionRegistry sessionRegistry() {
            return new SessionRegistryImpl();
        }

        @Bean
        public RegisterSessionAuthenticationStrategy registerSessionAuthStr() {
            return new RegisterSessionAuthenticationStrategy(sessionRegistry());
        }

        @Bean
        public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
            return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
        }


    }
}

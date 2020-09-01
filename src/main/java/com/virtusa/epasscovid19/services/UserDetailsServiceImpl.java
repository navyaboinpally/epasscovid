package com.virtusa.epasscovid19.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virtusa.epasscovid19.models.UserFrontVo;
import com.virtusa.epasscovid19.repos.UserFrontRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserFrontRepository userFrontRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserFrontVo userFrontVo = userFrontRepository.findByUserNameAndIsDeleted(username, 0);

        logger.info("loadUserByUsername username=" + username);

        if (userFrontVo == null)
            throw new UsernameNotFoundException(username + " not found");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (userFrontVo.getUserType().equalsIgnoreCase("ADMIN")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(username, userFrontVo.getPassword(), grantedAuthorities);
    }

}

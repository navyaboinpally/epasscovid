package com.virtusa.epasscovid19.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.epasscovid19.models.UserRoleVo;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleVo, Long> {
    UserRoleVo findByUserRoleId(long userRoleId);
}

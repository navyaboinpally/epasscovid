package com.virtusa.epasscovid19.repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.epasscovid19.models.UserFrontVo;

@Repository
public interface UserFrontRepository extends JpaRepository<UserFrontVo, Long> {

    UserFrontVo findByUserNameAndIsDeleted(String username, int status);

    public UserFrontVo findByUserFrontId(long userFrontId);

    public UserFrontVo findByUserFrontIdAndIsDeleted(long companyid, int isDeleted);

    @Transactional
    @Modifying
    @Query("UPDATE UserFrontVo SET password = ?2 WHERE userFrontId = ?1")
    void updatepassword(long userFrontId, String password);

}

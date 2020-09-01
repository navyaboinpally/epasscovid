package com.virtusa.epasscovid19.repos;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.virtusa.epasscovid19.models.EpassApplication;

@Repository	
public interface EpassApplicationRepo extends JpaRepository<EpassApplication, Long>,DataTablesRepository<EpassApplication, Long>
{
    @Query(value = "select count(*) from tbl_epass_applications where is_deleted=0", nativeQuery = true)
    int getTotalEpassApplication();

    @Query(value = "select count(*) from tbl_epass_applications where application_status = 0 and is_deleted=0", nativeQuery = true)
    int getPendingEpassApplication();

    @Query(value = "select count(*) from tbl_epass_applications where application_status = 1 and is_deleted=0", nativeQuery = true)
    int getAcceptedApplication();

    @Query(value = "select count(*) from tbl_epass_applications where application_status = 2 and is_deleted=0", nativeQuery = true)
    int getRejectedApplication();

    @Query(value = "select count(*) from tbl_epass_applications where is_deleted=0 and user_id=?1", nativeQuery = true)
    int getTotalEpassApplicationofUser(long id);

    @Query(value = "select count(*) from tbl_epass_applications where application_status = 0 and is_deleted=0 and user_id=?1", nativeQuery = true)
    int getPendingEpassApplicationofUser(long id);

    @Query(value = "select count(*) from tbl_epass_applications where application_status = 1 and is_deleted=0 and user_id=?1", nativeQuery = true)
    int getAcceptedApplicationofUser(long id);

    @Query(value = "select count(*) from tbl_epass_applications where application_status = 2 and is_deleted=0 and user_id=?1", nativeQuery = true)
    int getRejectedApplicationofUser(long id);


    @Modifying
    @Query("update EpassApplication set applicationStatus=?2 where epassApplicationId=?1")
	void updatestatus(long id, int status);
	
}

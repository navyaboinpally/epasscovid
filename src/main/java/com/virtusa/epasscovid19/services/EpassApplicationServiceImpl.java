package com.virtusa.epasscovid19.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.epasscovid19.models.EpassApplication;
import com.virtusa.epasscovid19.repos.EpassApplicationRepo;

@Service
@Transactional
public class EpassApplicationServiceImpl implements EpassApplicationService 
{
	@Autowired
	EpassApplicationRepo epassApplicationRepo;

	@Override
	public void updateStatus(long id, int status) 
	{		
		epassApplicationRepo.updatestatus(id,status);
	}

	@Override
	public EpassApplication save(EpassApplication epassApplication) {
		// TODO Auto-generated method stub
		return epassApplicationRepo.save(epassApplication);
	}

}

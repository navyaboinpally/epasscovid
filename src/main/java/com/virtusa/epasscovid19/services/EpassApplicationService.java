package com.virtusa.epasscovid19.services;

import com.virtusa.epasscovid19.models.EpassApplication;

public interface EpassApplicationService 
{
	void updateStatus(long id,int status);
	EpassApplication save(EpassApplication epassApplication);

}

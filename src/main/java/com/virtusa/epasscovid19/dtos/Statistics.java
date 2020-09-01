package com.virtusa.epasscovid19.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistics 
{
	private int totalapplications;
	private int pendingapplications;
	private int acceptedapplications;
	private int rejectedapplications;
	
}

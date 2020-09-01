package com.virtusa.epasscovid19.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_district")
@Getter
@Setter
public class District 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="district_id")
	private long districtId;
	
	private String districtName;
	
	
	
}

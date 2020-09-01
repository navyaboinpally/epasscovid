package com.virtusa.epasscovid19.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_epass_applications")
@Data
public class EpassApplication 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="epassApplication_id")
	private long epassApplicationId;

	@Column(name = "epassApplication_no")
	private String epassApplicationNo;
	@Column(name="fullname")
	private String fullname;
	@Column(name="email")
    private String email;
	@Column(name="phonenumber")
    private String phonenumber;
	@Column(name="sourceaddress")
    private String sourceaddress;
	@Column(name="destinationaddress")
    private String destinationaddress;
	@Column(name="idproofselect")
    private String idproofselect ;
	@Column(name="idproofnumber")
    private String idproofnumber;
	@Column(name="gender")
    private String gender;

	private String presentDistrict;
	private String destinationDistrict;


	@Lob
	@Column(name = "Image")
	private byte[] image;

	@Lob
	@Column(name = "idproofimage")
	private byte[] idproofimage;



	@Column(name = "application_status", columnDefinition = "int default 0") //0 pending 1 accepted 2 rejected
	private int applicationStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="created_on",length=50,updatable=false)
	private Date createdOn;

	@Column(name = "is_deleted", length = 1, columnDefinition = "int  default 0")
	private int isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_front_id")
    private UserFrontVo userFrontVo;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 50)
	private Date startdate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 50)
	private Date enddate;

}

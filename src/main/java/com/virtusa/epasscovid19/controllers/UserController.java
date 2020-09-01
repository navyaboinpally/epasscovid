package com.virtusa.epasscovid19.controllers;

import com.virtusa.epasscovid19.dtos.Statistics;
import com.virtusa.epasscovid19.models.District;
import com.virtusa.epasscovid19.models.EpassApplication;
import com.virtusa.epasscovid19.models.UserFrontVo;
import com.virtusa.epasscovid19.repos.DistrictRepository;
import com.virtusa.epasscovid19.repos.EpassApplicationRepo;
import com.virtusa.epasscovid19.repos.UserFrontRepository;
import com.virtusa.epasscovid19.services.EpassApplicationService;
import com.virtusa.epasscovid19.util.JasperExporter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Log
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	JasperExporter jasperExporter;

	@Autowired
	EpassApplicationRepo epassApplicationRepo;
	
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	EpassApplicationService epassApplicationService;

	@Autowired
	UserFrontRepository userFrontRepository;

	@RequestMapping(value = {"","/"})
	public String name() {
		return "user/index";
	}
	
	@PostMapping("/applications/statistics")
	@ResponseBody
	public Statistics epassApplicationstatistics(HttpSession session)
	{
		long userId=Long.parseLong(session.getAttribute("userId").toString());
		log.warning("statistics user id " + userId);
		return new Statistics(epassApplicationRepo.getTotalEpassApplicationofUser(userId), epassApplicationRepo.getPendingEpassApplicationofUser(userId), epassApplicationRepo.getAcceptedApplicationofUser(userId),epassApplicationRepo.getRejectedApplicationofUser(userId)) ;
		
	}
	
	@GetMapping("/applications")
	public String applications(@RequestParam(value = "status",defaultValue = "all") String status,Model m) 
	{
		m.addAttribute("status", status);	
		return "user/applications";
	}
	
	@GetMapping("/applications/new")
	public String applications(Model m) 
	{
		List<District> districts=districtRepository.findAll();
		log.info("Total "+districts.size());
		m.addAttribute("districts", districts);
		return "user/applications-new";
	}
	
	@PostMapping("/applications/new")
	public String registernow(@ModelAttribute EpassApplication epassApplication, @RequestParam("picid") MultipartFile file, @RequestParam("idpic") MultipartFile idpic, HttpSession session) {
		ZoneId defaultZoneId = ZoneId.systemDefault();

		long userId = Long.parseLong(session.getAttribute("userId").toString());
		UserFrontVo userFrontVo = userFrontRepository.findById(userId).orElse(null);
		epassApplication.setStartdate(new Date());
		epassApplication.setEnddate(Date.from(LocalDate.now().plusDays(15).atStartOfDay(defaultZoneId).toInstant()));


		epassApplication.setUserFrontVo(userFrontVo);
		epassApplication.setEpassApplicationNo("TA" + epassApplication.getPhonenumber());
		try {
			byte[] image = file.getBytes();
			byte[] imageidpic = idpic.getBytes();

			epassApplication.setImage(image);
			epassApplication.setIdproofimage(imageidpic);
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		epassApplicationService.save(epassApplication);
		return "redirect:/user/applications";
	}

	@PostMapping("/applications/datatable")
	@ResponseBody
	public DataTablesOutput<EpassApplication> epassApplicationDatatable(@Valid DataTablesInput input,
																		@RequestParam Map<String, String> allRequestParams, HttpSession session)
	{
		long userId=Long.parseLong(session.getAttribute("userId").toString());

		Specification<EpassApplication>  specification =new Specification<EpassApplication>() 
		{
			@Override
			public Predicate toPredicate(Root<EpassApplication> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) 
			{
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(criteriaBuilder.equal(root.get("isDeleted"), 0));
				predicates.add(criteriaBuilder.equal(root.get("userFrontVo").get("userFrontId"), userId));
				if (!allRequestParams.get("status").equalsIgnoreCase("all")) 
				{
                    predicates.add(criteriaBuilder.equal(root.get("applicationStatus"), Integer.parseInt(allRequestParams.get("status").toString())));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		DataTablesOutput<EpassApplication> dataTablesOutput=epassApplicationRepo.findAll(input, null,specification);
		return dataTablesOutput;
	}

	@GetMapping("/applications/{id}/pdf")
	public void applicationspdf(HttpSession session, @PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap jasperParameter = new HashMap();
		jasperParameter.put("epass_id", id);
		jasperExporter.jasperExporterPDF(jasperParameter,
				request.getServletContext().getRealPath("/") + "report"
						+ System.getProperty("file.separator") + "/id.jrxml", "ID", response);

	}

}
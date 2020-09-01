package com.virtusa.epasscovid19.controllers;

import com.virtusa.epasscovid19.dtos.Statistics;
import com.virtusa.epasscovid19.models.EpassApplication;
import com.virtusa.epasscovid19.repos.EpassApplicationRepo;
import com.virtusa.epasscovid19.services.EpassApplicationService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EpassApplicationRepo epassApplicationRepo;

    @Autowired
    EpassApplicationService epassApplicationService;

    @RequestMapping(value = {"","/"})
    public String name() {
        return "admin/index";
    }


    @GetMapping("/applications")
    public String applications(@RequestParam(value = "status",defaultValue = "all") String status, Model m) {
        m.addAttribute("status", status);
        return "admin/applications";
    }

    @PostMapping("/applications/datatable")
    @ResponseBody
    public DataTablesOutput<EpassApplication> epassApplicationDatatable(@Valid DataTablesInput input,
                                                                        @RequestParam Map<String, String> allRequestParams, HttpSession session) throws URISyntaxException {

//		final String baseUrl = "https://api.covid19india.org/state_district_wise.json";
//		URI uri = new URI(baseUrl);
//		ResponseEntity<JSONObject> result = restTemplate.getForEntity(uri, JSONObject.class);
//		log.info("Cool");
//
//		JSONObject jsonObject = new JSONObject(result.getBody().get("Telangana"));
//		JSONObject json = (JSONObject) parser.parse(stringToParse);
//
//
//		//JSONObject data = new JSONObject(jsonObject.getString("Telangana"));
//
//		log.info(jsonObject.toString());

//		JSONObject k=(JSONObject)result.getBody();
//		JSONObject kk= (JSONObject) k.get("Telangana");
//		log.info(kk.toString());


        Specification<EpassApplication>  specification =new Specification<EpassApplication>() {
            @Override
            public Predicate toPredicate(Root<EpassApplication> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get("isDeleted"), 0));

                if (!allRequestParams.get("status").equalsIgnoreCase("all")) {
                    predicates.add(criteriaBuilder.equal(root.get("applicationStatus"), Integer.parseInt(allRequestParams.get("status").toString())));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        DataTablesOutput<EpassApplication> dataTablesOutput=epassApplicationRepo.findAll(input, null,specification);
        return dataTablesOutput;
    }


    @PostMapping("/applications/statistics")
    @ResponseBody
    public Statistics epassApplicationstatistics() {
        return new Statistics(epassApplicationRepo.getTotalEpassApplication(), epassApplicationRepo.getPendingEpassApplication(), epassApplicationRepo.getAcceptedApplication(),epassApplicationRepo.getRejectedApplication()) ;

    }

    @GetMapping("/applications/{id}/update/{status}")
    public String applications(@PathVariable long id,@PathVariable int status) {
        epassApplicationService.updateStatus(id, status);
        return "redirect:/admin/applications";
    }
}

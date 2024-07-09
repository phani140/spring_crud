package com.sathya.rest.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sathya.rest.entity.Hospital;
import com.sathya.rest.model.HospitalDetails;
import com.sathya.rest.service.HospitalService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	@PostMapping("/save")
	public Hospital createHospital( @Valid @RequestBody HospitalDetails hospitalDetails ) { 
		
		return hospitalService.createHospital(hospitalDetails);        
	}
	
	@GetMapping("/{id}")
	public Hospital getHospital(@PathVariable long id) {
		return hospitalService.getHospital(id);
	}
	
	
	@GetMapping("/getall")
	public List<Hospital> getHospitals() {
		return hospitalService.getHospitalList();
	}
	
	@DeleteMapping("/{id}")
	public void deleteHospitalById(@PathVariable long id) {
		hospitalService.deleteHospital(id);
	}
	
	@PostMapping("/saveall")
	public List<Hospital> saveAll(@RequestBody List<HospitalDetails> hospitals) {
		
		return hospitalService.saveAllHospitals(hospitals);
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<Hospital> patchUpdate(@PathVariable long id, @RequestBody Map<String, Object> updateHospitals)
	{ return hospitalService.partialUpdateHospital(id,updateHospitals);
	}
	
	
	@GetMapping("/byname/{name}")
    public Hospital getHospitalByName(@PathVariable String name) {
        return hospitalService.getHospitalByName(name);
	//http://localhost:2021/api/hospital/byname/nims
	}
	
    @GetMapping("/bynameandlocation")
    public Hospital getHospitalByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        return hospitalService.getHospitalByNameAndLocation(name, location);
     // http://localhost:2021/api/hospital/bynameandlocation?name=apolo&location=gachibowli
    }
    
    @GetMapping("/by-rating")
    public ResponseEntity<List<Hospital>> getHospitalsByRatingRange(@RequestParam double minRating, @RequestParam double maxRating) {
        List<Hospital> hospitals = hospitalService.getHospitalsByRatingRange(minRating, maxRating);
        return ResponseEntity.ok(hospitals);
    //http://localhost:2021/api/hospital/by-rating?minRating=4&maxRating=7
    }
    
    @GetMapping("/by-names")
    public ResponseEntity<List<Hospital>> getHospitalsByNames(@RequestParam List<String> names) {
        List<Hospital> hospitals = hospitalService.getHospitalsByNames(names);
        return new ResponseEntity<>(hospitals, HttpStatus .OK);
      //http://localhost:2021/api/hospital/by-names?names=kims,apolo
    }
    
   
    
    
    @GetMapping("/getallsort")
    public List<Hospital> getAllHospitalsSorted() {
        return hospitalService.getAllHospitalsSorted();
    }
    
    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<Hospital> getAllHospitalsByPaging(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return hospitalService.getDataByPaging(pageNumber, pageSize);
    }
    
    
    
    
}



	
	
	
	
	
	
	



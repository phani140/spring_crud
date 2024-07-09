package com.sathya.rest.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sathya.rest.entity.Hospital;
import com.sathya.rest.model.HospitalDetails;
import com.sathya.rest.repository.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;
	
	public Hospital createHospital(HospitalDetails hospitalDetails) {
		Hospital hospital=new Hospital();
		hospital.setName(hospitalDetails.getName());
		hospital.setLocation(hospitalDetails.getLocation());
		hospital.setRating(hospitalDetails.getRating());
		hospital.setEmail(hospitalDetails.getEmail());
		hospital.setMobile(hospitalDetails.getMobile());
		hospital.setCreateAt(LocalDateTime.now());
		hospital.setCreateBy(System.getProperty("user.name"));
		
		
		Hospital savedHospital = hospitalRepository.save(hospital);
		return savedHospital;		
		
	}

	public Hospital getHospital(long id) {
	  Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
	    
	      if(optionalHospital.isPresent()) {
	    	  return optionalHospital.get();
	      }
	      else
	      {
	    	  return null;
	      }
	}

	
	public List<Hospital> getHospitalList() {
		return hospitalRepository.findAll();
	}

	public void deleteHospital(long id) {
		hospitalRepository.deleteById(id);
	}

	public List<Hospital> saveAllHospitals(List<HospitalDetails> details) {
		
		List<Hospital> hospitals = new ArrayList<>();
		for(HospitalDetails hospitalDetails: details) {
			Hospital hospital=new Hospital();
			hospital.setName(hospitalDetails.getName());
			hospital.setLocation(hospitalDetails.getLocation());
			hospital.setRating(hospitalDetails.getRating());
			hospital.setEmail(hospitalDetails.getEmail());
			hospital.setMobile(hospitalDetails.getMobile());

			hospital.setCreateAt(LocalDateTime.now());
			hospital.setCreateBy(System.getProperty("user.name"));
			
			hospitals.add(hospital);
			
		}
		return hospitalRepository.saveAll(hospitals);
	}

	
		

	
	public ResponseEntity<Hospital> partialUpdateHospital(long id, Map<String, Object> updateHospitals) {
		Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if (hospitalOptional.isPresent()) 
        {
            Hospital hospital = hospitalOptional.get();
            
            
            if (updateHospitals.containsKey("name")) {
                hospital.setName((String) updateHospitals.get("name"));
            }
            if (updateHospitals.containsKey("location")) {
                hospital.setLocation((String) updateHospitals.get("location"));
            }
            if (updateHospitals.containsKey("rating")) {
                hospital.setRating((int) updateHospitals.get("rating"));
            }
            hospitalRepository.save(hospital);
            return ResponseEntity.ok(hospital);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
	}
	
	
	
	public Hospital updateHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
	}
	
	public Hospital getHospitalByName(String name) {
        return hospitalRepository.findByName(name);
	}

	public Hospital getHospitalByNameAndLocation(String name, String location) {
	  return hospitalRepository.findByNameAndLocation(name, location);
	}
	
	public List<Hospital> getHospitalsByRatingRange(double minRating, double maxRating) {
        return hospitalRepository.findByRatingBetween(minRating, maxRating);
    }
	 
	 public List<Hospital> getHospitalsByNames(List<String> names) {
	        return hospitalRepository.findByNameIn(names);
	 }
	 
	 
	 public List<Hospital> getAllHospitalsSorted(){
		 Sort sort = Sort.by(Direction.DESC,"name");
		 return hospitalRepository.findAll(sort);
	 }
	 
	 
	 public Page<Hospital> getDataByPaging(int pageNumber,int pageSize){
		 Pageable pageable = PageRequest.of(pageNumber, pageSize);
		 return hospitalRepository.findAll(pageable);
	 }
}
		


		
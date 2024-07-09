package com.sathya.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathya.rest.entity.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	   Hospital findByName(String name);
	 
	   Hospital findByNameAndLocation(String name, String location);
	   
	   List<Hospital> findByRatingBetween(double minRating, double maxRating);
	 
	   List<Hospital> findByNameIn(List<String> names);

	   
}

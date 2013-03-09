package com.hospital.services;

import java.util.List;
import java.util.Set;

import com.hospital.model.Patient;


public interface PatientService {
    
    Integer countByRoom(Integer roomId);
    
    List findByRoomGeneric(Integer roomId);

	List<Patient> findByRoom(Integer roomId);
	
	void generateReport();
	
	Set<Patient> findAll();
	 
	int save(Patient patient);
	    
	int updateRoom(Patient patient);
	
	void wrongMethod();

}

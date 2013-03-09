package com.hospital.services.impl;

import java.util.List;
import java.util.Set;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;
import com.hospital.services.PatientService;

public class PatientServiceImpl implements PatientService{
    
    private PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public Integer countByRoom(Integer roomId) {
        return patientDao.countByRoom(roomId);
    }
    
    public List findByRoomGeneric(Integer roomId) {
    	return patientDao.findByRoomGeneric(roomId);
    }
    
    public List<Patient> findByRoom(Integer roomId) {
    	return patientDao.findByRoom(roomId);
    }
    
    public void generateReport() {
    	patientDao.generateReport();
    }
    
    public Set<Patient> findAll() {
    	return patientDao.findAll();
    }

	public int save(Patient patient) {
		return patientDao.save(patient);
	}

	public int updateRoom(Patient patient) {
		return patientDao.updateRoom(patient);
	}
	
	public void wrongMethod() {
		patientDao.wrongMethod();
	}
    
}

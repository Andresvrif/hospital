package com.hospital.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;
import com.hospital.services.PatientService;

@Component("patientService")
public class PatientServiceImpl implements PatientService{
    
    private PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> findByRoom(Integer roomId) {
        return patientDao.findByRoom(roomId);
    }
    
}

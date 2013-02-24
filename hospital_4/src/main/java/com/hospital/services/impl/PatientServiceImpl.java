package com.hospital.services.impl;

import java.util.List;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;
import com.hospital.services.PatientService;

public class PatientServiceImpl implements PatientService{
    
    private PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> findByRoom(Integer roomId) {
        return patientDao.findByRoom(roomId);
    }
    
}

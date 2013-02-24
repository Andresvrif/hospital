package com.hospital.services;

import java.util.List;

import com.hospital.model.Patient;

public interface PatientService {
    
    public List<Patient> findByRoom(Integer roomId);

}

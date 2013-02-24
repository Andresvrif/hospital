package com.hospital.services.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Required;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;
import com.hospital.services.PatientService;

public class PatientServiceImpl implements PatientService{
    
    private PatientDao patientDao;
    private String requiredString;

    // BeanPostProcessor de inicialización
    @PostConstruct
    private void init() {
        System.out.println("Soy el servicio PatientService y este es el BeanPostProcessor " + requiredString);
    }

    @PreDestroy
    private void fin() {
        System.out.println("Eso es todo amigos");
    }

    // BeanPostProcessor de pre-inicialización
    @Required
    public void setRequiredString(String requiredString) {
        this.requiredString = requiredString;
    }

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> findByRoom(Integer roomId) {
        return patientDao.findByRoom(roomId);
    }
    
}

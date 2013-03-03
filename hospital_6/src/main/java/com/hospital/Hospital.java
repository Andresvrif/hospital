package com.hospital;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.hospital.model.Patient;
import com.hospital.services.PatientService;

public class Hospital {

    public static void main(String [] args)
    {

    	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml");

        PatientService patientService = (PatientService) context.getBean("patientService");

        List<Patient> patients = patientService.findByRoom(1);
        System.out.println("Pacientes en la habitacion 1: ");
        for(Patient patient : patients) {
            System.out.println("-->"+patient.getId()+","+patient.getName()+" "+patient.getLastName());
        }
        patients = patientService.findByRoom(2);
        System.out.println("Pacientes en la habitacion 2: ");
        for(Patient patient : patients) {
            System.out.println("-->"+patient.getId()+","+patient.getName()+" "+patient.getLastName());
        }
        
        try {
        	patients = patientService.findByRoom(-1);
        } catch(IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException) {
        	System.out.println(incorrectResultSizeDataAccessException.getMessage());
        }

    }

}

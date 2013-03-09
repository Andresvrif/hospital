package com.hospital;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.hospital.model.Patient;
import com.hospital.services.PatientService;

public class Hospital {

    public static void main(String [] args)
    {

    	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml");

        PatientService patientService = (PatientService) context.getBean("patientService");

        Integer count = patientService.countByRoom(1);
        
        System.out.println("Número de pacientes en la habitación 1: "+count);
        
        List patientsMap = patientService.findByRoomGeneric(1);
        System.out.println("Pacientes: " + patientsMap.toString());
        
        List<Patient> patients = patientService.findByRoom(1);
        
        for(Patient patient : patients) {
        	System.out.println("Paciente: " + patient.getName() + " " +patient.getLastName());
        }
        
        Set<Patient> patientSet = patientService.findAll();
        System.out.println("Pacientes en todas las habitaciones: ");
        for(Patient patient : patientSet) {
        	System.out.println(patient.getName() + " " +patient.getLastName());
        }
        
        Patient newPatient = new Patient();
        newPatient.setId(patientSet.size()+1);
        newPatient.setName("Lucia");
        newPatient.setLastName("Bastien");
        newPatient.setRoomId(1);
        
        int result = patientService.save(newPatient);
        if(result==1) {
        	System.out.println("Paciente insertado");
        }
        
        patientService.generateReport();
        
        newPatient.setRoomId(2);
        result = patientService.updateRoom(newPatient);
        if(result==1) {
        	System.out.println("Paciente cambiado de habitación");
        }
        
        patientService.generateReport();
        
        try {
        	patientService.wrongMethod();
        } catch(DataAccessException e) {
        	System.out.println(e.getMessage());
        }
        
    }

}

package com.hospital;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hospital.config.HospitalConfig;
import com.hospital.model.Patient;
import com.hospital.services.PatientService;

public class Hospital {

    public static void main(String [] args)
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(HospitalConfig.class);

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

    }

}

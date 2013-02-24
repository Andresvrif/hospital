package com.hospital.services.stubs;

import java.util.ArrayList;
import java.util.List;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;

public class StubPatientDao implements PatientDao {

    public List<Patient> findByRoom(Integer roomId) {

        List<Patient> patients = new ArrayList<Patient>();

        if(roomId!=null) {
            patients.add(new Patient("TestPatient","1"));
            patients.add(new Patient("TestPatient","2"));
        }
        
        return patients;

   }
}

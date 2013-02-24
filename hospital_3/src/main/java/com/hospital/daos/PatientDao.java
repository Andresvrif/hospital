package com.hospital.daos;

import java.util.List;

import com.hospital.model.Patient;

public interface PatientDao {

    List<Patient> findByRoom(Integer roomId);

}

package com.hospital.services.stubs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.hospital.services.impl.PatientServiceImpl;


@RunWith(JUnit4.class)
public class PatientServiceImplTests {

    private PatientServiceImpl patientService;

    @Before
    public void setUp() {
        patientService = new PatientServiceImpl(new StubPatientDao());
    }

    @Test
    public void testPatientsFound() {
        Assert.assertEquals(patientService.findByRoom(1).size(), 2);
    }

    @Test
    public void testPatientsNotFound() {
        Assert.assertEquals(patientService.findByRoom(null).size(),0);
    }


}

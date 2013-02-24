package com.hospital.services.com.hospital.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hospital.services.PatientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-hospital.xml")
public class HospitalIntegrationTests {

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientsFound() {

        Assert.assertEquals(patientService.findByRoom(1).size(),2);

    }

}

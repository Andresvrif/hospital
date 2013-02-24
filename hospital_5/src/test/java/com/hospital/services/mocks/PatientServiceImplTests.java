package com.hospital.services.mocks;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;
import com.hospital.services.impl.PatientServiceImpl;

@RunWith(JUnit4.class)
public class PatientServiceImplTests {

    private PatientServiceImpl patientService;

    // 1: Generamos el mock
    private PatientDao patientDaoMock = createMock(PatientDao.class);

    @Before
    public void setUp() {

        patientService = new PatientServiceImpl(patientDaoMock);

    }

    @Test
    public void testPatientsFound() {

        List<Patient> patients = new ArrayList<Patient>();
        patients.add(new Patient("TestPatient","1"));
        patients.add(new Patient("TestPatient","2"));

        // 2: Especificamos el comportamiento esperado
        expect(patientDaoMock.findByRoom(1)).andReturn(patients);
        replay(patientDaoMock);

        // 3: Realizamos la prueba
        Assert.assertEquals(patientService.findByRoom(1).size(), 2);

        // 4: Verificamos que el mock se ha comportado como esperábamos (no se han omitido llamadas, ...)
        verify(patientDaoMock);

    }

}

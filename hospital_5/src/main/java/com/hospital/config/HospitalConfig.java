package com.hospital.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.hospital.daos.PatientDao;
import com.hospital.daos.jdbc.PatientDaoJdbc;
import com.hospital.services.PatientService;
import com.hospital.services.impl.PatientServiceImpl;

@Configuration
public class HospitalConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("test-data.sql").build();
        return db;
    }

    
    @Bean
    public PatientDao patientDao() {
        PatientDaoJdbc patientDao = new PatientDaoJdbc(dataSource());
        return  patientDao;
    }
    
    @Bean
    public PatientService patientService() {
        PatientServiceImpl patientService = new PatientServiceImpl(patientDao());
        return patientService;
    }
    
}

package com.hospital.daos.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;

public class PatientDaoJdbc implements PatientDao {

    private DataSource dataSource;

    public PatientDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Patient> findByRoom(Integer roomId) {

        List<Patient> patients = new ArrayList<Patient>();

        PreparedStatement query = null;

        String queryString = "SELECT ID, NAME, LASTNAME FROM T_PATIENT WHERE ROOMID=?";

        try {

            Connection connection = dataSource.getConnection();

            query = connection.prepareStatement(queryString);
            query.setInt(1, roomId);

            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String lastName = rs.getString("LASTNAME");
                Patient patient = new Patient(name,lastName);
                patient.setId(id);
                patient.setRoomId(roomId);
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return patients;

    }

}

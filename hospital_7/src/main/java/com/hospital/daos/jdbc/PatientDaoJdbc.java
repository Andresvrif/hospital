package com.hospital.daos.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.hospital.daos.PatientDao;
import com.hospital.model.Patient;

public class PatientDaoJdbc implements PatientDao {

	private JdbcTemplate jdbcTemplate;
  
    public PatientDaoJdbc(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Integer countByRoom(Integer roomId) {

        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM T_PATIENT WHERE ROOMID=?", roomId);

    }
    
    public List findByRoomGeneric(Integer roomId) {
    	
		return jdbcTemplate.queryForList("SELECT * FROM T_PATIENT WHERE ROOMID=?", roomId);
    	
    }
    
    public List<Patient> findByRoom(Integer roomId) {
    
    	return jdbcTemplate.query("SELECT ID, NAME, LASTNAME FROM T_PATIENT WHERE ROOMID=?", new PatientMapper(), roomId);
    	
    }
    
    public void generateReport() {
    	jdbcTemplate.query("SELECT * FROM T_PATIENT", new PatientRowCallbackHandler());
    }
    
    public Set<Patient> findAll() {
    	return jdbcTemplate.query("SELECT * FROM T_PATIENT", new PatientResultSetExtractor());
    }
    
    public int save(Patient patient) {
    	
    	return jdbcTemplate.update("INSERT INTO T_PATIENT(ID, NAME, LASTNAME, ROOMID) VALUES (?,?,?,?)",patient.getId(), patient.getName(), patient.getLastName(), patient.getRoomId());
    	
    }
    
    public int updateRoom(Patient patient) {
    	
    	return jdbcTemplate.update("UPDATE T_PATIENT SET ROOMID=? WHERE ID=?",patient.getRoomId(), patient.getId());
    	
    }
    
    public void wrongMethod() {
    	jdbcTemplate.queryForInt("ESTO VA A FALLAR");
    }
    
    // Mapea cada fila del ResultSet a un objeto Patient
    private class PatientMapper implements RowMapper<Patient> {
    	
    	public Patient mapRow(ResultSet rs, int row) throws SQLException {
			Patient patient = new Patient();
			patient.setId(rs.getInt(1));
			patient.setName(rs.getString(2));
			patient.setLastName(rs.getString(3));
			return patient;
		}
    	
    }
    
    // Procesa filas de una query
    private class PatientRowCallbackHandler implements RowCallbackHandler {

		public void processRow(ResultSet rs) throws SQLException {
			
			System.out.println("--Paciente--");
			System.out.println("Nombre: " + rs.getString(2));
			System.out.println("Apellidos: " + rs.getString(3));
			System.out.println("Habitación: " + rs.getInt(4));
			
		}
    	
    }
    
    // Procesa el resultado de una sóla vez
    private class PatientResultSetExtractor implements ResultSetExtractor<Set<Patient>> {

		public Set<Patient> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			Set<Patient> patientsSet = new HashSet<Patient>();
			
			while(rs.next()) {
				
				Patient patient = new Patient();
				patient.setId(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setLastName(rs.getString(3));
				
				patientsSet.add(patient);
			}
			
			return patientsSet;
			
		}
    	
    }

}

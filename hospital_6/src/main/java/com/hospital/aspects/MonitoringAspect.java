package com.hospital.aspects;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.hospital.model.Patient;
import com.hospital.services.PatientService;

@Aspect
public class MonitoringAspect {
	
	@Before("execution(* com.hospital.services.PatientService.findByRoom(*))")
	public void findingPatients() {
		System.out.println("@Before -> Buscando pacientes en habitación...");
	}
	
	@Before("execution(* com.hospital.*.PatientService.findByRoom(*))")
	public void findingPatientsWithJoinPoint(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().toShortString();
		Object roomId = joinPoint.getArgs()[0];
		System.out.println("@Before -> " + name + " buscando pacientes en habitación "+roomId);
	}
	
	@Before("execution(* com.hospital.*.PatientService.findByRoom(*)) && target(patientService) && args(roomId)")
	public void findingPatientsWithJoinPoint(PatientService patientService, Integer roomId) {
		String name = patientService.toString();
		System.out.println("@Before (selección contexto) -> " + name + " buscando pacientes en habitación "+roomId);
	}
	
	@AfterReturning(value="execution(* com..PatientService.find*(..))",returning="patients")
	public void returningPatientsWithJoinPoint(JoinPoint joinPoint, List<Patient> patients) {
		String name = joinPoint.getSignature().toShortString();
		Object roomId = joinPoint.getArgs()[0];
		System.out.println("@AfterReturning -> " + name + " devolviento "+patients.size()+" pacientes en habitación "+roomId);
	}
	
	@AfterThrowing(value="execution(* com..PatientService.find*(..))",throwing="incorrectResultSizeDataAccessException")
	public void returningExceptionWithJoinPoint(JoinPoint joinPoint, IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException) {
		String name = joinPoint.getSignature().toShortString();
		Object roomId = joinPoint.getArgs()[0];
		System.out.println("@AfterThrowing -> " + name + " ocurrió un error al buscar pacientes en habitación "+roomId);
	}

	@After(value="execution(* com.hospital.*.PatientService.findByRoom(*))")
	public void returningWithJoinPoint(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().toShortString();
		Object roomId = joinPoint.getArgs()[0];
		System.out.println("@After -> " + name + " terminó de buscar pacientes en habitación "+roomId);
	}
	
	@SuppressWarnings("unchecked")
	@Around(value="daoMethods()")
	public List<Patient> aroundWithJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
		String name = joinPoint.getSignature().toShortString();
		Object roomId = joinPoint.getArgs()[0];
		System.out.println("@Around -> " + name + " va a buscar pacientes en la habitación "+roomId);
		List<Patient> patients = (List<Patient>) joinPoint.proceed();
		System.out.println("@Around -> " + name + " terminó de buscar en la habitación "+roomId);
		return patients;
	}
	
	@Pointcut("execution(* com.hospital..*Dao.*(..))")
	public void daoMethods() {}

}

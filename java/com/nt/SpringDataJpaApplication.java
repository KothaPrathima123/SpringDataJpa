package com.nt;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nt.entiy.Doctor;
import com.nt.service.IDoctorService;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SpringDataJpaApplication.class, args);
		IDoctorService service=ctx.getBean("doctorService",IDoctorService.class);
		try {
			Doctor doc=new Doctor();
			doc.setDocName("rajesh");
			doc.setSpecialization("MD_Cardio");
			//invoke b method
			String resMsg=service.registerDoctor(doc);
			System.out.println(resMsg);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("count of records : "+service.fetchDoctorCount());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("201 id exists ?:"+service.checkDoctorAvailability(201));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
			Iterable<Doctor>it=service.showAllDoctors();
			it.forEach(doc->{
				System.out.println(doc);
				});
			System.out.println("------------");
			it.forEach(doc->System.out.println(doc));//improved foreach() meth in java 8
			System.out.println("-------------");
			it.forEach(System.out::println);//java 8 forEach() method +static ref
			System.out.println("---------------");
			for(Doctor doc:it) {
				System.out.println(doc);
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
		try {
			//service.showAllDoctorsByIds(List.of(null));//throws IllegalArgumentException
			//service.showAllDoctorsByIds(List.of(100,null,200))//throws NullPointerException
			service.showAllDoctorsByIds(Arrays.asList(1,2,101,null)).forEach(System.out::println);//valid
			service.showAllDoctorsByIds(List.of(1,2,100,200,101)).forEach(System.out::println);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	 try {
		 Doctor d=new Doctor();
		 d.setDocId(1015);//full obj modifn or insertion
		 d.setDocName("karan");
		 d.setIncome(9000.0);
		 d.setSpecialization("Cardio");
		 System.out.println(service.registerOrUpdateDoctor(d));
	 } catch(Exception e) {
		 e.printStackTrace();
	 }
	try {
		Doctor d=new Doctor();
		d.setDocId(2);
		d.setDocName("raj");
		d.setIncome(2000.0);
		d.setSpecialization("MD");
		String resultMsg=service.registerOrUpdateDoctor(d);//partial obj modfn
		System.out.println(resultMsg);
	} catch(Exception e) {
		e.printStackTrace();
	}
	try {
		System.out.println(service.deleteDoctorById(111));
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	try {
		Doctor d=new Doctor();
		d.setDocId(12);
	
		d.setDocName("karan");
		System.out.println(service.deleteDoctor(d));
	
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	try {
		System.out.println(service.removeAllDoctors());
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	try {
		System.out.println(service.removeDoctorsByIds(List.of(14,15)));
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	
}

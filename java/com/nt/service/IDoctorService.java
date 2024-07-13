package com.nt.service;

import java.util.List;

import com.nt.entiy.Doctor;


public interface IDoctorService {

	public String registerDoctor(Doctor doc);
	public String registerAllDoctors(List<Doctor> list);
	public long fetchDoctorCount();
	public boolean checkDoctorAvailability(Integer id);
	public Iterable<Doctor> showAllDoctors();
	public Iterable<Doctor> showAllDoctorsByIds(Iterable<Integer> ids);
	public Doctor showDoctorById(Integer id);
	//full obj modifn
	public String registerOrUpdateDoctor(Doctor d);
	//partial obj modn
	//public String updateCustAddrs(int cno,String newAddrs);
	public String deleteDoctorById(Integer id);
	public String deleteDoctor(Doctor d);
	public String removeAllDoctors();
	public String removeDoctorsByIds(Iterable<Integer> ids);
	public String removeDoctorById(int id);
	
}

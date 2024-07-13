package com.nt.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entiy.Doctor;
import com.nt.repository.IDoctorRepo;

@Service("doctorService")
public class DoctorMgmtServiceImpl implements IDoctorService{
	@Autowired
	private IDoctorRepo doctorRepo;
	@Override
	public String registerDoctor(Doctor doctor) {
		Doctor doc=doctorRepo.save(doctor);
		return "Doctor obj is saved with id value: "+doc.getDocId();
	}
	@Override
	public boolean checkDoctorAvailability(Integer id) {
		boolean flag=doctorRepo.existsById(id);
		return flag;
	}
	@Override
	public String registerAllDoctors(List<Doctor> list) {
		Iterator<Doctor> savedList=(Iterator<Doctor>) doctorRepo.saveAll(list);
		return list.size()+" patients are saved";
	}
	
	@Override
	public long fetchDoctorCount() {
		return doctorRepo.count();
	}
	@Override
	public Iterable<Doctor> showAllDoctors() {
		// TODO Auto-generated method stub
		return doctorRepo.findAll();
	}
	@Override
	public Iterable<Doctor> showAllDoctorsByIds(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return doctorRepo.findAllById(ids);
	}
	@Override
	public Doctor showDoctorById(Integer id) {
		Doctor dutyDoctor=new Doctor();
		dutyDoctor.setSpecialization("duty doctor");
		Doctor doctor=doctorRepo.findById(id).orElse(dutyDoctor);
		return doctor;
	}
	/*
	 * @Override public Doctor showDoctorById(Integer id) { Doctor
	 * doctor=doctorRepo.findById(id).orElseThrow(()->new
	 * IllegalArgumentException("invalid doctor id")); return doctor; }
	 */
	/*
	 * @Override public Doctor showDoctorById(Integer id) { Optional<Doctor>
	 * opt=doctorRepo.findById(id); if(opt.get()) return opt.get(); else throw
	 * IllegalArgumentException("invalid doctor id"); }
	 */
	@Override
	public String registerOrUpdateDoctor(Doctor d) {
		Optional<Doctor> opt=doctorRepo.findById(d.getDocId());
		if(opt.isPresent()) {
			doctorRepo.save(d);//for update obj modification
			return d.getDocId()+" Doctor details are fund and updated";
		} else
		{
			return "Doctor is saved with id value: "+doctorRepo.save(d).getDocId();
		}
	}
	/*@Override
	public String updateCustomerAddrs(int cno,String newAddrs) {
		Optional<Customer> opt=custRepo.findById(cno);
		if(opt.isPresent()) {
			Customer cust=opt.get();
			cust.setCadd(newAddrs);
			custRepo.save(cust);
			return cno+" customer address ia updated";
		
		} 
	return cno+" customer not found for updation";
	}*/
	@Override
	public String deleteDoctorById(Integer id) {
		Optional<Doctor> opt=doctorRepo.findById(id);
		if(opt.isPresent()) {
			doctorRepo.deleteById(id);
			return id+" doctor is deleted";
		} else {
			return id+" doctor not found";
		}
	}
	@Override
	public String deleteDoctor(Doctor d) {
		//load object
		Optional<Doctor> opt=doctorRepo.findById(d.getDocId());
		if(opt.isEmpty()) {
			return d.getDocId()+" doctor is not found";
		} else {
			doctorRepo.delete(opt.get());
			return d.getDocId()+" doctor is not found and deleted";
		}
	}
	@Override
	public String removeAllDoctors()
	{
		long count =doctorRepo.count();
		if(count>0) {
			doctorRepo.deleteAll();
			return count+" no of records are deleted";
		}
		return "no records found delete";
	}
	@Override
	public String removeDoctorsByIds(Iterable<Integer> ids) {
		List<Doctor> list=(List<Doctor>)doctorRepo.findAllById(ids);
		doctorRepo.deleteAllById(ids);
		return list.size()+" no of records deleted";
	}
	
	public String removeDoctorById(int id) {
		Optional<Doctor> opt=doctorRepo.findById(id);
		if(opt.isPresent()) {
			doctorRepo.deleteById(id);
			return id+" patient is found and deleted";
		}else {
			return id+" patient is found for deletion";
		}
	}
	
}
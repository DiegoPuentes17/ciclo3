
package com.usa.ciclo3.ciclo3.service;

import com.usa.ciclo3.ciclo3.modelo.Doctor;
import com.usa.ciclo3.ciclo3.repository.DoctorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    
    public List<Doctor> getAll(){
        return doctorRepository.getAll();
    }
    public Optional<Doctor> getDoctor(int id){
        
        return doctorRepository.getDoctor(id);
    }
    public Doctor save(Doctor doctor){
        if(doctor.getId()== null){
            return doctorRepository.Save(doctor);
        }else{
            Optional<Doctor> DoctorNull = doctorRepository.getDoctor(doctor.getId());
            
            if(DoctorNull.isEmpty()){
                return doctorRepository.Save(doctor);
            }else{
                return doctor;
            }
        }
        
    }
        public Doctor update(Doctor doctor){
        if(doctor.getId()!=null){
            Optional<Doctor> CRUD= doctorRepository.getDoctor(doctor.getId());
            if(!CRUD.isEmpty()){
                if(doctor.getName()!=null){
                    CRUD.get().setName(doctor.getName());
                }
                if(doctor.getDepartment()!=null){
                    CRUD.get().setDepartment(doctor.getDepartment());
                }
                if(doctor.getYear()!=null){
                    CRUD.get().setYear(doctor.getYear());
                }
                if(doctor.getDescription()!=null){
                    CRUD.get().setDescription(doctor.getDescription());
                }
                if(doctor.getSpecialty()!=null){
                    CRUD.get().setSpecialty(doctor.getSpecialty());
                }
                
                doctorRepository.Save(CRUD.get());
                return CRUD.get();
            }else{
                return doctor;
            }
        }else{
            return doctor;
        }
    }

    public boolean delete(int id) {
        Boolean aBoolean = getDoctor(id).map(doctor -> {
            doctorRepository.delete(doctor);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}

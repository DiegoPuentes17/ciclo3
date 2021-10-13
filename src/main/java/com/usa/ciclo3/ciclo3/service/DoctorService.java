
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
}

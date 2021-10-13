
package com.usa.ciclo3.ciclo3.repository;

import com.usa.ciclo3.ciclo3.modelo.Doctor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorRepository {
    
    @Autowired
    private DoctorCrudRepository doctorCrudRepository;
    
    public List<Doctor> getAll(){
        return (List<Doctor>) doctorCrudRepository.findAll();
    }
    public Optional<Doctor> getDoctor(int id){
        return doctorCrudRepository.findById(id);
    }
    public Doctor Save(Doctor doctor){
        return doctorCrudRepository.save(doctor);
    }
}

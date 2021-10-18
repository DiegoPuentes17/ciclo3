
package com.usa.ciclo3.ciclo3.service;
import com.usa.ciclo3.ciclo3.modelo.Specialty;
import com.usa.ciclo3.ciclo3.repository.SpecialtyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {
    
    @Autowired
    private SpecialtyRepository specialtyRepository;
    
    public List<Specialty> getAll(){
        return specialtyRepository.getAll();
    }
    
    public Optional<Specialty> getSpecialty(int id){
        
        return specialtyRepository.getSpecialty(id);
    }
    
    public Specialty save(Specialty specialty){
        if(specialty.getId()== null){
            return specialtyRepository.save(specialty);
        }else{
            Optional<Specialty> specialtyNull = specialtyRepository.getSpecialty(specialty.getId());
            
            if(specialtyNull.isEmpty()){
                return specialtyRepository.save(specialty);
            }else{
                return specialty;
            }
        }
        
    }
    public Specialty update(Specialty specialty){
        if(specialty.getId()!=null){
            Optional<Specialty> e= specialtyRepository.getSpecialty(specialty.getId());
            if(!e.isEmpty()){
                if(specialty.getName()!=null){
                    e.get().setName(specialty.getName());
                }
                if(specialty.getDescription()!=null){
                    e.get().setDescription(specialty.getDescription());
                }
                if(specialty.getDoctor()!=null){
                    e.get().setDoctor(specialty.getDoctor());
                }
                
                specialtyRepository.save(e.get());
                return e.get();
            }else{
                return specialty;
            }
        }else{
            return specialty;
        }
    }

    public boolean delete(int Id) {
        Boolean aBoolean = getSpecialty(Id).map(specialty -> {
            specialtyRepository.delete(specialty);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}


package com.usa.ciclo3.ciclo3.web;

import com.usa.ciclo3.ciclo3.modelo.Specialty;
import com.usa.ciclo3.ciclo3.service.SpecialtyService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Specialty")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class SpecialtyController {
   @Autowired
    private SpecialtyService specialtyService;
    
    @GetMapping("/all")
    public List<Specialty> getSpecialty(){
        return specialtyService.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Specialty> getSpecialty(@PathVariable("id") int id){
        return specialtyService.getSpecialty(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Specialty save(@RequestBody Specialty specialty){
        return specialtyService.save(specialty);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Specialty update(@RequestBody Specialty specialty) {
        return specialtyService.update(specialty);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int Id) {
        return specialtyService.delete(Id);
    }
}

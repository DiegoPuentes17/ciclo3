/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.usa.ciclo3.ciclo3.repository;

import com.usa.ciclo3.ciclo3.modelo.Doctor;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author diego
 */
public interface DoctorCrudRepository extends CrudRepository<Doctor, Integer> {
    
}


package com.usa.ciclo3.ciclo3.repository;

import com.usa.ciclo3.ciclo3.modelo.Reservations;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservations> getAll(){
        return (List<Reservations>) reservationCrudRepository.findAll();
    }
    public Optional<Reservations> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }
    public Reservations save(Reservations reservations){
        return reservationCrudRepository.save(reservations);
    }
    public void delete(Reservations reservations){
        reservationCrudRepository.delete(reservations);
    }
}

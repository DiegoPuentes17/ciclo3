
package com.usa.ciclo3.ciclo3.repository;

import com.usa.ciclo3.ciclo3.modelo.Client;
import com.usa.ciclo3.ciclo3.modelo.Reservations;
import com.usa.ciclo3.ciclo3.modelo.custom.CountClient;
import java.util.ArrayList;
import java.util.Date;
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
    
    public List<Reservations> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }
    public List<Reservations> getReservationPeriod(Date dateOne , Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
        
    public List<CountClient> getTopClient(){

        List<CountClient> listStatus = new ArrayList<>();

        List<Object[]> report= reservationCrudRepository.countTotalReservationsByClient();
        for(int i=0; i<report.size();i++){
             listStatus.add(new CountClient ((Long)report.get(i)[1],(Client) report.get(i)[0]));
         
         }
        return listStatus;
    }
    
}

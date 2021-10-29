 
package com.usa.ciclo3.ciclo3.service;

import com.usa.ciclo3.ciclo3.modelo.Reservations;
import com.usa.ciclo3.ciclo3.modelo.custom.CountClient;
import com.usa.ciclo3.ciclo3.modelo.custom.StatusAmount;
import com.usa.ciclo3.ciclo3.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservations> getAll(){
        return reservationRepository.getAll();
    }
    
    public Optional<Reservations> getReservation(int id){
        
        return reservationRepository.getReservation(id);
    }
    
    public Reservations save(Reservations reservation){
        if(reservation.getIdReservation()== null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservations> reservationNull = reservationRepository.getReservation(reservation.getIdReservation());
            
            if(reservationNull.isEmpty()){
                return reservationRepository.save(reservation);
            }else{
                return reservation;
            }
        }
        
    }
    public Reservations update(Reservations reservations){
        if(reservations.getIdReservation()!=null){
            Optional<Reservations> e= reservationRepository.getReservation(reservations.getIdReservation());
            if(!e.isEmpty()){

                if(reservations.getStartDate()!=null){
                    e.get().setStartDate(reservations.getStartDate());
                }
                if(reservations.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservations.getDevolutionDate());
                }
                if(reservations.getClient()!=null){
                    e.get().setClient(reservations.getClient());
                }
                if(reservations.getDoctor()!=null){
                    e.get().setDoctor(reservations.getDoctor());
                }
                if(reservations.getScore()!=null){
                    e.get().setScore(reservations.getScore());
                }
               
                
                reservationRepository.save(e.get());
                return e.get();
            }else{
                return reservations;
            }
        }else{
            return reservations;
        }
    }

    public boolean delete(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservations -> {
            reservationRepository.delete(reservations);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public List<CountClient> getTopClient(){
        return reservationRepository.getTopClient();
    }
    
    public StatusAmount getStatusReport(){
        List<Reservations> completed=reservationRepository.getReservationByStatus("completed");
        List<Reservations> cancelled=reservationRepository.getReservationByStatus("cancelled");
        
        StatusAmount statusAmount = new StatusAmount(completed.size(), cancelled.size());
        return statusAmount;
    }
    public List<Reservations> getClientPeriod(String date1, String date2){
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        
        try{
            dateOne =parser.parse(date1);
            dateTwo =parser.parse(date2);
        }catch (ParseException e){
            e.printStackTrace();
        }
        if(dateOne.before(dateTwo)){
            return reservationRepository.getReservationPeriod(dateOne, dateTwo);
        }else{
            return new ArrayList<>();
        }
    }

}

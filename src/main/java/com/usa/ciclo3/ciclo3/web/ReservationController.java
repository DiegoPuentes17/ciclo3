
package com.usa.ciclo3.ciclo3.web;

import com.usa.ciclo3.ciclo3.modelo.Reservations;
import com.usa.ciclo3.ciclo3.modelo.custom.CountClient;
import com.usa.ciclo3.ciclo3.modelo.custom.StatusAmount;
import com.usa.ciclo3.ciclo3.service.ReservationService;
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
@RequestMapping("api/Reservation")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    //RETO 3
    @GetMapping("/all")
    public List<Reservations> getReservations(){
        return reservationService.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Reservations> getReservations(@PathVariable("id") int id){
        return reservationService.getReservation(id);
    }
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservations save(@RequestBody Reservations reservations){
        return reservationService.save(reservations);
    }
    
    //RETO 4
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservations update(@RequestBody Reservations reservations) {
        return reservationService.update(reservations);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int Id) {
        return reservationService.delete(Id);
    }
    
    //RETO 5

    @GetMapping("/report-status")
    public StatusAmount getReservationStatusAmount(){
        return reservationService.getStatusReport();
    }
    @GetMapping("/report-clients")
    public List<CountClient> getCountClient(){
        return reservationService.getTopClient();
    }
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservations> getDatesReport(@PathVariable("dateOne")String date1,@PathVariable("dateTwo")String date2){
        return reservationService.getClientPeriod(date1, date2);
    }

}

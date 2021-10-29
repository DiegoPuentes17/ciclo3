
package com.usa.ciclo3.ciclo3.repository;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.usa.ciclo3.ciclo3.modelo.Reservations;







public interface ReservationCrudRepository extends CrudRepository<Reservations, Integer>{
    
    
    public List<Reservations> findAllByStatus(String status);
    
    public List<Reservations> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    // select clientId, count(*) as "total" from reservacion group by cliendId order by total desc;
    @Query ("SELECT c.client, COUNT(c.client) from Reservations AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationsByClient();
}

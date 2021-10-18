
package com.usa.ciclo3.ciclo3.repository;

import com.usa.ciclo3.ciclo3.modelo.Messages;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {
    
    @Autowired
    private MessageCrudRepository messageCrudRepository;
    
    public List<Messages> getAll(){
        return (List<Messages>) messageCrudRepository.findAll();
    }
    public Optional<Messages> getMessages(int id){
        return messageCrudRepository.findById(id);
    }
    public Messages Save(Messages messages){
        return messageCrudRepository.save(messages);
    }
    public void delete(Messages messages){
        messageCrudRepository.delete(messages);
    }
}

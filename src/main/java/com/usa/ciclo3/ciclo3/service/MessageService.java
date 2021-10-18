package com.usa.ciclo3.ciclo3.service;

import com.usa.ciclo3.ciclo3.modelo.Messages;
import com.usa.ciclo3.ciclo3.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Messages> getAll(){
        return messageRepository.getAll();
    }
    public Optional<Messages> getMessage(int id){
        
        return messageRepository.getMessages(id);
    }
    public Messages save(Messages messages){
        if(messages.getIdMessage()== null){
            return messageRepository.Save(messages);
        }else{
            Optional<Messages> messageNull = messageRepository.getMessages(messages.getIdMessage());
            
            if(messageNull.isEmpty()){
                return messageRepository.Save(messages);
            }else{
                return messages;
            }
        }
        
    }
    public Messages update(Messages messages){
        if(messages.getIdMessage()!=null){
            Optional<Messages> CRUD= messageRepository.getMessages(messages.getIdMessage());
            if(!CRUD.isEmpty()){
                if(messages.getMessageText()!=null){
                    CRUD.get().setMessageText(messages.getMessageText());
                }
                if(messages.getDoctor()!=null){
                    CRUD.get().setDoctor(messages.getDoctor());
                }
                 if(messages.getClient()!=null){
                    CRUD.get().setClient(messages.getClient());
                }
                messageRepository.Save(CRUD.get());
                return CRUD.get();
            }else{
                return messages;
            }
        }else{
            return messages;
        }
    }

    public boolean delete(int id) {
        Boolean aBoolean = getMessage(id).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}

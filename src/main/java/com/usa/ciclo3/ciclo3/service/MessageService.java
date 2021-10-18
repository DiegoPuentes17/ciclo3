package com.usa.ciclo3.ciclo3.service;

import com.usa.ciclo3.ciclo3.modelo.Message;
import com.usa.ciclo3.ciclo3.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getAll(){
        return messageRepository.getAll();
    }
    public Optional<Message> getMessage(int id){
        
        return messageRepository.getMessage(id);
    }
    public Message save(Message message){
        if(message.getIdMessage()== null){
            return messageRepository.Save(message);
        }else{
            Optional<Message> messageNull = messageRepository.getMessage(message.getIdMessage());
            
            if(messageNull.isEmpty()){
                return messageRepository.Save(message);
            }else{
                return message;
            }
        }
        
    }
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> CRUD= messageRepository.getMessage(message.getIdMessage());
            if(!CRUD.isEmpty()){
                if(message.getMessageText()!=null){
                    CRUD.get().setMessageText(message.getMessageText());
                }
                if(message.getDoctor()!=null){
                    CRUD.get().setDoctor(message.getDoctor());
                }
                 if(message.getClient()!=null){
                    CRUD.get().setClient(message.getClient());
                }
                messageRepository.Save(CRUD.get());
                return CRUD.get();
            }else{
                return message;
            }
        }else{
            return message;
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

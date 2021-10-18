
package com.usa.ciclo3.ciclo3.service;

import com.usa.ciclo3.ciclo3.modelo.Client;
import com.usa.ciclo3.ciclo3.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }
    public Client save(Client client){
        if(client.getIdClient()== null){
            return clientRepository.save(client);
        }else{
            Optional<Client> clientNull = clientRepository.getClient(client.getIdClient());
            
            if(clientNull.isEmpty()){
                return clientRepository.save(client);
            }else{
                return client;
            }
        }
        
    }
    public Client update(Client client){
        if(client.getIdClient()!=null){
            Optional<Client> CRUD=clientRepository.getClient(client.getIdClient());
            if(!CRUD.isEmpty()){
                if(client.getName()!=null){
                    CRUD.get().setName(client.getName());
                }
                if(client.getEmail()!=null){
                    CRUD.get().setEmail(client.getEmail());
                }
                if(client.getMessages()!=null){
                    CRUD.get().setMessages(client.getMessages());
                }
                if(client.getPassword()!=null){
                    CRUD.get().setPassword(client.getPassword());
                }
                if(client.getAge()!=null){
                    CRUD.get().setAge(client.getAge());
                }
                clientRepository.save(CRUD.get());
                return CRUD.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }
    public boolean delete(int Id) {
        Boolean aBoolean = getClient(Id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
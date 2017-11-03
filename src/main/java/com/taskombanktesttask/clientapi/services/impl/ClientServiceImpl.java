package com.taskombanktesttask.clientapi.services.impl;

import com.taskombanktesttask.clientapi.data.ClientDAO;
import com.taskombanktesttask.clientapi.domain.dto.ClientDTO;
import com.taskombanktesttask.clientapi.domain.dto.DeleteClientDTO;
import com.taskombanktesttask.clientapi.domain.entities.ClientEntity;
import com.taskombanktesttask.clientapi.exceptions.ClientNotFoundException;
import com.taskombanktesttask.clientapi.exceptions.DataAccessException;
import com.taskombanktesttask.clientapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of client service
 * using {@link ClientDAO} that work with Hibernate
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;

    private ClientEntity findClientById(int clientId) {
        return clientDAO.getClientById(clientId);
    }

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public void createClient(ClientDTO clientDTO) {

        ClientEntity client = new ClientEntity(
                clientDTO.getFirstName(), clientDTO.getLastName(),
                clientDTO.getEmail(), clientDTO.getPhone()
        );

        clientDAO.createClient(client);

    }

    @Override
    public void deleteClient(DeleteClientDTO deleteClientDTO)
            throws ClientNotFoundException {

        ClientEntity clientToDelete;

        clientToDelete = findClientById(deleteClientDTO.getId());

        if(clientToDelete == null)
            throw new ClientNotFoundException("Client not found");

        clientDAO.deleteClient(clientToDelete);

    }

    @Override
    public List<ClientDTO> getClients(int limit, int offset) throws DataAccessException {

        return clientDAO.getClients(limit, offset).stream().map(

                client -> new ClientDTO(
                        client.getId(), client.getFirstName(), client.getLastName(),
                        client.getEmail(), client.getPhone()
                )

        ).collect(Collectors.toList());

    }

    @Override
    public void updateClient(ClientDTO clientDTO) throws ClientNotFoundException {

        ClientEntity clientToUpdate;

        clientToUpdate = findClientById(clientDTO.getId());

        clientToUpdate.setFirstName(clientDTO.getFirstName());
        clientToUpdate.setLastName(clientDTO.getLastName());
        clientToUpdate.setEmail(clientDTO.getEmail());
        clientToUpdate.setPhone(clientDTO.getPhone());

        clientDAO.updateClient(clientToUpdate);

    }

}

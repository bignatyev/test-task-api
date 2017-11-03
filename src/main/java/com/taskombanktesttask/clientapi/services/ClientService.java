package com.taskombanktesttask.clientapi.services;

import com.taskombanktesttask.clientapi.domain.dto.ClientDTO;
import com.taskombanktesttask.clientapi.domain.dto.DeleteClientDTO;
import com.taskombanktesttask.clientapi.exceptions.ClientNotFoundException;
import com.taskombanktesttask.clientapi.exceptions.DataAccessException;

import java.util.List;

/**
 * Service to manage clients database
 */
public interface ClientService {

    /**
     * Creates new client from data passed to method
     *
     * @param clientDTO new client data
     */
    void createClient(ClientDTO clientDTO);

    /**
     * Returns list of clients in database
     * @param limit amount of clients to return
     * @param offset offset from beginning of list
     * @return list of clients
     */
    List<ClientDTO> getClients(int limit, int offset) throws DataAccessException;

    /**
     * Updates client data
     * @param clientDTO new client data to replace old
     * @throws ClientNotFoundException thrown when client with given id not exist
     */
    void updateClient(ClientDTO clientDTO) throws ClientNotFoundException;

    /**
     * Deletes client
     * @param clientDTO object that contains id to delete
     * @throws ClientNotFoundException thrown when client with given id not exist
     */
    void deleteClient(DeleteClientDTO clientDTO)
            throws ClientNotFoundException;

}

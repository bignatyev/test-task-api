package com.taskombanktesttask.clientapi.data;

import com.sun.istack.internal.Nullable;
import com.taskombanktesttask.clientapi.domain.entities.ClientEntity;
import com.taskombanktesttask.clientapi.exceptions.DataAccessException;

import java.util.List;

/**
 * DAO for clients
 */
public interface ClientDAO {

    /**
     * Returns list of clients
     * @param limit amount of clients to return
     * @param offset offset from beginning of list
     * @return list of clients
     * @throws DataAccessException on database errors
     */
    List<ClientEntity> getClients(int limit, int offset) throws DataAccessException;

    /**
     * Returns client by given id
     *
     * @param clientId id of client
     * @return client with given id or null if it does not exist
     */
    @Nullable
    ClientEntity getClientById(int clientId);

    /**
     * Creates new client
     *
     * @param client client data
     */
    void createClient(ClientEntity client);

    /**
     * Updates given client
     * @param client client to update with new data
     */
    void updateClient(ClientEntity client);

    /**
     * Deletes client
     * @param client client to delete
     */
    void deleteClient(ClientEntity client);

}

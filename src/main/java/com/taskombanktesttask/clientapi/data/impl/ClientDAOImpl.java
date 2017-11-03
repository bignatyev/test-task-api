package com.taskombanktesttask.clientapi.data.impl;

import com.taskombanktesttask.clientapi.data.ClientDAO;
import com.taskombanktesttask.clientapi.domain.entities.ClientEntity;
import com.taskombanktesttask.clientapi.exceptions.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ClientEntity> getClients(int limit, int offset) throws DataAccessException {

        try {

            return entityManager.createNamedQuery(ClientEntity.QUERY_FIND_ALL, ClientEntity.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();

        } catch (
                QueryTimeoutException|TransactionRequiredException|PessimisticLockException|LockTimeoutException e
                ){
            throw new DataAccessException("To be written", e);
        }

    }

    @Override
    public ClientEntity getClientById(int id) {
        return entityManager.find(ClientEntity.class, id);
    }

    @Override
    public void createClient(ClientEntity client) {
        entityManager.persist(client);
    }

    @Override
    public void updateClient(ClientEntity client) {
        entityManager.merge(client);
    }

    @Override
    public void deleteClient(ClientEntity client) {
        entityManager.remove(client);
    }

}

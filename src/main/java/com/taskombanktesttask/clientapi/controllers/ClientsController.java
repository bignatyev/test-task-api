package com.taskombanktesttask.clientapi.controllers;

import com.taskombanktesttask.clientapi.domain.dto.ClientDTO;
import com.taskombanktesttask.clientapi.domain.dto.DeleteClientDTO;
import com.taskombanktesttask.clientapi.domain.dto.ReplyObject;
import com.taskombanktesttask.clientapi.exceptions.ClientNotFoundException;
import com.taskombanktesttask.clientapi.exceptions.DataAccessException;
import com.taskombanktesttask.clientapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value    = "/api/clients",
        produces = "application/json"
)
public class ClientsController {

    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(
            value  = "create",
            method = RequestMethod.POST
    )
    public ReplyObject create(@RequestBody ClientDTO client){
        clientService.createClient(client);
        return ReplyObject.success();
    }

    @RequestMapping(
            value  = "list",
            method = RequestMethod.GET,
            params = {"limit", "offset"}
    )
    public ReplyObject list(@RequestParam("limit") int limit, @RequestParam("offset") int offset)
            throws DataAccessException {
        return ReplyObject.success(clientService.getClients(limit, offset));
    }

    @RequestMapping(
            value  = "update",
            method = RequestMethod.PUT
    )
    public ReplyObject update(@RequestBody ClientDTO clientRequest) throws ClientNotFoundException {
        clientService.updateClient(clientRequest);
        return ReplyObject.success();
    }

    @RequestMapping(
            value  = "delete",
            method = RequestMethod.DELETE
    )
    public ReplyObject delete(@RequestBody DeleteClientDTO request) throws ClientNotFoundException {
        clientService.deleteClient(request);
        return ReplyObject.success();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ReplyObject internalErrorExceptionHandler(Exception e) {
        return ReplyObject.fail("Internal server error occurred");
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ReplyObject clientNotFoundExceptionHandler(ClientNotFoundException e) {
        return ReplyObject.fail("User not found");
    }

}

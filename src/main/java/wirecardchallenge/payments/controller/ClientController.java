package wirecardchallenge.payments.controller;

import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wirecardchallenge.Views;
import wirecardchallenge.payments.Service.ClientService;
import wirecardchallenge.payments.model.Client;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    @RequestMapping(path = "/clients/{id}")
    @JsonView(Views.Detail.class)
    @ResponseStatus(HttpStatus.OK)
    public Client getClient(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }

    @RequestMapping(path="/clients", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Client createClient(@RequestBody Client client) {
        return service.createClient(client);
    }
}

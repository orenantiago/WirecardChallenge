package wirecardchallenge.payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wirecardchallenge.payments.model.Client;
import wirecardchallenge.payments.repository.ClientRepository;

@RestController
public class demoController {

    @Autowired
    private ClientRepository repository;

    @RequestMapping(path = "/all")
    public @ResponseBody Iterable<Client> getAll() {
        Iterable<Client> all = repository.findAll();
        return all;
    }
}
